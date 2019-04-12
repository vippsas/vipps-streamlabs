package no.vipps.twitchecom.DTO;

public class TransactionInfoDTO {
    int amount;
    String status;
    String transactionId;
    String timeStamp;

    public TransactionInfoDTO(int amount, String status, String transactionId, String timeStamp) {
        this.amount = amount;
        this.status = status;
        this.transactionId = transactionId;
        this.timeStamp = timeStamp;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
