package com.payment.adyen.model.payment.adyen.model.pair;

import com.payment.adyen.model.payment.adyen.request.AuthorizationRequest;
import com.payment.adyen.model.payment.adyen.response.AuthorizationResponse;
import org.apache.commons.lang3.tuple.ImmutablePair;

public final class AuthorizationPair {

    private final ImmutablePair<AuthorizationRequest, AuthorizationResponse> immutablePair;

    protected AuthorizationPair(ImmutablePair<AuthorizationRequest, AuthorizationResponse> immutablePair) {
        this.immutablePair = immutablePair;
    }

    public static AuthorizationPair of(AuthorizationRequest request, AuthorizationResponse response) {
        return new AuthorizationPair(ImmutablePair.of(request, response));
    }

    public ImmutablePair<AuthorizationRequest, AuthorizationResponse> getPair() {
        return immutablePair;
    }
}
