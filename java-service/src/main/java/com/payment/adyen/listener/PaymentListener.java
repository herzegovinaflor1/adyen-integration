package com.payment.adyen.listener;

import com.payment.adyen.model.event.PaymentEvent;

public interface PaymentListener {

    void savePaymentEvent(PaymentEvent paymentEvent);

}
