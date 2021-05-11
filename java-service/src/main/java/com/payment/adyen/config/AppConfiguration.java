package com.payment.adyen.config;

import com.payment.adyen.exception.ConfigurationNotFoundException;
import org.apache.commons.lang3.StringUtils;

public final class AppConfiguration {

    private AppConfiguration() {
        throw new IllegalStateException("Object cannot be instantiated");
    }

    private static final String MERCHANT_ACCOUNT_PARAM = "MERCHANT_ACCOUNT";
    private static final String SHOPPER_IP_PARAM = "SHOPPER_IP";
    private static final String ISSUE_URL_PARAM = "ISSUE_URL";
    private static final String SHOPPER_REFERENCE_PARAM = "SHOPPER_REFERENCE";
    private static final String SHOPPER_EMAIL_PARAM = "SHOPPER_EMAIL";
    private static final String X_API_KEY_PARAM = "XAPIKey";

    public static final String MERCHANT_ACCOUNT = getConfiguration(MERCHANT_ACCOUNT_PARAM);
    public static final String SHOPPER_IP = getConfiguration(SHOPPER_IP_PARAM);
    public static final String SHOPPER_REFERENCE = getConfiguration(SHOPPER_REFERENCE_PARAM);
    public static final String ISSUE_URL = getConfiguration(ISSUE_URL_PARAM);
    public static final String SHOPPER_EMAIL = getConfiguration(SHOPPER_EMAIL_PARAM);
    public static final String X_API_KEY = getConfiguration(X_API_KEY_PARAM);

    private static String getConfiguration(String key) {
        var param = System.getenv(key);
        if (StringUtils.isEmpty(param)) {
            throw new ConfigurationNotFoundException("Value by key " + key + " is not found.");
        }
        return param;
    }
}
