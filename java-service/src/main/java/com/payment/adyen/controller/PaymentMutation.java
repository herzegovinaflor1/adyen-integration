package com.payment.adyen.controller;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.payment.adyen.facade.PaymentFacade;
import com.payment.adyen.model.payment.dto.AuthorizationResult;
import com.payment.adyen.model.payment.dto.CancelResult;
import com.payment.adyen.model.payment.dto.CaptureResult;
import com.payment.adyen.model.payment.dto.RefundResult;
import com.payment.adyen.model.payment.adyen.response.*;
import com.payment.adyen.model.payment.graphql.*;
import org.springframework.stereotype.Component;

@Component
public class PaymentMutation implements GraphQLMutationResolver {

    private final PaymentFacade paymentFacade;

    public PaymentMutation(PaymentFacade paymentFacade) {
        this.paymentFacade = paymentFacade;
    }

    public AuthorizationResult authorize(AuthorizationRequestInput authorizeRequest) {
        return paymentFacade.authorize(authorizeRequest);
    }

    public AuthorizationResult authorizeStored(StoredAuthorizationRequestInput storedAuthorizationRequest) {
        return paymentFacade.authorizeStored(storedAuthorizationRequest);
    }

    public DisableRecurringResponse removeStoredPayment(DisableRecurringRequestInput disableRecurringRequest) {
        return paymentFacade.removeStoredPayment(disableRecurringRequest);
    }

    public CaptureResult capture(CaptureRequestInput captureRequestInput) {
        return paymentFacade.capture(captureRequestInput);
    }

    public CancelResult cancel(CancelRequestInput cancelRequestInput) {
        return paymentFacade.cancel(cancelRequestInput);
    }

    public RefundResult refund(RefundRequestInput refundRequestInput) {
        return paymentFacade.refund(refundRequestInput);
    }

}
