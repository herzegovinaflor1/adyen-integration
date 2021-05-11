package com.payment.adyen.service.impl;

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
import org.springframework.http.ResponseEntity;
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

    // TODO: MOVE ROOT AND VERSION TO PROPERTIES
    private static final String AUTH_URL = "https://pal-test.adyen.com/pal/servlet/Payment/v64/authorise";
    private static final String AUTH_3D_URL = "https://pal-test.adyen.com/pal/servlet/Payment/v64/authorise3d";
    private static final String CAPTURE_URL = "https://pal-test.adyen.com/pal/servlet/Payment/v64/capture";
    private static final String CANCEL_URL = "https://pal-test.adyen.com/pal/servlet/Payment/v64/cancel";
    private static final String REFUND_URL = "https://pal-test.adyen.com/pal/servlet/Payment/v64/refund";
    private static final String RECURRING_PAYMENTS_URL = "https://pal-test.adyen.com/pal/servlet/Recurring/v49/listRecurringDetails";
    private static final String DISABLE_RECURRING_PAYMENTS_URL = "https://pal-test.adyen.com/pal/servlet/Recurring/v49/disable";

    private final RestTemplate restTemplate;

    public DefaultPaymentApiClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public AuthorizationResponse authorize(BasicAuthorizationRequest basicAuthorizationRequest) {
        var entity = createHttpEntity(basicAuthorizationRequest);
        var authorizationResponseEntity = executeApiRequest(ApiRequest.of(entity, AuthorizationResponse.class, AUTH_URL));
        return authorizationResponseEntity.getBody();
    }

    @Override
    public AuthorizationResponse authorize3d(Authorization3DRequest authorization3DRequest) {
        var entity = createHttpEntity(authorization3DRequest);
        var authorizationResponseEntity = executeApiRequest(ApiRequest.of(entity, AuthorizationResponse.class, AUTH_3D_URL));
        return authorizationResponseEntity.getBody();
    }

    @Override
    public CaptureResponse capture(CaptureRequest captureRequest) {
        var entity = createHttpEntity(captureRequest);
        var captureResponseEntity = executeApiRequest(ApiRequest.of(entity, CaptureResponse.class, CAPTURE_URL));
        return captureResponseEntity.getBody();
    }

    @Override
    public CancelResponse cancel(CancelRequest cancelRequest) {
        var entity = createHttpEntity(cancelRequest);
        var cancelResponseEntity = executeApiRequest(ApiRequest.of(entity, CancelResponse.class, CANCEL_URL));
        return cancelResponseEntity.getBody();
    }

    @Override
    public RefundResponse refund(RefundRequest refundRequest) {
        var entity = createHttpEntity(refundRequest);
        var refundResultResponseEntity = executeApiRequest(ApiRequest.of(entity, RefundResponse.class, REFUND_URL));
        return refundResultResponseEntity.getBody();
    }

    @Override
    public List<StoredPaymentDetail> getStoredPayments(StoredPaymentsRequest storedPaymentsRequest) {
        var entity = createHttpEntity(storedPaymentsRequest);
        var storedPayments = (List) restTemplate.postForEntity(RECURRING_PAYMENTS_URL, entity, Map.class).getBody().get("details");

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
        var captureResultResponseEntity = restTemplate.postForEntity(DISABLE_RECURRING_PAYMENTS_URL, entity, DisableRecurringResponse.class);
        return captureResultResponseEntity.getBody();
    }

    private <T extends AdyenRequest> HttpEntity<T> createHttpEntity(T entity) {
        var headers = new HttpHeaders();
        headers.set("X-API-Key", AppConfiguration.X_API_KEY);
        if(entity.getIdempotencyKey() != null){
            headers.set("Idempotency-Key", entity.getIdempotencyKey());
        }
        return new HttpEntity<>(entity, headers);
    }

    private <T, R> ResponseEntity<R> executeApiRequest(ApiRequest<T, R> request) {
        var circuitBreakerRegistry
                = CircuitBreakerRegistry.ofDefaults();
        var api = circuitBreakerRegistry.circuitBreaker("api");
        return api.decorateSupplier(() -> restTemplate.postForEntity(request.getUrl(), request.getRequest(), request.getResponse()))
                .get();
    }
}
