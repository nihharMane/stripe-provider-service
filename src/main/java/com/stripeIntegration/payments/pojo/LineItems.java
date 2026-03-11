package com.stripeIntegration.payments.pojo;

import lombok.Data;

@Data
public class LineItems {
    private String currency;
    private String productName;
    private long unitAmount;
    private long quantity;
}
