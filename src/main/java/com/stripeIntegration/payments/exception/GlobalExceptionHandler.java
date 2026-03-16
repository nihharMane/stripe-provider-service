package com.stripeIntegration.payments.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler returning ErrorResponse with errorCode and errorMessage.
 * This is intentionally small and simple.
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StripeProviderException.class)
    public ResponseEntity<ErrorResponse> handleStripeProvider(StripeProviderException ex) {
        log.warn("StripeProviderException: {} - {}", ex.getErrorCode(), ex.getErrorMessage());
        ErrorResponse resp = new ErrorResponse(ex.getErrorCode(), ex.getErrorMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
        log.error("Unhandled exception: {}", ex.getMessage(), ex);
        ErrorResponse resp = new ErrorResponse("30999", "Internal server error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
    }
}

