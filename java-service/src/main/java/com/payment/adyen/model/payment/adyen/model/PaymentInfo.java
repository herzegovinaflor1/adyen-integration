package com.payment.adyen.model.payment.adyen.model;

import java.util.List;

public class PaymentInfo {

    private Integer paymentId;
    private List<String> pspReference;
    private String resultCode;
    private String authCode;
    private String currency;
    private Integer value;
    private List<String> creationDate;
    private List<String> operation;

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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<String> getOperation() {
        return operation;
    }

    public void setOperation(List<String> operation) {
        this.operation = operation;
    }

    public List<String> getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(List<String> creationDate) {
        this.creationDate = creationDate;
    }

    public List<String> getPspReference() {
        return pspReference;
    }

    public void setPspReference(List<String> pspReference) {
        this.pspReference = pspReference;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }
}
