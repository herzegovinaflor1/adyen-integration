package com.payment.adyen.model.payment.graphql;

import java.io.Serializable;

public class AuthorizationRequestInput implements Serializable {

    private Integer value;
    private String currency;
    private String uuid;
    private String cardEncryptedJson;
    private Boolean stored;

    public String getCardEncryptedJson() {
        return cardEncryptedJson;
    }

    public void setCardEncryptedJson(String cardEncryptedJson) {
        this.cardEncryptedJson = cardEncryptedJson;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Boolean getStored() {
        return stored;
    }

    public void setStored(Boolean stored) {
        this.stored = stored;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
