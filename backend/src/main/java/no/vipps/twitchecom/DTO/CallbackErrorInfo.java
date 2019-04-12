package no.vipps.twitchecom.DTO;

public class CallbackErrorInfo {
    private int errorCode;
    private String errorGroup;
    private String errorMessage;

    public CallbackErrorInfo() {
    }

    public CallbackErrorInfo(int errorCode, String errorGroup, String errorMessage) {
        this.errorCode = errorCode;
        this.errorGroup = errorGroup;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorGroup() {
        return errorGroup;
    }

    public void setErrorGroup(String errorGroup) {
        this.errorGroup = errorGroup;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
