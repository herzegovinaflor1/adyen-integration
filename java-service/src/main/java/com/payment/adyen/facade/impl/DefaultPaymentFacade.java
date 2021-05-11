package com.payment.adyen.facade.impl;

import com.payment.adyen.config.AppConfiguration;
import com.payment.adyen.facade.PaymentFacade;
import com.payment.adyen.model.event.PaymentEvent;
import com.payment.adyen.model.payment.adyen.model.*;
import com.payment.adyen.model.payment.adyen.model.pair.AuthorizationPair;
import com.payment.adyen.model.payment.adyen.model.pair.CancelPair;
import com.payment.adyen.model.payment.adyen.model.pair.CapturePair;
import com.payment.adyen.model.payment.adyen.model.pair.RefundPair;
import com.payment.adyen.model.payment.adyen.request.*;
import com.payment.adyen.model.payment.adyen.response.*;
import com.payment.adyen.model.payment.dto.AuthorizationResult;
import com.payment.adyen.model.payment.dto.CancelResult;
import com.payment.adyen.model.payment.dto.CaptureResult;
import com.payment.adyen.model.payment.dto.RefundResult;
import com.payment.adyen.model.payment.graphql.*;
import com.payment.adyen.service.PaymentService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultPaymentFacade implements PaymentFacade {

    private final PaymentService paymentService;
    private final ConversionService conversionService;
    private final ApplicationEventPublisher eventPublisher;

    public DefaultPaymentFacade(PaymentService paymentService, ConversionService conversionService, ApplicationEventPublisher eventPublisher) {
        this.paymentService = paymentService;
        this.conversionService = conversionService;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public AuthorizationResult authorize(AuthorizationRequestInput authorizationRequestInput) {
        var authorizationRequest = conversionService.convert(authorizationRequestInput, BasicAuthorizationRequest.class);
        var authorizationResponse = paymentService.authorize(authorizationRequest);

        var authorizationResult = conversionService.convert(AuthorizationPair.of(authorizationRequest, authorizationResponse), AuthorizationResult.class);
        savePaymentEvent(authorizationResult);

        return authorizationResult;
    }

    @Override
    public AuthorizationResult authorizeStored(StoredAuthorizationRequestInput storedAuthorizationRequestInput) {
        var authorizationRequest = conversionService.convert(storedAuthorizationRequestInput, BasicAuthorizationRequest.class);
        var authorizationResponse = paymentService.authorize(authorizationRequest);

        var authorizationResult = conversionService.convert(AuthorizationPair.of(authorizationRequest, authorizationResponse), AuthorizationResult.class);
        savePaymentEvent(authorizationResult);

        return authorizationResult;
    }

    @Override
    public AuthorizationResult authorize3d(Authorization3DRequest authorizationRequestInput) {
        var authorizationResponse = paymentService.authorize3d(authorizationRequestInput);
        var authorizationResult = conversionService.convert(AuthorizationPair.of(authorizationRequestInput, authorizationResponse), AuthorizationResult.class);

        savePaymentEvent(authorizationResult);

        return authorizationResult;
    }

    @Override
    public CaptureResult capture(CaptureRequestInput captureRequestInput) {
        var captureRequest = conversionService.convert(captureRequestInput, CaptureRequest.class);
        var captureResponse = paymentService.capture(captureRequest);

        var captureResult = conversionService.convert(CapturePair.of(captureRequest, captureResponse), CaptureResult.class);
        savePaymentEvent(captureResult);

        return captureResult;
    }

    @Override
    public CancelResult cancel(CancelRequestInput cancelRequestInput) {
        var cancelRequest = conversionService.convert(cancelRequestInput, CancelRequest.class);
        var cancelResponse = paymentService.cancel(cancelRequest);

        var cancelResult = conversionService.convert(CancelPair.of(cancelRequest, cancelResponse), CancelResult.class);
        savePaymentEvent(cancelResult);

        return cancelResult;
    }

    @Override
    public RefundResult refund(RefundRequestInput refundRequestInput) {
        var refundRequest = conversionService.convert(refundRequestInput, RefundRequest.class);
        var refundResponse = paymentService.refund(refundRequest);

        var refundResult = conversionService.convert(RefundPair.of(refundRequest, refundResponse), RefundResult.class);
        savePaymentEvent(refundResult);

        return refundResult;
    }

    @Override
    public List<PaymentInfo> getPaymentsInfo(int pageNumber) {
        return paymentService.getPaymentsInfo(pageNumber);
    }

    @Override
    public long getNumberOfPages() {
        return paymentService.getNumberOfPages();
    }

    @Override
    public List<StoredPaymentDetail> getStoredPayments() {
        var recurring = new Recurring();
        recurring.setContract("RECURRING");
        var storedPaymentsRequest = new StoredPaymentsRequest();
        storedPaymentsRequest.setRecurring(recurring);
        storedPaymentsRequest.setMerchantAccount(AppConfiguration.MERCHANT_ACCOUNT);
        storedPaymentsRequest.setShopperReference(AppConfiguration.SHOPPER_REFERENCE);
        return paymentService.getStoredPayments(storedPaymentsRequest);
    }

    @Override
    public DisableRecurringResponse removeStoredPayment(DisableRecurringRequestInput disableRecurringRequestInput) {
        var disableRecurringRequest = conversionService.convert(disableRecurringRequestInput, DisableRecurringRequest.class);
        return paymentService.removeStoredPayment(disableRecurringRequest);
    }

    private <T> void savePaymentEvent(T paymentResult) {
        var paymentEvent = conversionService.convert(paymentResult, PaymentEvent.class);
        eventPublisher.publishEvent(paymentEvent);
    }
}
