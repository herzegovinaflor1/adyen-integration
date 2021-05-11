package com.payment.adyen.model.payment.graphql;

import java.io.Serializable;

public class DisableRecurringRequestInput implements Serializable {

    private String recurringDetailReference;
    private String uuid;

    public String getRecurringDetailReference() {
        return recurringDetailReference;
    }

    public void setRecurringDetailReference(String recurringDetailReference) {
        this.recurringDetailReference = recurringDetailReference;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
