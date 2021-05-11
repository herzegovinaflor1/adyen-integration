package com.payment.adyen.repository.impl;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.payment.adyen.model.event.PaymentEvent;
import com.payment.adyen.repository.PaymentRepository;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static com.mongodb.client.model.Accumulators.first;
import static com.mongodb.client.model.Accumulators.push;
import static com.mongodb.client.model.Aggregates.group;
import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Aggregates.skip;
import static com.mongodb.client.model.Aggregates.limit;
import static com.mongodb.client.model.Filters.eq;

@Repository
public class DefaultPaymentRepository implements PaymentRepository {

    private final ConversionService conversionService;
    private final MongoCollection<Document> paymentsCollection;

    public DefaultPaymentRepository(ConversionService conversionService, MongoCollection<Document> paymentsCollection) {
        this.conversionService = conversionService;
        this.paymentsCollection = paymentsCollection;
    }

    @Override
    public Stream<Document> getAllPayments(int pageNumber) {
        Bson groupBy = group("$paymentId",
                first("paymentId", "$paymentId"),
                first("authCode", "$authCode"),
                first("value", "$value"),
                first("currency", "$currency"),
                push("pspReference", "$pspReference"),
                push("operation", "$operation"),
                push("creationDate", "$creationDate")
        );

        var documents = paymentsCollection
                .aggregate(List.of(
                        groupBy,
                        skip(pageNumber * 10),
                        limit(10)
                ));
        return StreamSupport.stream(documents.spliterator(), false);
    }

    @Override
    public Stream<Document> getPaymentsByPaymentId(Integer paymentId) {
        Bson groupBy = group("$paymentId",
                first("paymentId", "$paymentId"),
                first("authCode", "$authCode"),
                first("value", "$value"),
                first("currency", "$currency"),
                push("pspReference", "$pspReference"),
                push("operation", "$operation"),
                push("creationDate", "$creationDate")
        );

        var documents = paymentsCollection.aggregate(List.of(
                groupBy,
                match(Filters.eq("paymentId", paymentId))
        ));
        return StreamSupport.stream(documents.spliterator(), false);
    }

    @Override
    public void saveEvent(PaymentEvent paymentEvent) {
        var event = conversionService.convert(paymentEvent, Document.class);
        paymentsCollection.insertOne(event);
    }

    @Override
    public Optional<Document> getPaymentInfoByPaymentId(Integer paymentId) {
        var orderedPayments = paymentsCollection.find(eq("paymentId", paymentId))
                .sort(Sorts.descending("creationDate"));
        return StreamSupport.stream(orderedPayments.spliterator(), false)
                .findFirst();
    }

    @Override
    public long getNumberOfPages() {
        return paymentsCollection.countDocuments();
    }
}
