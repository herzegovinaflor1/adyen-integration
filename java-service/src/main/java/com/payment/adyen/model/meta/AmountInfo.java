package com.payment.adyen.model.meta;

public class AmountInfo {

    private Integer value;
    private String currency;

    public String getCurrency() {
        return currency;
    }

    public Integer getValue() {
        return value;
    }

    public static AmountInfo.Builder builder() {
        return new AmountInfo().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public AmountInfo.Builder setCurrency(String currency) {
            AmountInfo.this.currency = currency;
            return this;
        }

        public AmountInfo.Builder setValue(Integer value) {
            AmountInfo.this.value = value;
            return this;
        }

        public AmountInfo build() {
            return AmountInfo.this;
        }
    }

}
