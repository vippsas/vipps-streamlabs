package no.vipps.twitchecom.DTO;

public class InitiateRequestFromClientDTO {
    private int amount;
    private String transactionText;
    private String senderName;

    public InitiateRequestFromClientDTO(int amount, String transactionText, String senderName) {
        this.senderName = senderName;
        this.amount = amount;
        this.transactionText = transactionText;
    }

    public InitiateRequestFromClientDTO() {
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getTransactionText() {
        return transactionText;
    }

    public void setTransactionText(String transactionText) {
        this.transactionText = transactionText;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
}
