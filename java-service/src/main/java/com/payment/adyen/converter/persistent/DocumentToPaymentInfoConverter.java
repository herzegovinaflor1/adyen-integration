package com.payment.adyen.converter.persistent;

import com.payment.adyen.model.payment.adyen.model.PaymentInfo;
import org.bson.Document;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class DocumentToPaymentInfoConverter implements Converter<Document, PaymentInfo> {

    private static final DateTimeFormatter CURRENT_DATE_TIME_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
    private static final DateTimeFormatter CONVERTED_DATE_TIME_FORMAT =
            DateTimeFormatter.ofPattern("dd MMMM yyyy");

    @Override
    public PaymentInfo convert(Document source) {
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setPaymentId(source.getInteger("paymentId"));
        paymentInfo.setResultCode(source.getString("resultCode"));
        paymentInfo.setAuthCode(source.getString("authCode"));
        paymentInfo.setValue(source.getInteger("value"));
        paymentInfo.setCurrency(source.getString("currency"));

        paymentInfo.setPspReference(getListedFiled(source, "pspReference"));
        paymentInfo.setCreationDate(getFormattedDate(source));
        paymentInfo.setOperation(getListedFiled(source, "operation"));

        return paymentInfo;
    }

    private List<String> getListedFiled(Document source, String key) {
        if (source.get(key) instanceof Collection) {
            return source.getList(key, String.class);
        }
        return Collections.singletonList(source.getString(key));
    }

    private List<String> getFormattedDate(Document source){
        return getListedFiled(source, "creationDate").stream()
                .map(this::convertDate)
                .collect(Collectors.toList());
    }

    private String convertDate(String creationDate){
        return LocalDateTime.parse(creationDate, CURRENT_DATE_TIME_FORMAT)
                .format(CONVERTED_DATE_TIME_FORMAT);
    }
}
