package com.payment.adyen.converter.adyen;

import com.payment.adyen.config.AppConfiguration;
import com.payment.adyen.model.meta.AdditionalData;
import com.payment.adyen.model.meta.AmountInfo;
import com.payment.adyen.model.payment.adyen.request.BasicAuthorizationRequest;
import com.payment.adyen.model.payment.adyen.model.Recurring;
import com.payment.adyen.model.payment.graphql.AuthorizationRequestInput;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AuthorizationRequestInputToAuthorizationRequestConverter implements Converter<AuthorizationRequestInput, BasicAuthorizationRequest> {

    @Override
    public BasicAuthorizationRequest convert(AuthorizationRequestInput source) {
        var additionalData = AdditionalData.builder()
                .setCardEncryptedJson(source.getCardEncryptedJson())
                .build();
        var amountInfo = AmountInfo.builder()
                .currency(source.getCurrency())
                .value(source.getValue())
                .build();


        Recurring recurring = null;
        if(source.getStored()) {
            recurring = new Recurring();
            recurring.setContract("RECURRING,ONECLICK");
        }

        return BasicAuthorizationRequest.builder()
                .setIdempotencyKey(source.getUuid())
                .setAdditionalData(additionalData)
                .setReference(UUID.randomUUID().toString())
                .setMerchantAccount(AppConfiguration.MERCHANT_ACCOUNT)
                .setAmount(amountInfo)
                .setRecurring(recurring)
                .build();
    }
}
