package com.stripeIntegration.payments.service;

import com.stripeIntegration.payments.exception.StripeProviderException;
import com.stripeIntegration.payments.pojo.createPaymentRequest;
import com.stripeIntegration.payments.pojo.LineItems;
import com.stripeIntegration.payments.constants.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;   // ✅ ADDED
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ValidationService {

    public void isValid(createPaymentRequest request) {
        log.info("Validating payment request");

        if (request == null) {
            log.error(ErrorCode.REQUEST_NULL.getMessage());
            throw new StripeProviderException(
                    ErrorCode.REQUEST_NULL.getCode(),
                    ErrorCode.REQUEST_NULL.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }

        if (request.getSuccessUrl() == null || request.getSuccessUrl().trim().isEmpty()) {
            log.error(ErrorCode.SUCCESS_URL_MISSING.getMessage());
            throw new StripeProviderException(
                    ErrorCode.SUCCESS_URL_MISSING.getCode(),
                    ErrorCode.SUCCESS_URL_MISSING.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }

        if (!isValidUrl(request.getSuccessUrl())) {
            log.error(ErrorCode.SUCCESS_URL_INVALID.getMessage());
            throw new StripeProviderException(
                    ErrorCode.SUCCESS_URL_INVALID.getCode(),
                    ErrorCode.SUCCESS_URL_INVALID.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }

        if (request.getCancelUrl() == null || request.getCancelUrl().trim().isEmpty()) {
            log.error(ErrorCode.CANCEL_URL_MISSING.getMessage());
            throw new StripeProviderException(
                    ErrorCode.CANCEL_URL_MISSING.getCode(),
                    ErrorCode.CANCEL_URL_MISSING.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }

        if (!isValidUrl(request.getCancelUrl())) {
            log.error(ErrorCode.CANCEL_URL_INVALID.getMessage());
            throw new StripeProviderException(
                    ErrorCode.CANCEL_URL_INVALID.getCode(),
                    ErrorCode.CANCEL_URL_INVALID.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }

        if (request.getLineItems() == null || request.getLineItems().isEmpty()) {
            log.error(ErrorCode.LINE_ITEMS_MISSING.getMessage());
            throw new StripeProviderException(
                    ErrorCode.LINE_ITEMS_MISSING.getCode(),
                    ErrorCode.LINE_ITEMS_MISSING.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }

        for (int i = 0; i < request.getLineItems().size(); i++) {
            LineItems item = request.getLineItems().get(i);
            validateLineItem(item, i);
        }

        log.info("Payment request validation successful");
    }

    private void validateLineItem(LineItems item, int index) {

        if (item == null) {
            log.error("Line item at index {} is null", index);
            throw new StripeProviderException(
                    ErrorCode.LINE_ITEM_NULL.getCode(),
                    ErrorCode.LINE_ITEM_NULL.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }

        if (item.getProductName() == null || item.getProductName().trim().isEmpty()) {
            log.error("Product name is missing for line item at index {}", index);
            throw new StripeProviderException(
                    ErrorCode.PRODUCT_NAME_MISSING.getCode(),
                    ErrorCode.PRODUCT_NAME_MISSING.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }

        if (item.getCurrency() == null || item.getCurrency().trim().isEmpty()) {
            log.error("Currency is missing for line item at index {}", index);
            throw new StripeProviderException(
                    ErrorCode.CURRENCY_MISSING.getCode(),
                    ErrorCode.CURRENCY_MISSING.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }

        if (item.getUnitAmount() <= 0) {
            log.error("Unit amount must be greater than 0 for line item at index {}", index);
            throw new StripeProviderException(
                    ErrorCode.UNIT_AMOUNT_INVALID.getCode(),
                    ErrorCode.UNIT_AMOUNT_INVALID.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }

        if (item.getQuantity() <= 0) {
            log.error("Quantity must be greater than 0 for line item at index {}", index);
            throw new StripeProviderException(
                    ErrorCode.QUANTITY_INVALID.getCode(),
                    ErrorCode.QUANTITY_INVALID.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    private boolean isValidUrl(String url) {
        return url != null && (url.startsWith("http://") || url.startsWith("https://"));
    }
}