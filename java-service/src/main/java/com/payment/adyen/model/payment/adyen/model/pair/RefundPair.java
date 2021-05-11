package com.payment.adyen.model.payment.adyen.model.pair;

import com.payment.adyen.model.payment.adyen.request.RefundRequest;
import com.payment.adyen.model.payment.adyen.response.RefundResponse;
import org.apache.commons.lang3.tuple.ImmutablePair;

public final class RefundPair {

    private final ImmutablePair<RefundRequest, RefundResponse> immutablePair;

    protected RefundPair(ImmutablePair<RefundRequest, RefundResponse> immutablePair) {
        this.immutablePair = immutablePair;
    }

    public static RefundPair of(RefundRequest request, RefundResponse response) {
        return new RefundPair(ImmutablePair.of(request, response));
    }

    public ImmutablePair<RefundRequest, RefundResponse> getPair() {
        return immutablePair;
    }
}
