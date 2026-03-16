package com.stripeIntegration.payments.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Simple POJO for error responses with errorCode and errorMessage.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Simple error response with code and message")
public class ErrorResponse {

    @JsonProperty("errorCode")
    @Schema(description = "Error code (string, 30000 series)", example = "30001")
    private String errorCode;

    @JsonProperty("errorMessage")
    @Schema(description = "Short human-readable error message", example = "Success URL is missing")
    private String errorMessage;
}

