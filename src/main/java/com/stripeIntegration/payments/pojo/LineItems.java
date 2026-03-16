package com.stripeIntegration.payments.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Line item details for a product in the checkout session")
public class LineItems {

    @Schema(
        description = "Currency code in ISO 4217 format (e.g., USD, EUR, GBP)",
        example = "USD",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String currency;

    @Schema(
        description = "Name or title of the product",
        example = "Premium Subscription",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String productName;

    @Schema(
        description = "Unit amount in the smallest currency unit (e.g., cents for USD). Example: 2000 for $20.00",
        example = "2000",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private long unitAmount;

    @Schema(
        description = "Quantity of the product",
        example = "1",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private long quantity;
}
