package no.vipps.twitchecom.DTO;

public class RegularCheckOutPaymentRequest {
    private String merchantSerialNumber;
    private String orderId;
    private CallbackTransactionInfoStatus transactionInfo;
    private CallbackErrorInfo callbackErrorInfo;

    public RegularCheckOutPaymentRequest() {
    }

    public String getMerchantSerialNumber() {
        return merchantSerialNumber;
    }

    public void setMerchantSerialNumber(String merchantSerialNumber) {
        this.merchantSerialNumber = merchantSerialNumber;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public CallbackTransactionInfoStatus getTransactionInfo() {
        return transactionInfo;
    }

    public void setTransactionInfo(CallbackTransactionInfoStatus transactionInfo) {
        this.transactionInfo = transactionInfo;
    }

    public CallbackErrorInfo getCallbackErrorInfo() {
        return callbackErrorInfo;
    }

    public void setCallbackErrorInfo(CallbackErrorInfo callbackErrorInfo) {
        this.callbackErrorInfo = callbackErrorInfo;
    }
}
