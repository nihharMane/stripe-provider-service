package com.stripeIntegration.payments.constants;

/**
 * Enum for error codes and messages used in the Stripe Payment Integration Service.
 * All error codes follow the 30000 series pattern.
 */
public enum ErrorCode {
    REQUEST_NULL("30000", "Payment request is null"),
    SUCCESS_URL_MISSING("30001", "Success URL is missing"),
    SUCCESS_URL_INVALID("30002", "Success URL format is invalid"),
    CANCEL_URL_MISSING("30003", "Cancel URL is missing"),
    CANCEL_URL_INVALID("30004", "Cancel URL format is invalid"),
    LINE_ITEMS_MISSING("30005", "Line items are missing"),
    LINE_ITEM_NULL("30006", "Line item is null"),
    PRODUCT_NAME_MISSING("30007", "Product name is missing"),
    CURRENCY_MISSING("30008", "Currency is missing"),
    UNIT_AMOUNT_INVALID("30009", "Unit amount must be greater than 0"),
    QUANTITY_INVALID("30010", "Quantity must be greater than 0"),
    INTERNAL_SERVER_ERROR("30999", "Internal server error");

    private final String code;
    private final String message;

    /**
     * Constructor for ErrorCode enum.
     *
     * @param code the error code
     * @param message the error message
     */
    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Gets the error code.
     *
     * @return the error code
     */
    public String getCode() {
        return code;
    }

    /**
     * Gets the error message.
     *
     * @return the error message
     */
    public String getMessage() {
        return message;
    }
}

