package com.payment.adyen.model.payment.adyen.response;

public class CaptureResponse {

    private String pspReference;
    private String response;

    public String getPspReference() {
        return pspReference;
    }

    public void setPspReference(String pspReference) {
        this.pspReference = pspReference;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

}
