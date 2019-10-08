package no.vipps.twitchecom.service;

import no.vipps.twitchecom.DTO.*;
import no.vipps.twitchecom.entity.OrderModel;
import no.vipps.twitchecom.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Value("${STREAMER_NAME}")
    private String STREAMER_NAME;

    public String getInternalOrderStatus(String orderId){
        Integer id = Integer.parseInt(orderId.replace(STREAMER_NAME.toLowerCase(), ""));

        Optional orderOptional = orderRepository.findById(id);
        OrderModel orderModel;

        if(orderOptional.isPresent()){
            orderModel = (OrderModel) orderOptional.get();

            return orderModel.getOrderStatus().name();
        }else{
            return null;
        }
    }
}
