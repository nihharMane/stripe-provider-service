package com.stripeIntegration.payments.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "Request object for creating a Stripe checkout session")
public class createPaymentRequest {

    @Schema(
        description = "The URL to redirect to after a successful payment",
        example = "https://example.com/success",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String successUrl;

    @Schema(
        description = "The URL to redirect to if the payment is cancelled",
        example = "https://example.com/cancel",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String cancelUrl;

    @Schema(
        description = "List of line items (products) to be included in the checkout session",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    List<LineItems> lineItems;
}
