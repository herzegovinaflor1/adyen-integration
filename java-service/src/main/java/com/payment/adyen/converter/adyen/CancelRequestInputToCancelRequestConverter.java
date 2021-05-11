package com.payment.adyen.converter.adyen;

import com.payment.adyen.config.AppConfiguration;
import com.payment.adyen.model.payment.adyen.request.CancelRequest;
import com.payment.adyen.model.payment.graphql.CancelRequestInput;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CancelRequestInputToCancelRequestConverter implements Converter<CancelRequestInput, CancelRequest> {

    @Override
    public CancelRequest convert(CancelRequestInput cancelRequestInput) {
        CancelRequest cancelRequest = new CancelRequest();
        cancelRequest.setMerchantAccount(AppConfiguration.MERCHANT_ACCOUNT);
        cancelRequest.setReference(UUID.randomUUID().toString());
        cancelRequest.setOriginalReference(cancelRequestInput.getOriginalReference());
        cancelRequest.setPaymentId(cancelRequestInput.getPaymentId());
        cancelRequest.setIdempotencyKey(cancelRequestInput.getUuid());
        return cancelRequest;
    }
}
