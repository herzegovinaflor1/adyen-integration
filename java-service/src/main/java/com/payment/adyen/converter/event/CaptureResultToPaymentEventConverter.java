package com.payment.adyen.converter.event;

import com.payment.adyen.model.event.PaymentEvent;
import com.payment.adyen.model.payment.dto.CaptureResult;
import com.payment.adyen.model.payment.adyen.model.Operation;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CaptureResultToPaymentEventConverter implements Converter<CaptureResult, PaymentEvent> {

    @Override
    public PaymentEvent convert(CaptureResult captureResult) {
        PaymentEvent paymentEvent = new PaymentEvent();
        paymentEvent.setPspReference(captureResult.getPspReference());
        paymentEvent.setResponse(captureResult.getResponse());
        paymentEvent.setOperation(Operation.CAPTURE.name());
        paymentEvent.setPaymentId(captureResult.getPaymentId());
        paymentEvent.setCreationDate(LocalDateTime.now().toString());
        return paymentEvent;
    }
}
