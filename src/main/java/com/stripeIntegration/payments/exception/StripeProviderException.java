package com.stripeIntegration.payments.exception;

import org.springframework.http.HttpStatus;

public class StripeProviderException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;
    private final HttpStatus httpStatus;   // ✅ IMPORTANT

    public StripeProviderException(String errorCode, String errorMessage, HttpStatus httpStatus) {
        super(errorMessage);   // VERY IMPORTANT
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}