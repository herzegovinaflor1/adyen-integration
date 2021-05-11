package com.payment.adyen.model.payment.adyen.request;

public class Authorization3DRequest extends AuthorizationRequest {

    private String shopperIP;
    private String merchantAccount;
    private String md;
    private String paResponse;

    public static Builder builder() {
        return new Authorization3DRequest().new Builder();
    }

    public String getMerchantAccount() {
        return merchantAccount;
    }

    public String getPaResponse() {
        return paResponse;
    }

    public String getMd() {
        return md;
    }

    public String getShopperIP() {
        return shopperIP;
    }

    public class Builder {
        private Builder() {
        }

        public Builder setMerchantAccount(String merchantAccount) {
            Authorization3DRequest.this.merchantAccount = merchantAccount;
            return this;
        }

        public Builder setPaResponse(String paResponse) {
            Authorization3DRequest.this.paResponse = paResponse;
            return this;
        }

        public Builder setMd(String md) {
            Authorization3DRequest.this.md = md;
            return this;
        }

        public Builder setShopperIP(String shopperIP) {
            Authorization3DRequest.this.shopperIP = shopperIP;
            return this;
        }

        public Authorization3DRequest build() {
            return Authorization3DRequest.this;
        }
    }
}
