package com.payment.adyen.converter.adyen;

import com.payment.adyen.config.AppConfiguration;
import com.payment.adyen.model.meta.AmountInfo;
import com.payment.adyen.model.meta.Card;
import com.payment.adyen.model.payment.adyen.request.BasicAuthorizationRequest;
import com.payment.adyen.model.payment.adyen.model.Recurring;
import com.payment.adyen.model.payment.graphql.StoredAuthorizationRequestInput;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StoredAuthorizationRequestInputToAuthorizationRequestConverter implements Converter<StoredAuthorizationRequestInput, BasicAuthorizationRequest> {

    @Override
    public BasicAuthorizationRequest convert(StoredAuthorizationRequestInput source) {
        Card card = new Card();
        card.setCvc(source.getCvc());

        var amountInfo = AmountInfo.builder()
                .setCurrency(source.getCurrency())
                .setValue(source.getValue())
                .build();

        Recurring recurring = new Recurring();
        recurring.setContract("ONECLICK");

        return BasicAuthorizationRequest.builder()
                .setIdempotencyKey(source.getUuid())
                .setCard(card)
                .setAmount(amountInfo)
                .setSelectedRecurringDetailReference(source.getSelectedRecurringDetailReference())
                .setRecurring(recurring)
                .setShopperInteraction("Ecommerce")
                .setReference(UUID.randomUUID().toString())
                .setMerchantAccount(AppConfiguration.MERCHANT_ACCOUNT)
                .build();
    }
}
