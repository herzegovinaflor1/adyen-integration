package com.payment.adyen.controller;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.payment.adyen.facade.PaymentFacade;
import com.payment.adyen.model.payment.adyen.model.PaymentInfo;
import com.payment.adyen.model.payment.adyen.model.StoredPaymentDetail;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentQuery implements GraphQLQueryResolver {

    private final PaymentFacade paymentFacade;

    public PaymentQuery(PaymentFacade paymentFacade) {
        this.paymentFacade = paymentFacade;
    }

    public List<PaymentInfo> paymentsInfo(int pageNumber) {
        return paymentFacade.getPaymentsInfo(pageNumber);
    }

    public List<StoredPaymentDetail> storedPayments() {
        return paymentFacade.getStoredPayments();
    }

    public int pages() {
        return (int) paymentFacade.getNumberOfPages();
    }

}
