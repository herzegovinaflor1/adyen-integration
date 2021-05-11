package com.payment.adyen.model.payment.adyen.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.payment.adyen.model.payment.adyen.model.ModificationAmount;

public class CaptureRequest extends AdyenRequest {

    @JsonIgnore
    private Integer paymentId;
    private String originalReference;
    private ModificationAmount modificationAmount;
    private String reference;
    private String merchantAccount;

    public String getMerchantAccount() {
        return merchantAccount;
    }

    public void setMerchantAccount(String merchantAccount) {
        this.merchantAccount = merchantAccount;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

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
}
