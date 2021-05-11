package com.payment.adyen.model.event;

public final class PaymentEvent {

    private String pspReference;
    private Integer paymentId;
    private String response;
    private String creationDate;
    private String resultCode;
    private String authCode;
    private String currency;
    private Integer value;
    private String operation;

    public String getCurrency() {
        return currency;
    }

    public String getAuthCode() {
        return authCode;
    }

    public String getResultCode() {
        return resultCode;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getResponse() {
        return response;
    }

    public String getPspReference() {
        return pspReference;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void setPspReference(String pspReference) {
        this.pspReference = pspReference;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
