package no.vipps.twitchecom.entity;

import no.vipps.twitchecom.DonationStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "Donations")
public class Donation {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String sender;

    private String amountInNOK;

    private double amountInUSD;

    @NotNull
    private String message;

    @NotNull
    private String vippsOrderId;

    @Enumerated(EnumType.STRING)
    private DonationStatus donationStatus;

    public Donation(@NotNull String sender, @NotNull String amountInNOK, @NotNull double amountInUSD, @NotNull String message, @NotNull String vippsOrderId, DonationStatus donationStatus) {
        this.sender = sender;
        this.amountInNOK = amountInNOK;
        this.amountInUSD = amountInUSD;
        this.message = message;
        this.vippsOrderId = vippsOrderId;
        this.donationStatus = donationStatus;
    }

    public Donation() {
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getVippsOrderId() {
        return vippsOrderId;
    }

    public void setVippsOrderId(String vippsOrderId) {
        this.vippsOrderId = vippsOrderId;
    }

    public DonationStatus getDonationStatus() {
        return donationStatus;
    }

    public void setDonationStatus(DonationStatus donationStatus) {
        this.donationStatus = donationStatus;
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
