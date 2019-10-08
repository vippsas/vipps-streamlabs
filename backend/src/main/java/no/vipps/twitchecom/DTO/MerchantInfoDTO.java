package no.vipps.twitchecom.DTO;

public class MerchantInfoDTO {

    private String merchantSerialNumber;
    private String callbackPrefix;
    private String fallBack;
    private Boolean isApp;

    public MerchantInfoDTO(String merchantSerialNumber, String callbackPrefix, String fallBack, Boolean isApp) {
        this.merchantSerialNumber = merchantSerialNumber;
        this.callbackPrefix = callbackPrefix;
        this.fallBack = fallBack;
        this.isApp = isApp;
    }

    public String getMerchantSerialNumber() {
        return merchantSerialNumber;
    }

    public void setMerchantSerialNumber(String merchantSerialNumber) {
        this.merchantSerialNumber = merchantSerialNumber;
    }

    public String getCallbackPrefix() {
        return callbackPrefix;
    }

    public void setCallbackPrefix(String callbackPrefix) {
        this.callbackPrefix = callbackPrefix;
    }

    public String getFallBack() {
        return fallBack;
    }

    public void setFallBack(String fallBack) {
        this.fallBack = fallBack;
    }

    public Boolean getApp() {
        return isApp;
    }

    public void setApp(Boolean app) {
        isApp = app;
    }

    @Override
    public String toString() {
        return "MerchantInfoDTO{" +
                "merchantSerialNumber='" + merchantSerialNumber + '\'' +
                ", callbackPrefix='" + callbackPrefix + '\'' +
                ", fallBack='" + fallBack + '\'' +
                ", isApp=" + isApp +
                '}';
    }
}