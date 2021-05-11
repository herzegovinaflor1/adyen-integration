package com.payment.adyen.converter.adyen;

import com.payment.adyen.config.AppConfiguration;
import com.payment.adyen.model.payment.adyen.request.DisableRecurringRequest;
import com.payment.adyen.model.payment.graphql.DisableRecurringRequestInput;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DisableRecurringRequestInputToDisableRecurringRequestConverter implements Converter<DisableRecurringRequestInput, DisableRecurringRequest> {

    @Override
    public DisableRecurringRequest convert(DisableRecurringRequestInput disableRecurringRequestInput) {
        DisableRecurringRequest disableRecurringRequest = new DisableRecurringRequest();
        disableRecurringRequest.setMerchantAccount(AppConfiguration.MERCHANT_ACCOUNT);
        disableRecurringRequest.setShopperReference(AppConfiguration.SHOPPER_REFERENCE);
        disableRecurringRequest.setRecurringDetailReference(disableRecurringRequestInput.getRecurringDetailReference());
        disableRecurringRequest.setIdempotencyKey(disableRecurringRequestInput.getUuid());
        return disableRecurringRequest;
    }
}
