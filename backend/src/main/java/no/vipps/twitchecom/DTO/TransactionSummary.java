package no.vipps.twitchecom.DTO;

public class TransactionSummary {
    private Integer capturedAmount;
    private Integer refundedAmount;
    private Integer remainingAmountToCapture;
    private Integer remainingAmountToRefund;

    public TransactionSummary(Integer capturedAmount, Integer refundedAmount, Integer remainingAmountToCapture, Integer remainingAmountToRefund) {
        this.capturedAmount = capturedAmount;
        this.refundedAmount = refundedAmount;
        this.remainingAmountToCapture = remainingAmountToCapture;
        this.remainingAmountToRefund = remainingAmountToRefund;
    }

    public TransactionSummary() {
    }

    public Integer getCapturedAmount() {
        return capturedAmount;
    }

    public void setCapturedAmount(Integer capturedAmount) {
        this.capturedAmount = capturedAmount;
    }

    public Integer getRefundedAmount() {
        return refundedAmount;
    }

    public void setRefundedAmount(Integer refundedAmount) {
        this.refundedAmount = refundedAmount;
    }

    public Integer getRemainingAmountToCapture() {
        return remainingAmountToCapture;
    }

    public void setRemainingAmountToCapture(Integer remainingAmountToCapture) {
        this.remainingAmountToCapture = remainingAmountToCapture;
    }

    public Integer getRemainingAmountToRefund() {
        return remainingAmountToRefund;
    }

    public void setRemainingAmountToRefund(Integer remainingAmountToRefund) {
        this.remainingAmountToRefund = remainingAmountToRefund;
    }
}
