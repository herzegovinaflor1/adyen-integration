package com.payment.adyen.model.payment.adyen.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.payment.adyen.config.AppConfiguration;
import com.payment.adyen.model.meta.AmountInfo;
import com.payment.adyen.model.meta.AdditionalData;
import com.payment.adyen.model.meta.BrowserInfo;
import com.payment.adyen.model.meta.Card;
import com.payment.adyen.model.payment.adyen.model.Recurring;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BasicAuthorizationRequest extends AuthorizationRequest {

    private Card card;
    private AdditionalData additionalData;
    private AmountInfo amount;
    private String reference;
    private String merchantAccount;

    private final String issuerUrl = AppConfiguration.ISSUE_URL;
    private final BrowserInfo browserInfo = BrowserInfo.builder().buildDefault();
    private final String shopperEmail = AppConfiguration.SHOPPER_EMAIL;
    private final String shopperIP = AppConfiguration.SHOPPER_IP;
    private final String shopperReference = AppConfiguration.SHOPPER_REFERENCE;

    private Recurring recurring;
    private String selectedRecurringDetailReference;
    private String shopperInteraction;

    public static Builder builder() {
        return new BasicAuthorizationRequest().new Builder();
    }

    public String getMerchantAccount() {
        return merchantAccount;
    }

    public String getReference() {
        return reference;
    }

    public AmountInfo getAmount() {
        return amount;
    }

    public AdditionalData getAdditionalData() {
        return additionalData;
    }

    public String getIssuerUrl() {
        return issuerUrl;
    }

    public BrowserInfo getBrowserInfo() {
        return browserInfo;
    }

    public String getShopperEmail() {
        return shopperEmail;
    }

    public String getShopperIP() {
        return shopperIP;
    }

    public String getShopperReference() {
        return shopperReference;
    }

    public Recurring getRecurring() {
        return recurring;
    }

    public Card getCard() {
        return card;
    }

    public String getSelectedRecurringDetailReference() {
        return selectedRecurringDetailReference;
    }

    public String getShopperInteraction() {
        return shopperInteraction;
    }

    public class Builder {
        private Builder() {
        }

        public Builder setIdempotencyKey(String uuid) {
            BasicAuthorizationRequest.super.setIdempotencyKey(uuid);
            return this;
        }

        public Builder setShopperInteraction(String shopperInteraction) {
            BasicAuthorizationRequest.this.shopperInteraction = shopperInteraction;
            return this;
        }

        public Builder setSelectedRecurringDetailReference(String selectedRecurringDetailReference) {
            BasicAuthorizationRequest.this.selectedRecurringDetailReference = selectedRecurringDetailReference;
            return this;
        }

        public Builder setCard(Card card) {
            BasicAuthorizationRequest.this.card = card;
            return this;
        }

        public Builder setAdditionalData(AdditionalData additionalData) {
            BasicAuthorizationRequest.this.additionalData = additionalData;
            return this;
        }

        public Builder setAmount(AmountInfo amount) {
            BasicAuthorizationRequest.this.amount = amount;
            return this;
        }

        public Builder setReference(String reference) {
            BasicAuthorizationRequest.this.reference = reference;
            return this;
        }

        public Builder setMerchantAccount(String merchantAccount) {
            BasicAuthorizationRequest.this.merchantAccount = merchantAccount;
            return this;
        }

        public Builder setRecurring(Recurring recurring) {
            BasicAuthorizationRequest.this.recurring = recurring;
            return this;
        }

        public BasicAuthorizationRequest build() {
            return BasicAuthorizationRequest.this;
        }
    }
}
