package no.vipps.twitchecom.DTO;

public class StreamLabDTO {
    private String sender;
    private String amount;
    private String message;

    public StreamLabDTO(String sender, String amount, String message) {
        this.sender = sender;
        this.amount = amount;
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
