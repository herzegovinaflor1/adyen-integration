package com.payment.adyen.listener.impl;

import com.payment.adyen.listener.PaymentListener;
import com.payment.adyen.model.event.PaymentEvent;
import com.payment.adyen.repository.PaymentRepository;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class DefaultPaymentListener implements PaymentListener {

    private final PaymentRepository paymentRepository;

    public DefaultPaymentListener(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    @EventListener
    @Async
    public void savePaymentEvent(PaymentEvent paymentEvent) {
        paymentRepository.saveEvent(paymentEvent);
    }

}
