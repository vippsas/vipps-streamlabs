package no.vipps.twitchecom.controller;

import no.vipps.twitchecom.DTO.InitiateRequestFromClientDTO;
import no.vipps.twitchecom.DTO.OrderStatus;
import no.vipps.twitchecom.DTO.RegularCheckOutPaymentRequest;
import no.vipps.twitchecom.service.DonateService;
import no.vipps.twitchecom.service.OrderService;
import no.vipps.twitchecom.service.StreamlabService;
import no.vipps.twitchecom.service.VippsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.logging.Logger;

//@CrossOrigin(origins = {"http://vippstwitchstage.herokuapp.com", "https://vippstwitchstage.herokuapp.com"})
@CrossOrigin
@RestController
@RequestMapping
public class VippsController {

    @Autowired
    VippsService vippsService;

    @Autowired
    StreamlabService streamlabService;

    @Autowired
    DonateService donateService;

    @Autowired
    OrderService orderService;

    Logger logger = Logger.getLogger(VippsController.class.getName());

    @PostMapping("/initiatePayment")
    public String initiatePayment(@RequestBody InitiateRequestFromClientDTO initiateRequestFromClientDTO) throws IOException {
        logger.info("INITIATEPAYMENT ORDERID");
        String amountInNOK = initiateRequestFromClientDTO.getAmountInNOK();
        double amountInUSD = initiateRequestFromClientDTO.getAmountInUSD();
        String transactionText = initiateRequestFromClientDTO.getTransactionText();
        String senderName = initiateRequestFromClientDTO.getSenderName();

        return donateService.initDonateToStreamer(amountInNOK, amountInUSD, transactionText, senderName);
    }

    //This endpoint listens for callback from Vipps and give us notice if the payment is captured, reserved or failed in any way.
    @PostMapping("/v2/payments/{orderId}")
    public void recieveCallback(@RequestBody RegularCheckOutPaymentRequest regularCheckOutPaymentRequest, @PathVariable String orderId) throws IOException {
        logger.info("RECIVIED CALLBACK ORDERID= " + orderId);
        donateService.processCallback(regularCheckOutPaymentRequest);
    }

    @GetMapping("/getOrderStatus/{orderId}")
    public ResponseEntity getOrderStatus(@PathVariable String orderId) throws IOException {
        logger.info("GET ORDERSTATUS ORDERID= " + orderId);
        OrderStatus orderStatus = donateService.checkVippsOrderStatus(orderId);

        return new ResponseEntity(orderStatus, HttpStatus.OK);
    }

}
