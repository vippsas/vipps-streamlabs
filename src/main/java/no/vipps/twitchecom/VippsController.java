package no.vipps.twitchecom;

import no.vipps.twitchecom.DTO.InitiateRequestFromClientDTO;
import no.vipps.twitchecom.DTO.RegularCheckOutPaymentRequest;
import no.vipps.twitchecom.DTO.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = {"http://vippstwitchstage.herokuapp.com", "https://vippstwitchstage.herokuapp.com"})
@RestController
@RequestMapping
public class VippsController {

    @Autowired
    VippsService vippsService;

    @Autowired
    StreamlabService streamlabService;

    //Don't need this actually. Initiate Payment is taking care of this internally
    @GetMapping("/accesstoken")
    public String getAccessToken(){
        return vippsService.getAccessToken();
    }


    @PostMapping("/initiatePayment")
    public String initiatePayment(@RequestBody InitiateRequestFromClientDTO initiateRequestFromClientDTO) throws IOException {
        int amount = initiateRequestFromClientDTO.getAmount();
        String transactionText = initiateRequestFromClientDTO.getTransactionText();
        String senderName = initiateRequestFromClientDTO.getSenderName();

        return vippsService.initiatePayment(amount, transactionText, senderName);
    }

    //This endpoint listens for callback from Vipps and give us notice if the payment is captured, reserved or failed in any way.
    //NOTICE! Here I check if it's SALE(captured and fulfilled) or if it's RESERVED(Not fulfilled). This is not correct for production. Also need to
    //perform actions on other statuses based on your preferences. E.g. if reserved, capture if goods is sent/delivered.
    @PostMapping("/v2/payments/{orderId}")
    public String recieveCallback(@RequestBody RegularCheckOutPaymentRequest regularCheckOutPaymentRequest, @PathVariable String orderId) throws IOException {
        if(regularCheckOutPaymentRequest.getTransactionInfo().getStatus() == TransactionStatus.SALE ||
                regularCheckOutPaymentRequest.getTransactionInfo().getStatus() == TransactionStatus.RESERVED
        ){
            streamlabService.notifyStreamLab(orderId);
        }
        return "";
    }
}
