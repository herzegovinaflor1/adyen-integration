package com.payment.adyen.converter.adyen;

import com.payment.adyen.config.AppConfiguration;
import com.payment.adyen.model.payment.adyen.model.ModificationAmount;
import com.payment.adyen.model.payment.adyen.model.PaymentInfo;
import com.payment.adyen.model.payment.adyen.request.RefundRequest;
import com.payment.adyen.model.payment.graphql.RefundRequestInput;
import com.payment.adyen.service.PaymentService;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RefundRequestInputToRefundRequestConverter implements Converter<RefundRequestInput, RefundRequest> {

    private final PaymentService paymentService;

    public RefundRequestInputToRefundRequestConverter(@Lazy PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public RefundRequest convert(RefundRequestInput refundRequestInput) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setMerchantAccount(AppConfiguration.MERCHANT_ACCOUNT);
        refundRequest.setReference(UUID.randomUUID().toString());
        refundRequest.setOriginalReference(refundRequestInput.getOriginalReference());
        refundRequest.setPaymentId(refundRequestInput.getPaymentId());
        refundRequest.setIdempotencyKey(refundRequestInput.getUuid());

        PaymentInfo paymentInfoByOrderId = paymentService.getPaymentByOrderId(refundRequestInput.getPaymentId());

        ModificationAmount modificationAmount = new ModificationAmount();
        modificationAmount.setCurrency(paymentInfoByOrderId.getCurrency());
        modificationAmount.setValue(paymentInfoByOrderId.getValue());

        refundRequest.setModificationAmount(modificationAmount);
        return refundRequest;
    }

}
