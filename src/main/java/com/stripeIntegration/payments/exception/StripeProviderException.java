package com.stripeIntegration.payments.exception;

/**
 * Simple custom runtime exception carrying an error code and a message.
 */
public class StripeProviderException extends RuntimeException {
    private final String errorCode;
    private final String errorMessage;

    public StripeProviderException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public StripeProviderException(String errorCode, String errorMessage, Throwable cause) {
        super(errorMessage, cause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

