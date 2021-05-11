package com.payment.adyen.converter.adyen;

import com.payment.adyen.config.AppConfiguration;
import com.payment.adyen.model.payment.adyen.request.CaptureRequest;
import com.payment.adyen.model.payment.graphql.CaptureRequestInput;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CaptureRequestInputToCaptureRequestConverter implements Converter<CaptureRequestInput, CaptureRequest> {

    @Override
    public CaptureRequest convert(CaptureRequestInput source) {
        CaptureRequest captureRequest = new CaptureRequest();
        captureRequest.setMerchantAccount(AppConfiguration.MERCHANT_ACCOUNT);
        captureRequest.setReference(UUID.randomUUID().toString());
        captureRequest.setOriginalReference(source.getOriginalReference());
        captureRequest.setModificationAmount(source.getModificationAmount());
        captureRequest.setPaymentId(source.getPaymentId());
        captureRequest.setIdempotencyKey(source.getUuid());
        return captureRequest;
    }
}
