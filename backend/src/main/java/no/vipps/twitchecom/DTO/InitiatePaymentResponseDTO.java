package no.vipps.twitchecom.DTO;

public class InitiatePaymentResponseDTO {
    private String orderId;
    private String url;

    public InitiatePaymentResponseDTO(String orderId, String url) {
        this.orderId = orderId;
        this.url = url;
    }

    public InitiatePaymentResponseDTO() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

