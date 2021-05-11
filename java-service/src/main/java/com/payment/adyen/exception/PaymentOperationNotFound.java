package com.payment.adyen.exception;

public class PaymentOperationNotFound extends RuntimeException {

    public PaymentOperationNotFound(String message) {
        super(message);
    }

}
