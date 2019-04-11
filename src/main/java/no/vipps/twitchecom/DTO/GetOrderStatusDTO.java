package no.vipps.twitchecom.DTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetOrderStatusDTO {

    private String orderId;
    @SerializedName("transactionInfo")
    @Expose
    private TransactionInfoDTO transactionInfoDTO;

    public GetOrderStatusDTO() {
    }

    public GetOrderStatusDTO(String orderId, TransactionInfoDTO transactionInfoDTO) {
        this.orderId = orderId;
        this.transactionInfoDTO = transactionInfoDTO;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public TransactionInfoDTO getTransactionInfoDTO() {
        return transactionInfoDTO;
    }

    public void setTransactionInfoDTO(TransactionInfoDTO transactionInfoDTO) {
        this.transactionInfoDTO = transactionInfoDTO;
    }
}
