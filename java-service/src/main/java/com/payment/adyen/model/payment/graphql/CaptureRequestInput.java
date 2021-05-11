package com.payment.adyen.model.payment.graphql;

import com.payment.adyen.model.payment.adyen.model.ModificationAmount;

import java.io.Serializable;

public class CaptureRequestInput implements Serializable {

    private String originalReference;
    private ModificationAmount modificationAmount;
    private Integer paymentId;
    private String uuid;

    public ModificationAmount getModificationAmount() {
        return modificationAmount;
    }

    public void setModificationAmount(ModificationAmount modificationAmount) {
        this.modificationAmount = modificationAmount;
    }

    public String getOriginalReference() {
        return originalReference;
    }

    public void setOriginalReference(String originalReference) {
        this.originalReference = originalReference;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
