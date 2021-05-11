package com.payment.adyen.model.payment.adyen.request;

import com.payment.adyen.model.payment.adyen.model.Recurring;

public class StoredPaymentsRequest extends AdyenRequest{

    private Recurring recurring;
    private String shopperReference;
    private String merchantAccount;

    public Recurring getRecurring() {
        return recurring;
    }

    public String getShopperReference() {
        return shopperReference;
    }

    public void setShopperReference(String shopperReference) {
        this.shopperReference = shopperReference;
    }

    public String getMerchantAccount() {
        return merchantAccount;
    }

    public void setMerchantAccount(String merchantAccount) {
        this.merchantAccount = merchantAccount;
    }

    public void setRecurring(Recurring recurring) {
        this.recurring = recurring;
    }
}
