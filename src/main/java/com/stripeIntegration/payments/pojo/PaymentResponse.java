package com.stripeIntegration.payments.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "API Response wrapper for payment creation containing the Stripe session details")
public class PaymentResponse {

    @JsonProperty("success")
    @Schema(
        description = "Indicates whether the payment session was created successfully",
        example = "true"
    )
    private Boolean success;

    @JsonProperty("message")
    @Schema(
        description = "Human-readable message describing the result of the operation",
        example = "Payment session created successfully"
    )
    private String message;

    @JsonProperty("data")
    @Schema(
        description = "The Stripe checkout session response containing session details",
        implementation = StripeSessionResponse.class
    )
    private StripeSessionResponse data;

    @JsonProperty("timestamp")
    @Schema(
        description = "Timestamp in milliseconds when the response was generated",
        example = "1710350400000"
    )
    private Long timestamp;
}
