package com.payment.adyen.service;

import com.payment.adyen.model.payment.adyen.model.PaymentInfo;
import com.payment.adyen.model.payment.adyen.model.StoredPaymentDetail;
import com.payment.adyen.model.payment.adyen.request.*;
import com.payment.adyen.model.payment.adyen.response.*;

import java.util.List;

public interface PaymentService {

    AuthorizationResponse authorize (BasicAuthorizationRequest basicAuthorizationRequest);

    AuthorizationResponse authorize3d (Authorization3DRequest authorization3DRequest);

    CaptureResponse capture (CaptureRequest captureRequest);

    CancelResponse cancel (CancelRequest cancelRequest);

    RefundResponse refund (RefundRequest refundRequest);

    List<PaymentInfo> getPaymentsInfo(int pageNumber);

    long getNumberOfPages();

    List<StoredPaymentDetail> getStoredPayments(StoredPaymentsRequest storedPaymentsRequest);

    PaymentInfo getLastPaymentInfoByOrderId(Integer orderId);

    PaymentInfo getPaymentByOrderId(Integer orderId);

    DisableRecurringResponse removeStoredPayment(DisableRecurringRequest disableRecurringRequest);

}
