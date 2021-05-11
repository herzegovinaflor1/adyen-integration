package com.payment.adyen.facade;

import com.payment.adyen.model.payment.adyen.model.*;
import com.payment.adyen.model.payment.adyen.request.Authorization3DRequest;
import com.payment.adyen.model.payment.adyen.response.*;
import com.payment.adyen.model.payment.dto.AuthorizationResult;
import com.payment.adyen.model.payment.dto.CancelResult;
import com.payment.adyen.model.payment.dto.CaptureResult;
import com.payment.adyen.model.payment.dto.RefundResult;
import com.payment.adyen.model.payment.graphql.*;

import java.util.List;

public interface PaymentFacade {

    AuthorizationResult authorize (AuthorizationRequestInput authorizationRequest);

    AuthorizationResult authorizeStored (StoredAuthorizationRequestInput storedAuthorizationRequestInput);

    AuthorizationResult authorize3d (Authorization3DRequest authorization3DRequest);

    CaptureResult capture (CaptureRequestInput captureRequestInput);

    CancelResult cancel (CancelRequestInput cancelRequestInput);

    RefundResult refund (RefundRequestInput refundRequestInput);

    List<PaymentInfo> getPaymentsInfo(int pageNumber);

    long getNumberOfPages();

    List<StoredPaymentDetail> getStoredPayments();

    DisableRecurringResponse removeStoredPayment(DisableRecurringRequestInput disableRecurringRequestInput);

}
