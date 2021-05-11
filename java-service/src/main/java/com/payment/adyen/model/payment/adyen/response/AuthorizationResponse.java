package com.payment.adyen.model.payment.adyen.response;

import com.payment.adyen.model.meta.AdditionalData;

public class AuthorizationResponse {

    private String pspReference;
    private String resultCode;
    private String authCode;
    private String currency;
    private String value;
    private String creationDate;
    private String md;
    private AdditionalData additionalData;
    private String issuerUrl;
    private String paRequest;

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getPspReference() {
        return pspReference;
    }

    public void setPspReference(String pspReference) {
        this.pspReference = pspReference;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getMd() {
        return md;
    }

    public void setMd(String md) {
        this.md = md;
    }

    public String getIssuerUrl() {
        return issuerUrl;
    }

    public void setIssuerUrl(String issuerUrl) {
        this.issuerUrl = issuerUrl;
    }

    public String getPaRequest() {
        return paRequest;
    }

    public void setPaRequest(String paRequest) {
        this.paRequest = paRequest;
    }

    public AdditionalData getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(AdditionalData additionalData) {
        this.additionalData = additionalData;
    }

    @Override
    public String toString() {
        return "AuthorizationResponse{" +
                "pspReference='" + pspReference + '\'' +
                ", resultCode='" + resultCode + '\'' +
                ", authCode='" + authCode + '\'' +
                ", currency='" + currency + '\'' +
                ", value='" + value + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", md='" + md + '\'' +
                ", additionalData=" + additionalData +
                ", issuerUrl='" + issuerUrl + '\'' +
                ", paRequest='" + paRequest + '\'' +
                '}';
    }
}
