package com.payment.adyen.model.meta;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdditionalData {

    @JsonProperty("card.encrypted.json")
    private String cardEncryptedJson;
    @JsonProperty("threeds2.cardEnrolled")
    private String threeds2CardEnrolled;

    public static AdditionalData.Builder builder() {
        return new AdditionalData().new Builder();
    }

    public String getCardEncryptedJson() {
        return cardEncryptedJson;
    }

    public String getThreeds2CardEnrolled() {
        return threeds2CardEnrolled;
    }

    public class Builder {
        private Builder() {
        }

        public AdditionalData.Builder setCardEncryptedJson(String cardEncryptedJson) {
            AdditionalData.this.cardEncryptedJson = cardEncryptedJson;
            return this;
        }

        public AdditionalData.Builder setThreeds2CardEnrolled(String threeds2CardEnrolled) {
            AdditionalData.this.threeds2CardEnrolled = threeds2CardEnrolled;
            return this;
        }

        public AdditionalData build() {
            return AdditionalData.this;
        }
    }
}
