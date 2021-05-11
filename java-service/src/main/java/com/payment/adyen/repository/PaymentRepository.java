package com.payment.adyen.repository;

import com.payment.adyen.model.event.PaymentEvent;
import org.bson.Document;

import java.util.Optional;
import java.util.stream.Stream;

public interface PaymentRepository {

    Stream<Document> getAllPayments(int pageNumber);

    Stream<Document> getPaymentsByPaymentId(Integer orderId);

    void saveEvent(PaymentEvent paymentEvent);

    Optional<Document> getPaymentInfoByPaymentId(Integer orderId);

    long getNumberOfPages();
}
