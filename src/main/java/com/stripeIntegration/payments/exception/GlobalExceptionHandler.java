package com.stripeIntegration.payments.exception;

import com.stripeIntegration.payments.constants.ErrorCode;
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
        ErrorResponse resp = new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), ErrorCode.INTERNAL_SERVER_ERROR.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
    }
}

