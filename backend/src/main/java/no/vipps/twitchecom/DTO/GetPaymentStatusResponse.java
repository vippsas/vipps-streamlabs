package no.vipps.twitchecom.DTO;

import com.google.gson.annotations.SerializedName;

public class GetPaymentStatusResponse {

    private String orderId;
    private TransactionInfo transactionInfo;

    public GetPaymentStatusResponse() {
    }

    public GetPaymentStatusResponse(String orderId, TransactionInfo orderStatusInfoTransactionInfo) {
        this.orderId = orderId;
        this.transactionInfo = orderStatusInfoTransactionInfo;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public TransactionInfo getTransactionInfo() {
        return transactionInfo;
    }

    public void setTransactionInfo(TransactionInfo transactionInfo) {
        this.transactionInfo = transactionInfo;
    }
}
