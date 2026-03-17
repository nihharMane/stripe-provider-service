package com.stripeIntegration.payments.service;

import com.stripeIntegration.payments.exception.StripeProviderException;
import com.stripeIntegration.payments.pojo.createPaymentRequest;
import com.stripeIntegration.payments.pojo.LineItems;
import com.stripeIntegration.payments.constants.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service for validating payment requests.
 * Throws StripeProviderException with error codes in the 30000 series if validation fails.
 */
@Slf4j
@Service
public class ValidationService {

    /**
     * Validates all fields in the createPaymentRequest.
     * Throws StripeProviderException if any validation fails.
     *
     * @param request the payment request to validate
     * @throws StripeProviderException if any validation fails
     */
    public void isValid(createPaymentRequest request) {
        log.info("Validating payment request");

        // Check if request is null
        if (request == null) {
            log.error(ErrorCode.REQUEST_NULL.getMessage());
            throw new StripeProviderException(ErrorCode.REQUEST_NULL.getCode(), ErrorCode.REQUEST_NULL.getMessage());
        }

        // Validate successUrl
        if (request.getSuccessUrl() == null || request.getSuccessUrl().trim().isEmpty()) {
            log.error(ErrorCode.SUCCESS_URL_MISSING.getMessage());
            throw new StripeProviderException(ErrorCode.SUCCESS_URL_MISSING.getCode(), ErrorCode.SUCCESS_URL_MISSING.getMessage());
        }

        // Validate successUrl format (basic URL validation)
        if (!isValidUrl(request.getSuccessUrl())) {
            log.error(ErrorCode.SUCCESS_URL_INVALID.getMessage());
            throw new StripeProviderException(ErrorCode.SUCCESS_URL_INVALID.getCode(), ErrorCode.SUCCESS_URL_INVALID.getMessage());
        }

        // Validate cancelUrl
        if (request.getCancelUrl() == null || request.getCancelUrl().trim().isEmpty()) {
            log.error(ErrorCode.CANCEL_URL_MISSING.getMessage());
            throw new StripeProviderException(ErrorCode.CANCEL_URL_MISSING.getCode(), ErrorCode.CANCEL_URL_MISSING.getMessage());
        }

        // Validate cancelUrl format (basic URL validation)
        if (!isValidUrl(request.getCancelUrl())) {
            log.error(ErrorCode.CANCEL_URL_INVALID.getMessage());
            throw new StripeProviderException(ErrorCode.CANCEL_URL_INVALID.getCode(), ErrorCode.CANCEL_URL_INVALID.getMessage());
        }

        // Validate lineItems
        if (request.getLineItems() == null || request.getLineItems().isEmpty()) {
            log.error(ErrorCode.LINE_ITEMS_MISSING.getMessage());
            throw new StripeProviderException(ErrorCode.LINE_ITEMS_MISSING.getCode(), ErrorCode.LINE_ITEMS_MISSING.getMessage());
        }

        // Validate each line item
        for (int i = 0; i < request.getLineItems().size(); i++) {
            LineItems item = request.getLineItems().get(i);
            validateLineItem(item, i);
        }

        log.info("Payment request validation successful");
    }

    /**
     * Validates a single line item.
     *
     * @param item the line item to validate
     * @param index the index of the item in the list
     * @throws StripeProviderException if validation fails
     */
    private void validateLineItem(LineItems item, int index) {
        if (item == null) {
            log.error("Line item at index {} is null", index);
            throw new StripeProviderException(ErrorCode.LINE_ITEM_NULL.getCode(), ErrorCode.LINE_ITEM_NULL.getMessage());
        }

        // Validate productName
        if (item.getProductName() == null || item.getProductName().trim().isEmpty()) {
            log.error("Product name is missing for line item at index {}", index);
            throw new StripeProviderException(ErrorCode.PRODUCT_NAME_MISSING.getCode(), ErrorCode.PRODUCT_NAME_MISSING.getMessage());
        }

        // Validate currency
        if (item.getCurrency() == null || item.getCurrency().trim().isEmpty()) {
            log.error("Currency is missing for line item at index {}", index);
            throw new StripeProviderException(ErrorCode.CURRENCY_MISSING.getCode(), ErrorCode.CURRENCY_MISSING.getMessage());
        }

        // Validate unitAmount
        if (item.getUnitAmount() <= 0) {
            log.error("Unit amount must be greater than 0 for line item at index {}", index);
            throw new StripeProviderException(ErrorCode.UNIT_AMOUNT_INVALID.getCode(), ErrorCode.UNIT_AMOUNT_INVALID.getMessage());
        }

        // Validate quantity
        if (item.getQuantity() <= 0) {
            log.error("Quantity must be greater than 0 for line item at index {}", index);
            throw new StripeProviderException(ErrorCode.QUANTITY_INVALID.getCode(), ErrorCode.QUANTITY_INVALID.getMessage());
        }
    }

    /**
     * Basic URL validation - checks if string starts with http:// or https://
     *
     * @param url the URL to validate
     * @return true if URL format is valid, false otherwise
     */
    private boolean isValidUrl(String url) {
        return url != null && (url.startsWith("http://") || url.startsWith("https://"));
    }
}
