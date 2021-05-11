package com.payment.adyen.model.meta;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class AmountInfo {

    private Integer value;
    private String currency;

}
