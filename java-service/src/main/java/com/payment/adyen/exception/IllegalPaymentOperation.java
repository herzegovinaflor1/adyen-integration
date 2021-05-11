package com.payment.adyen.exception;

public class IllegalPaymentOperation extends RuntimeException {

    public IllegalPaymentOperation(String message) {
        super(message);
    }

}
