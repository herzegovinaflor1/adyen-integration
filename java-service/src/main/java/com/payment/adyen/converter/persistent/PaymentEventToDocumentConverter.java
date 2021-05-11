package com.payment.adyen.converter.persistent;

import com.payment.adyen.model.event.PaymentEvent;
import org.bson.Document;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PaymentEventToDocumentConverter implements Converter<PaymentEvent, Document> {
    @Override
    public Document convert(PaymentEvent paymentEvent) {
        Document document = new Document();
        putToDocument(document, "pspReference", paymentEvent.getPspReference());
        putToDocument(document, "value", paymentEvent.getValue());
        putToDocument(document, "authCode", paymentEvent.getAuthCode());
        putToDocument(document, "response", paymentEvent.getResponse());
        putToDocument(document, "creationDate", paymentEvent.getCreationDate());
        putToDocument(document, "currency", paymentEvent.getCurrency());
        putToDocument(document, "operation", paymentEvent.getOperation());
        putToDocument(document, "paymentId", paymentEvent.getPaymentId());
        putToDocument(document, "resultCode", paymentEvent.getResultCode());
        return document;
    }

    private void putToDocument(Document document, String key, Object value) {
        if (value != null) {
            document.put(key, value);
        }
    }
}
