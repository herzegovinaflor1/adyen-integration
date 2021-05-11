package com.payment.adyen.converter.dto;

import com.payment.adyen.model.payment.adyen.model.pair.CapturePair;
import com.payment.adyen.model.payment.dto.CaptureResult;
import com.payment.adyen.model.payment.adyen.request.CaptureRequest;
import com.payment.adyen.model.payment.adyen.response.CaptureResponse;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CapturePairToCaptureResultConverter implements Converter<CapturePair, CaptureResult> {

    @Override
    public CaptureResult convert(CapturePair capturePair) {
        ImmutablePair<CaptureRequest, CaptureResponse> captureInfo = capturePair.getPair();
        CaptureRequest captureRequest = captureInfo.getKey();
        CaptureResponse captureResponse = captureInfo.getValue();

        CaptureResult captureResult = new CaptureResult();
        captureResult.setPaymentId(captureRequest.getPaymentId());
        captureResult.setPspReference(captureResponse.getPspReference());
        captureResult.setResponse(captureResponse.getResponse());

        return captureResult;
    }
}
