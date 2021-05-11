package com.payment.adyen.converter.event;

import com.payment.adyen.model.event.PaymentEvent;
import com.payment.adyen.model.payment.dto.RefundResult;
import com.payment.adyen.model.payment.adyen.model.Operation;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RefundResultToPaymentEventConverter implements Converter<RefundResult, PaymentEvent> {

    @Override
    public PaymentEvent convert(RefundResult refundResult) {
        PaymentEvent paymentEvent = new PaymentEvent();
        paymentEvent.setPspReference(refundResult.getPspReference());
        paymentEvent.setResponse(refundResult.getResponse());
        paymentEvent.setPaymentId(refundResult.getPaymentId());
        paymentEvent.setOperation(Operation.REFUND.name());
        paymentEvent.setCreationDate(LocalDateTime.now().toString());
        return paymentEvent;
    }
}
