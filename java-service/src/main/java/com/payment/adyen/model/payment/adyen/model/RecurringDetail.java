package com.payment.adyen.model.payment.adyen.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.payment.adyen.model.meta.Card;

@JsonRootName("RecurringDetail")
public class RecurringDetail {

    private Card card;
    private String paymentMethodVariant;
    private String recurringDetailReference;

    public String getPaymentMethodVariant() {
        return paymentMethodVariant;
    }

    public void setPaymentMethodVariant(String paymentMethodVariant) {
        this.paymentMethodVariant = paymentMethodVariant;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public String getRecurringDetailReference() {
        return recurringDetailReference;
    }

    public void setRecurringDetailReference(String recurringDetailReference) {
        this.recurringDetailReference = recurringDetailReference;
    }

}
