package com.payment.adyen.exception;

public class ApiRequestNotValid extends RuntimeException {

    public ApiRequestNotValid(String message) {
        super(message);
    }

}
