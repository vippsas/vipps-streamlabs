package no.vipps.twitchecom.DTO;

public class InitiateRequestFromClientDTO {
    private String amountInNOK;
    private double amountInUSD;
    private String transactionText;
    private String senderName;

    public InitiateRequestFromClientDTO(String amountInNOK, double amountInUSD, String transactionText, String senderName) {
        this.amountInNOK = amountInNOK;
        this.amountInUSD = amountInUSD;
        this.transactionText = transactionText;
        this.senderName = senderName;
    }

    public InitiateRequestFromClientDTO() {
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

    public String getAmountInNOK() {
        return amountInNOK;
    }

    public void setAmountInNOK(String amountInNOK) {
        this.amountInNOK = amountInNOK;
    }

    public double getAmountInUSD() {
        return amountInUSD;
    }

    public void setAmountInUSD(double amountInUSD) {
        this.amountInUSD = amountInUSD;
    }
}
