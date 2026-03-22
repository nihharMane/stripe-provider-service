package com.stripeIntegration.payments.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "PaymentResponse",
        description = "Response returned after creating a Stripe checkout session. Contains session ID and hosted payment URL."
)
public class PaymentResponse {

    @Schema(
            description = "Stripe Checkout Session ID. Use this ID to retrieve session details.",
            example = "cs_test_a1b2c3d4"
    )
    private String stripeSessionId;

    @Schema(
            description = "Hosted payment page URL where the user completes the payment.",
            example = "https://checkout.stripe.com/pay/cs_test_a1b2c3d4"
    )
    private String hostedPageUrl;
}