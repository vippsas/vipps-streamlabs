package no.vipps.twitchecom.DTO;

import java.time.LocalDateTime;

public class CallbackTransactionInfoStatus {
    private int amount;
    private TransactionStatus status;
    private LocalDateTime timeStamp;
    private String transactionId;

    public CallbackTransactionInfoStatus(int amount, TransactionStatus status, LocalDateTime timeStamp, String transactionId) {
        this.amount = amount;
        this.status = status;
        this.timeStamp = timeStamp;
        this.transactionId = transactionId;
    }

    public CallbackTransactionInfoStatus() {
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
