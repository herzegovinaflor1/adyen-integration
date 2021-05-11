package com.payment.adyen.model.payment.adyen.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class AdyenRequest {

    @JsonIgnore
    private String idempotencyKey;

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public void setIdempotencyKey(String idempotencyKey) {
        this.idempotencyKey = idempotencyKey;
    }
}
