package com.stripeIntegration.payments.service.impl;

import com.stripeIntegration.payments.exception.StripeProviderException;
import com.stripeIntegration.payments.httpservice.HttpRequest;
import com.stripeIntegration.payments.httpservice.httpServiceCall;
import com.stripeIntegration.payments.pojo.PaymentResponse;
import com.stripeIntegration.payments.pojo.createPaymentRequest;
import com.stripeIntegration.payments.pojo.StripeSessionResponse;
import com.stripeIntegration.payments.service.helper.CreatePaymentHelper;
import com.stripeIntegration.payments.service.interfaces.paymentService;
import com.stripeIntegration.payments.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class paymentServiceimpl implements paymentService {
    private final httpServiceCall httpServiceCall;
    private final CreatePaymentHelper createPaymentHelper;
    private final JsonUtil jsonUtil;


    @Override
    public PaymentResponse createPayment(createPaymentRequest createPaymentRequest) {
        log.info("Create Payment Request: {}", createPaymentRequest);

        // Minimal validation - throw StripeProviderException with 30000-series codes
        if (createPaymentRequest == null) {
            throw new StripeProviderException("30000", "Payment request is null");
        }
        if (createPaymentRequest.getSuccessUrl() == null || createPaymentRequest.getSuccessUrl().isEmpty()) {
            throw new StripeProviderException("30001", "Success URL is missing");
        }
        if (createPaymentRequest.getCancelUrl() == null || createPaymentRequest.getCancelUrl().isEmpty()) {
            throw new StripeProviderException("30002", "Cancel URL is missing");
        }
        if (createPaymentRequest.getLineItems() == null || createPaymentRequest.getLineItems().isEmpty()) {
            throw new StripeProviderException("30003", "Line items are missing");
        }

        HttpRequest httpRequest = createPaymentHelper.createHttpStripeSession(createPaymentRequest);

        ResponseEntity<String> httpResponse = httpServiceCall.makeHttpCall(httpRequest);

        // Convert JSON response to StripeSessionResponse object
        StripeSessionResponse stripeSessionResponse = jsonUtil.convertJsonToObject(httpResponse.getBody(), StripeSessionResponse.class);

        // Log the converted object at info level
        log.info("Stripe Session Response: {}", stripeSessionResponse);

        // Create response object with success details
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setSuccess(true);
        paymentResponse.setMessage("Payment session created successfully");
        paymentResponse.setData(stripeSessionResponse);
        paymentResponse.setTimestamp(System.currentTimeMillis());

        log.info("Final Payment Response: {}", paymentResponse);

        return paymentResponse;
    }
}
