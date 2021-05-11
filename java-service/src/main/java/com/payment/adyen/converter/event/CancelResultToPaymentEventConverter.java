package com.payment.adyen.converter.event;

import com.payment.adyen.model.event.PaymentEvent;
import com.payment.adyen.model.payment.dto.CancelResult;
import com.payment.adyen.model.payment.adyen.model.Operation;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CancelResultToPaymentEventConverter implements Converter<CancelResult, PaymentEvent> {

    @Override
    public PaymentEvent convert(CancelResult cancelResult) {
        PaymentEvent paymentEvent = new PaymentEvent();
        paymentEvent.setPaymentId(cancelResult.getPaymentId());
        paymentEvent.setResponse(cancelResult.getResponse());
        paymentEvent.setPspReference(cancelResult.getPspReference());
        paymentEvent.setCreationDate(LocalDateTime.now().toString());
        paymentEvent.setOperation(Operation.CANCELLATION.name());
        return paymentEvent;
    }
}
