package com.payment.adyen.converter.dto;

import com.payment.adyen.model.payment.adyen.model.pair.CancelPair;
import com.payment.adyen.model.payment.dto.CancelResult;
import com.payment.adyen.model.payment.adyen.request.CancelRequest;
import com.payment.adyen.model.payment.adyen.response.CancelResponse;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CancelPairToCancelResultConverter implements Converter<CancelPair, CancelResult> {

    @Override
    public CancelResult convert(CancelPair cancelPair) {
        ImmutablePair<CancelRequest, CancelResponse> pair = cancelPair.getPair();
        CancelRequest cancelRequest = pair.getKey();
        CancelResponse cancelResponse = pair.getValue();

        CancelResult cancelResult = new CancelResult();
        cancelResult.setResponse(cancelResponse.getResponse());
        cancelResult.setPspReference(cancelResponse.getPspReference());
        cancelResult.setPaymentId(cancelRequest.getPaymentId());

        return cancelResult;
    }
}
