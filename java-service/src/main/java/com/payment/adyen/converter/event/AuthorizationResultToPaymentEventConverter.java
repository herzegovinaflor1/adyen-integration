package com.payment.adyen.converter.event;

import com.payment.adyen.model.event.PaymentEvent;
import com.payment.adyen.model.payment.adyen.model.Operation;
import com.payment.adyen.model.payment.dto.AuthorizationResult;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AuthorizationResultToPaymentEventConverter implements Converter<AuthorizationResult, PaymentEvent> {

    @Override
    public PaymentEvent convert(AuthorizationResult authorizationResult) {
        PaymentEvent paymentEvent = new PaymentEvent();
        paymentEvent.setPspReference(authorizationResult.getPspReference());
        paymentEvent.setResultCode(authorizationResult.getResultCode());
        paymentEvent.setAuthCode(authorizationResult.getAuthCode());
        paymentEvent.setValue(authorizationResult.getValue());
        paymentEvent.setCurrency(authorizationResult.getCurrency());
        paymentEvent.setCreationDate(LocalDateTime.now().toString());
        paymentEvent.setOperation(Operation.AUTHORIZATION.name());
        paymentEvent.setPaymentId(authorizationResult.getPaymentId());
        return paymentEvent;
    }
}
