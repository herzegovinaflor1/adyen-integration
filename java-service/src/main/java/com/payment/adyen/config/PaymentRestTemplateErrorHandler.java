package com.payment.adyen.config;

import com.payment.adyen.exception.ApiRequestNotValid;
import org.apache.commons.io.IOUtils;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@Component
public class PaymentRestTemplateErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        var statusCode = response.getStatusCode().series();
        return statusCode == CLIENT_ERROR
                || statusCode == SERVER_ERROR;
    }

    /**
     * We will unify all errors together to simplify code integration
     *
     * @param response which contains error information from Adyen
     */
    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        throw new ApiRequestNotValid("Please, verify the card information: " + IOUtils.toString(response.getBody(), StandardCharsets.UTF_8.name()));
    }
}
