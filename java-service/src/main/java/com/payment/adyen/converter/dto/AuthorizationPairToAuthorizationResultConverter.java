package com.payment.adyen.converter.dto;

import com.payment.adyen.model.meta.AmountInfo;
import com.payment.adyen.model.payment.dto.AuthorizationResult;
import com.payment.adyen.model.payment.adyen.model.pair.AuthorizationPair;
import com.payment.adyen.model.payment.adyen.request.AuthorizationRequest;
import com.payment.adyen.model.payment.adyen.request.BasicAuthorizationRequest;
import com.payment.adyen.model.payment.adyen.response.AuthorizationResponse;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class AuthorizationPairToAuthorizationResultConverter implements Converter<AuthorizationPair, AuthorizationResult> {

    private static final String REDIRECT_3DS_FLAG = "RedirectShopper";

    @Override
    public AuthorizationResult convert(AuthorizationPair authorizationPair) {
        ImmutablePair<AuthorizationRequest, AuthorizationResponse> pair = authorizationPair.getPair();
        AuthorizationRequest authorizationRequest = pair.getKey();
        AuthorizationResponse authorizationResponse = pair.getValue();

        var authorizationResult = new AuthorizationResult();
        authorizationResult.setAuthCode(authorizationResponse.getAuthCode());
        authorizationResult.setResultCode(authorizationResponse.getResultCode());
        authorizationResult.setAdditionalData(authorizationResponse.getAdditionalData());
        authorizationResult.setPspReference(authorizationResponse.getPspReference());
        authorizationResult.setPaymentId(ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE));

        // 3ds population
        if (REDIRECT_3DS_FLAG.equals(authorizationResponse.getResultCode())) {
            authorizationResult.setMd(authorizationResponse.getMd());
            authorizationResult.setIssuerUrl(authorizationResponse.getIssuerUrl());
            authorizationResult.setPaRequest(authorizationResponse.getPaRequest());
        }

        if (authorizationRequest instanceof BasicAuthorizationRequest) {
            var basicRequest = (BasicAuthorizationRequest) authorizationRequest;
            AmountInfo amount = basicRequest.getAmount();
            authorizationResult.setCurrency(amount.getCurrency());
            authorizationResult.setValue(amount.getValue());
        }

        return authorizationResult;
    }
}
