package com.payment.adyen.model.payment.api;

import com.payment.adyen.exception.ApiRequestNotValid;
import org.apache.commons.lang3.StringUtils;

public final class ApiRequest<T, R> {

    private final String url;
    private final T request;
    private final Class<R> response;

    public ApiRequest(T request, Class<R> response, String url) {
        this.url = url;
        this.request = request;
        this.response = response;
    }

    public Class<R> getResponse() {
        return response;
    }

    public T getRequest() {
        return request;
    }

    public String getUrl() {
        return url;
    }

    public static <T, R> ApiRequest<T, R> of(T request, Class<R> response, String url) {
        if(request == null || response == null || StringUtils.isEmpty(url)){
            throw new ApiRequestNotValid("Api request is not valid. " + request + " " + response + " " + url);
        }
        return new ApiRequest<>(request, response, url);
    }

}
