package com.stripeIntegration.payments.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Stripe Checkout Session Response containing session details and URLs")
public class StripeSessionResponse {

    @Schema(
        description = "Unique identifier for the Stripe checkout session",
        example = "cs_test_a1b2c3d4e5f6g7h8i9j0",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String id;

    @Schema(
        description = "The URL that directs the customer to the Stripe-hosted payment page",
        example = "https://checkout.stripe.com/pay/cs_test_a1b2c3d4e5f6g7h8i9j0",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String url;

    @JsonProperty("payment_status")
    @Schema(
        description = "Current payment status (paid, unpaid, no_payment_required)",
        example = "unpaid"
    )
    private String paymentStatus;

    @Schema(
        description = "Session status (open, complete, expired)",
        example = "open",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String status;

}