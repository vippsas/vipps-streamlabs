package no.vipps.twitchecom.DTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InitiatePaymentRequestDTO {

    @SerializedName("merchantInfo")
    @Expose
   private MerchantInfoDTO merchantInfoDTO;

    @SerializedName("customerInfo")
    @Expose
    private CustomerInfoDTO customerInfoDTO;

    @SerializedName("transaction")
    @Expose
    private TransactionDTO transactionDTO;

    public InitiatePaymentRequestDTO() {
    }

    public InitiatePaymentRequestDTO(MerchantInfoDTO merchantInfoDTO, CustomerInfoDTO customerInfoDTO, TransactionDTO transactionDTO) {
        this.merchantInfoDTO = merchantInfoDTO;
        this.customerInfoDTO = customerInfoDTO;
        this.transactionDTO = transactionDTO;
    }

    public MerchantInfoDTO getMerchantInfoDTO() {
        return merchantInfoDTO;
    }

    public void setMerchantInfoDTO(MerchantInfoDTO merchantInfoDTO) {
        this.merchantInfoDTO = merchantInfoDTO;
    }

    public CustomerInfoDTO getCustomerInfoDTO() {
        return customerInfoDTO;
    }

    public void setCustomerInfoDTO(CustomerInfoDTO customerInfoDTO) {
        this.customerInfoDTO = customerInfoDTO;
    }

    public TransactionDTO getTransactionDTO() {
        return transactionDTO;
    }

    public void setTransactionDTO(TransactionDTO transactionDTO) {
        this.transactionDTO = transactionDTO;
    }

    @Override
    public String toString() {
        return "InitiatePaymentRequestDTO{" +
                "merchantInfoDTO=" + merchantInfoDTO +
                ", customerInfoDTO=" + customerInfoDTO +
                ", transactionDTO=" + transactionDTO +
                '}';
    }
}
