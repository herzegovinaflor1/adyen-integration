package com.payment.adyen.model.payment.adyen.model.pair;

import com.payment.adyen.model.payment.adyen.request.CancelRequest;
import com.payment.adyen.model.payment.adyen.response.CancelResponse;
import org.apache.commons.lang3.tuple.ImmutablePair;

public final class CancelPair {

    private final ImmutablePair<CancelRequest, CancelResponse> immutablePair;

    protected CancelPair(ImmutablePair<CancelRequest, CancelResponse> immutablePair) {
        this.immutablePair = immutablePair;
    }

    public static CancelPair of(CancelRequest request, CancelResponse response) {
        return new CancelPair(ImmutablePair.of(request, response));
    }

    public ImmutablePair<CancelRequest, CancelResponse> getPair() {
        return immutablePair;
    }
}
