package no.vipps.twitchecom.service;

import no.vipps.twitchecom.DTO.GetPaymentStatusResponse;
import no.vipps.twitchecom.DTO.RegularCheckOutPaymentRequest;
import no.vipps.twitchecom.TransactionStatus;
import no.vipps.twitchecom.DonationStatus;
import no.vipps.twitchecom.entity.OrderModel;
import no.vipps.twitchecom.OrderStatus;
import no.vipps.twitchecom.entity.Donation;
import no.vipps.twitchecom.repository.DonationRepository;
import no.vipps.twitchecom.repository.OrderRepository;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class DonateService {

    @Autowired
    VippsService vippsService;

    @Autowired
    StreamlabService streamlabService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    DonationRepository donationRepository;

    @Value("${TRANSACTION_TEXT}")
    private String TRANSACTION_TEXT;

    @Value("${STREAMER_NAME}")
    private String STREAMER_NAME;

    Logger logger = Logger.getLogger(DonateService.class.getName());

    @Transactional
    public String initDonateToStreamer(String amountInNOK, double amountInUSD, String messageToStreamer, String senderName) throws IOException {

        Optional<OrderModel> latestOrder = orderRepository.findTopByOrderByIdDesc();

        Integer month = Integer.parseInt("" + LocalDateTime.now().getMonthValue() + "" + LocalDateTime.now().getMonthValue());

        Integer orderId = month + latestOrder.map(orderModel -> orderModel.getId() + 1).orElse(1);

        OrderModel order = new OrderModel(OrderStatus.PENDING, System.currentTimeMillis() / 1000, STREAMER_NAME.toLowerCase() + (orderId + 1000000));

        order = orderRepository.save(order);

        logger.info("INITDONATETOSTREAMER VIPPS orderId" + order.getVippsOrderId());

        Donation streamLabModel = new Donation(senderName, amountInNOK, amountInUSD, messageToStreamer, order.getVippsOrderId(), DonationStatus.PENDING);

        streamLabModel = donationRepository.save(streamLabModel);

        return vippsService.initiatePayment(streamLabModel.getVippsOrderId(), streamLabModel.getAmountInNOK(), streamLabModel.getAmountInUSD(), TRANSACTION_TEXT);

    }

    @Transactional
    public void processCallback(RegularCheckOutPaymentRequest regularCheckOutPaymentRequest) throws IOException {

        logger.info("PROCESS CALLBACK IN DONATE SERVICE ORDERID= " + regularCheckOutPaymentRequest.getOrderId());

        OrderModel orderModel = orderRepository.findByVippsOrderId(regularCheckOutPaymentRequest.getOrderId()).orElseThrow(() -> new RuntimeException("Could not find order"));
        Donation donation = donationRepository.findByVippsOrderId(regularCheckOutPaymentRequest.getOrderId()).orElseThrow(() -> new RuntimeException("Could not find donation"));

        TransactionStatus transactionStatus = regularCheckOutPaymentRequest.getTransactionInfo().getStatus();

        switch (transactionStatus) {
            case SALE:
                orderModel.setOrderStatus(OrderStatus.SUCCESS);
                break;
            case RESERVED:
                orderModel.setOrderStatus(OrderStatus.SUCCESS);
                break;
            case REJECTED:
                orderModel.setOrderStatus(OrderStatus.FAILED);
                donation.setDonationStatus(DonationStatus.FAILED);
                break;
            case CANCELLED:
                orderModel.setOrderStatus(OrderStatus.CANCELLED);
                donation.setDonationStatus(DonationStatus.CANCELLED);
                break;
            default: orderModel.setOrderStatus(OrderStatus.FAILED);
        }

        orderRepository.save(orderModel);

        if(orderModel.getOrderStatus() == OrderStatus.SUCCESS){
            boolean notifiedStreamlabSuccess = streamlabService.notifyStreamlabOfDonation(donation);

            if(notifiedStreamlabSuccess){
                donation.setDonationStatus(DonationStatus.SUCCESS);
            }else{
                donation.setDonationStatus(DonationStatus.FAILED);
            }
        }

        donationRepository.save(donation);
    }

    @Transactional
    public no.vipps.twitchecom.DTO.OrderStatus checkVippsOrderStatus(String orderId) throws IOException {

        OrderModel orderModel = orderRepository.findByVippsOrderId(orderId).orElseThrow(() -> new RuntimeException("Could not find order"));
        Donation donation = donationRepository.findByVippsOrderId(orderId).orElseThrow(() -> new RuntimeException("Could not find donation"));

        if(donation.getDonationStatus() == DonationStatus.PENDING) {

            GetPaymentStatusResponse getPaymentStatusResponse = vippsService.getPaymentStatusResponse(orderId);

            TransactionStatus transactionStatus = getPaymentStatusResponse.getTransactionInfo().getStatus();

            switch (transactionStatus) {
                case SALE:
                    orderModel.setOrderStatus(OrderStatus.SUCCESS);
                    break;
                case RESERVED:
                    orderModel.setOrderStatus(OrderStatus.SUCCESS);
                    break;
                case REJECTED:
                    orderModel.setOrderStatus(OrderStatus.FAILED);
                    donation.setDonationStatus(DonationStatus.FAILED);
                    break;
                case CANCELLED:
                    orderModel.setOrderStatus(OrderStatus.CANCELLED);
                    donation.setDonationStatus(DonationStatus.CANCELLED);
                    break;
                case CANCEL:
                    orderModel.setOrderStatus(OrderStatus.CANCELLED);
                    donation.setDonationStatus(DonationStatus.CANCELLED);
                    break;
                default: orderModel.setOrderStatus(OrderStatus.FAILED);
            }

            donationRepository.save(donation);
            orderRepository.save(orderModel);

        }

        return new no.vipps.twitchecom.DTO.OrderStatus(donation.getDonationStatus().name());
    }
}
