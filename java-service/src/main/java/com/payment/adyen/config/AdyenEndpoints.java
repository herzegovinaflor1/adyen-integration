package com.payment.adyen.config;

import com.payment.adyen.exception.ConfigurationNotFoundException;
import org.apache.commons.lang3.StringUtils;

public final class AdyenEndpoints {

    private AdyenEndpoints() {
        throw new IllegalStateException("Object cannot be instantiated");
    }

    private static final String AUTHORIZATION_PARAM = "AUTHORIZATION";
    private static final String AUTHORIZATION_3D_PARAM = "AUTHORIZATION_3D";
    private static final String CAPTURE_PARAM = "CAPTURE";
    private static final String CANCEL_PARAM = "CANCEL";
    private static final String REFUND_PARAM = "REFUND";
    private static final String RECURRING_PAYMENTS_PARAM = "RECURRING_PAYMENTS";
    private static final String DISABLE_RECURRING_PAYMENTS_PARAM = "DISABLE_RECURRING_PAYMENTS";

    public static final String AUTHORIZATION = getConfiguration(AUTHORIZATION_PARAM);
    public static final String AUTHORIZATION_3D = getConfiguration(AUTHORIZATION_3D_PARAM);
    public static final String CAPTURE = getConfiguration(CAPTURE_PARAM);
    public static final String CANCEL = getConfiguration(CANCEL_PARAM);
    public static final String REFUND = getConfiguration(REFUND_PARAM);
    public static final String RECURRING_PAYMENTS = getConfiguration(RECURRING_PAYMENTS_PARAM);
    public static final String DISABLE_RECURRING_PAYMENTS = getConfiguration(DISABLE_RECURRING_PAYMENTS_PARAM);

    private static String getConfiguration(String key) {
        var param = System.getenv(key);
        if (StringUtils.isEmpty(param)) {
            throw new ConfigurationNotFoundException("Value by key " + key + " is not found.");
        }
        return param;
    }
}
