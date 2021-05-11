package com.payment.adyen.service.impl;

import com.payment.adyen.exception.IllegalPaymentOperation;
import com.payment.adyen.exception.PaymentOperationNotFound;
import com.payment.adyen.model.payment.adyen.model.Operation;
import com.payment.adyen.model.payment.adyen.model.PaymentInfo;
import com.payment.adyen.model.payment.adyen.model.StoredPaymentDetail;
import com.payment.adyen.model.payment.adyen.request.*;
import com.payment.adyen.model.payment.adyen.response.*;
import com.payment.adyen.repository.PaymentRepository;
import com.payment.adyen.service.PaymentApiClientService;
import com.payment.adyen.service.PaymentService;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DefaultPaymentService implements PaymentService {

    private static final long PAGE_CAPACITY = 10;

    private final PaymentRepository paymentRepository;
    private final ConversionService conversionService;
    private final PaymentApiClientService paymentApiClientService;

    public DefaultPaymentService(PaymentRepository paymentRepository, @Lazy ConversionService conversionService, PaymentApiClientService paymentApiClientService) {
        this.paymentRepository = paymentRepository;
        this.conversionService = conversionService;
        this.paymentApiClientService = paymentApiClientService;
    }

    @Override
    public AuthorizationResponse authorize(BasicAuthorizationRequest basicAuthorizationRequest) {
        return paymentApiClientService.authorize(basicAuthorizationRequest);
    }

    @Override
    public AuthorizationResponse authorize3d(Authorization3DRequest authorization3DRequest) {
        return paymentApiClientService.authorize3d(authorization3DRequest);
    }

    @Override
    public CaptureResponse capture(CaptureRequest captureRequest) {
        if (isAuthorized(captureRequest.getPaymentId())) {
            return paymentApiClientService.capture(captureRequest);
        }
        throw new IllegalPaymentOperation("Capture operation is not applicable. Make sure you have appropriate payment status");
    }

    @Override
    public CancelResponse cancel(CancelRequest cancelRequest) {
        if (isAuthorized(cancelRequest.getPaymentId())) {
            return paymentApiClientService.cancel(cancelRequest);
        }
        throw new IllegalPaymentOperation("Cancel operation is not applicable. Make sure you have appropriate payment status");
    }

    @Override
    public RefundResponse refund(RefundRequest refundRequest) {
        if (isCaptured(refundRequest.getPaymentId())) {
            return paymentApiClientService.refund(refundRequest);
        }
        throw new IllegalPaymentOperation("Refund operation is not applicable. Make sure you have appropriate payment status");
    }

    @Override
    public List<PaymentInfo> getPaymentsInfo(int pageNumber) {
        return paymentRepository.getAllPayments(pageNumber)
                .map(document -> conversionService.convert(document, PaymentInfo.class))
                .collect(Collectors.toList());
    }

    @Override
    public long getNumberOfPages() {
        long recordNumber = paymentRepository.getNumberOfPages();
        return (recordNumber + PAGE_CAPACITY - 1) / PAGE_CAPACITY;
    }

    @Override
    public List<StoredPaymentDetail> getStoredPayments(StoredPaymentsRequest storedPaymentsRequest) {
        return paymentApiClientService.getStoredPayments(storedPaymentsRequest);
    }

    @Override
    public PaymentInfo getLastPaymentInfoByOrderId(Integer orderId) {
        var payment = paymentRepository.getPaymentInfoByPaymentId(orderId);
        return payment.map(paymentDocument -> conversionService.convert(paymentDocument, PaymentInfo.class))
                .orElseThrow(() -> new PaymentOperationNotFound("Order id " + orderId + " does not exist"));
    }

    @Override
    public PaymentInfo getPaymentByOrderId(Integer orderId) {
        return paymentRepository.getPaymentsByPaymentId(orderId)
                .map(document -> conversionService.convert(document, PaymentInfo.class))
                .findFirst()
                .orElse(null);
    }

    @Override
    public DisableRecurringResponse removeStoredPayment(DisableRecurringRequest disableRecurringRequest) {
        return paymentApiClientService.disableStoredPayment(disableRecurringRequest);
    }

    // TODO: refactor
    private boolean isAuthorized(Integer orderId) {
        var paymentInfoByOrderId = getLastPaymentInfoByOrderId(orderId);
        return paymentInfoByOrderId.getOperation().stream().reduce((_ignore, last) -> last)
                .map(lastOperation -> lastOperation.equals(Operation.AUTHORIZATION.name()))
                .orElse(false);
    }

    private boolean isCaptured(Integer orderId) {
        var paymentInfoByOrderId = getLastPaymentInfoByOrderId(orderId);
        return paymentInfoByOrderId.getOperation().stream().reduce((_ignore, last) -> last)
                .map(lastOperation -> lastOperation.equals(Operation.CAPTURE.name()))
                .orElse(false);
    }
    ///########

}
