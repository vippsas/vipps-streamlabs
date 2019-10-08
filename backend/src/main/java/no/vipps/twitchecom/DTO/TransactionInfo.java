package no.vipps.twitchecom.DTO;

import no.vipps.twitchecom.TransactionStatus;

public class TransactionInfo {
    private double amount;
    private TransactionStatus status;
    private String timeStamp;
    private String transactionId;

    public TransactionInfo() {
    }

    public TransactionInfo(double amount, TransactionStatus status, String timeStamp, String transactionId) {
        this.amount = amount;
        this.status = status;
        this.timeStamp = timeStamp;
        this.transactionId = transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
