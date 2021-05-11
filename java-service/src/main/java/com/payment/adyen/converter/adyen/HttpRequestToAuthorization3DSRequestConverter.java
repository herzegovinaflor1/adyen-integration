package com.payment.adyen.converter.adyen;

import com.payment.adyen.config.AppConfiguration;
import com.payment.adyen.model.payment.adyen.request.Authorization3DRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class HttpRequestToAuthorization3DSRequestConverter implements Converter<HttpServletRequest, Authorization3DRequest> {

    @Override
    public Authorization3DRequest convert(HttpServletRequest source) {
        var md = source.getParameter("MD");
        var paRes = source.getParameter("PaRes");
        return Authorization3DRequest.builder()
                .setMd(md)
                .setPaResponse(paRes)
                .setMerchantAccount(AppConfiguration.MERCHANT_ACCOUNT)
                .setShopperIP(AppConfiguration.SHOPPER_IP)
                .build();
    }
}
