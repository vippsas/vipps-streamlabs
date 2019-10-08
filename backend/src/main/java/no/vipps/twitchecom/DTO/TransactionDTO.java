package no.vipps.twitchecom.DTO;

public class TransactionDTO {
    private String orderId;
    private String amount;
    private String transactionText;

    public TransactionDTO(String orderId, String amount, String transactionText) {
        this.orderId = orderId;
        this.amount = amount;
        this.transactionText = transactionText;
    }

    public TransactionDTO() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTransactionText() {
        return transactionText;
    }

    public void setTransactionText(String transactionText) {
        this.transactionText = transactionText;
    }

    @Override
    public String toString() {
        return "TransactionDTO{" +
                "orderId='" + orderId + '\'' +
                ", amount='" + amount + '\'' +
                ", transactionText='" + transactionText + '\'' +
                '}';
    }
}
