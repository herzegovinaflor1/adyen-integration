package com.payment.adyen.service.impl;

import com.payment.adyen.config.AdyenEndpoints;
import com.payment.adyen.config.AppConfiguration;
import com.payment.adyen.model.meta.Card;
import com.payment.adyen.model.payment.adyen.model.StoredPaymentDetail;
import com.payment.adyen.model.payment.adyen.request.*;
import com.payment.adyen.model.payment.adyen.response.*;
import com.payment.adyen.model.payment.api.ApiRequest;
import com.payment.adyen.service.PaymentApiClientService;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DefaultPaymentApiClientService implements PaymentApiClientService {

    private final RestTemplate restTemplate;

    public DefaultPaymentApiClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public AuthorizationResponse authorize(BasicAuthorizationRequest basicAuthorizationRequest) {
        var entity = createHttpEntity(basicAuthorizationRequest);
        return executeApiRequest(ApiRequest.of(entity, AuthorizationResponse.class, AdyenEndpoints.AUTHORIZATION));
    }

    @Override
    public AuthorizationResponse authorize3d(Authorization3DRequest authorization3DRequest) {
        var entity = createHttpEntity(authorization3DRequest);
        return executeApiRequest(ApiRequest.of(entity, AuthorizationResponse.class, AdyenEndpoints.AUTHORIZATION_3D));
    }

    @Override
    public CaptureResponse capture(CaptureRequest captureRequest) {
        var entity = createHttpEntity(captureRequest);
        return executeApiRequest(ApiRequest.of(entity, CaptureResponse.class, AdyenEndpoints.CAPTURE));
    }

    @Override
    public CancelResponse cancel(CancelRequest cancelRequest) {
        var entity = createHttpEntity(cancelRequest);
        return executeApiRequest(ApiRequest.of(entity, CancelResponse.class, AdyenEndpoints.CANCEL));
    }

    @Override
    public RefundResponse refund(RefundRequest refundRequest) {
        var entity = createHttpEntity(refundRequest);
        return executeApiRequest(ApiRequest.of(entity, RefundResponse.class, AdyenEndpoints.REFUND));
    }

    @Override
    public List<StoredPaymentDetail> getStoredPayments(StoredPaymentsRequest storedPaymentsRequest) {
        var entity = createHttpEntity(storedPaymentsRequest);
        var storedPayments = (List) executeApiRequest(ApiRequest.of(entity, Map.class, AdyenEndpoints.RECURRING_PAYMENTS))
                .get("details");

        if (CollectionUtils.isEmpty(storedPayments)) {
            return Collections.emptyList();
        }
        // TODO: move to converter
        return (List<StoredPaymentDetail>) storedPayments.stream()
                .map(val -> ((LinkedHashMap) val).get("RecurringDetail"))
                .map(val -> {
                    var obj = ((LinkedHashMap<String, Object>) val);
                    var cardObj = (LinkedHashMap<String, String>) obj.get("card");
                    var card = new Card();
                    card.setExpiryMonth(cardObj.get("expiryMonth"));
                    card.setExpiryYear(cardObj.get("expiryYear"));
                    card.setHolderName(cardObj.get("holderName"));
                    card.setNumber(cardObj.get("number"));

                    StoredPaymentDetail storedPaymentDetail = new StoredPaymentDetail();
                    storedPaymentDetail.setCard(card);
                    storedPaymentDetail.setRecurringDetailReference((String) obj.get("recurringDetailReference"));
                    storedPaymentDetail.setPaymentMethodVariant((String) obj.get("paymentMethodVariant"));

                    return storedPaymentDetail;
                })
                .collect(Collectors.toList());
    }

    @Override
    public DisableRecurringResponse disableStoredPayment(DisableRecurringRequest disableRecurringRequest) {
        var entity = createHttpEntity(disableRecurringRequest);
        return executeApiRequest(ApiRequest.of(entity, DisableRecurringResponse.class, AdyenEndpoints.DISABLE_RECURRING_PAYMENTS));
    }

    private <T extends AdyenRequest> HttpEntity<T> createHttpEntity(T entity) {
        var headers = new HttpHeaders();
        headers.set("X-API-Key", AppConfiguration.X_API_KEY);
        if (entity.getIdempotencyKey() != null) {
            headers.set("Idempotency-Key", entity.getIdempotencyKey());
        }
        return new HttpEntity<>(entity, headers);
    }

    private <T, R> R executeApiRequest(ApiRequest<T, R> request) {
        var circuitBreakerRegistry
                = CircuitBreakerRegistry.ofDefaults();
        var api = circuitBreakerRegistry.circuitBreaker("api");
        return api.decorateSupplier(() -> restTemplate.postForEntity(request.getUrl(), request.getRequest(), request.getResponse()))
                .get()
                .getBody();
    }
}
