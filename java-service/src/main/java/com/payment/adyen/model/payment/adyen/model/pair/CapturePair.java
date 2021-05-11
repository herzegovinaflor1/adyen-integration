package com.payment.adyen.model.payment.adyen.model.pair;

import com.payment.adyen.model.payment.adyen.request.CaptureRequest;
import com.payment.adyen.model.payment.adyen.response.CaptureResponse;
import org.apache.commons.lang3.tuple.ImmutablePair;

public final class CapturePair {

    private final ImmutablePair<CaptureRequest, CaptureResponse> immutablePair;

    protected CapturePair(ImmutablePair<CaptureRequest, CaptureResponse> immutablePair) {
        this.immutablePair = immutablePair;
    }

    public static CapturePair of(CaptureRequest request, CaptureResponse response){
        return new CapturePair(ImmutablePair.of(request, response));
    }

    public ImmutablePair<CaptureRequest, CaptureResponse> getPair() {
        return immutablePair;
    }
}
