package com.payment.adyen.model.payment.adyen.request;

import com.payment.adyen.model.payment.adyen.model.ModificationAmount;

public class RefundRequest extends AdyenRequest {

    private Integer paymentId;
    private String originalReference;
    private ModificationAmount modificationAmount;
    private String reference;
    private String merchantAccount;

    public String getOriginalReference() {
        return originalReference;
    }

    public void setOriginalReference(String originalReference) {
        this.originalReference = originalReference;
    }

    public ModificationAmount getModificationAmount() {
        return modificationAmount;
    }

    public void setModificationAmount(ModificationAmount modificationAmount) {
        this.modificationAmount = modificationAmount;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getMerchantAccount() {
        return merchantAccount;
    }

    public void setMerchantAccount(String merchantAccount) {
        this.merchantAccount = merchantAccount;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }
}
