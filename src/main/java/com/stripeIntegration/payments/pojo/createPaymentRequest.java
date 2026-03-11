package com.stripeIntegration.payments.pojo;

import lombok.Data;

import java.util.List;

@Data
public class createPaymentRequest {
    private String successUrl;
    private String cancelUrl;

    List<LineItems> lineItems;
}
