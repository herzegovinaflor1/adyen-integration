package com.payment.adyen.converter.dto;

import com.payment.adyen.model.payment.adyen.model.pair.RefundPair;
import com.payment.adyen.model.payment.dto.RefundResult;
import com.payment.adyen.model.payment.adyen.request.RefundRequest;
import com.payment.adyen.model.payment.adyen.response.RefundResponse;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RefundPairToRefundResultConverter implements Converter<RefundPair, RefundResult> {

    @Override
    public RefundResult convert(RefundPair refundPair) {
        ImmutablePair<RefundRequest, RefundResponse> pair = refundPair.getPair();
        RefundRequest refundRequest = pair.getKey();
        RefundResponse refundResponse = pair.getValue();

        RefundResult refundResult = new RefundResult();
        refundResult.setPaymentId(refundRequest.getPaymentId());
        refundResult.setResponse(refundResponse.getResponse());
        refundResult.setPspReference(refundResponse.getPspReference());

        return refundResult;
    }
}
