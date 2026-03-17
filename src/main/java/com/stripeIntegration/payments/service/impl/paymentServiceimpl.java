package com.stripeIntegration.payments.service.impl;

import com.stripeIntegration.payments.exception.StripeProviderException;
import com.stripeIntegration.payments.httpservice.HttpRequest;
import com.stripeIntegration.payments.httpservice.httpServiceCall;
import com.stripeIntegration.payments.pojo.PaymentResponse;
import com.stripeIntegration.payments.pojo.createPaymentRequest;
import com.stripeIntegration.payments.pojo.StripeSessionResponse;
import com.stripeIntegration.payments.service.helper.CreatePaymentHelper;
import com.stripeIntegration.payments.service.interfaces.paymentService;
import com.stripeIntegration.payments.service.ValidationService;
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
    private final ValidationService validationService;


    @Override
    public PaymentResponse createPayment(createPaymentRequest createPaymentRequest) {
        log.info("Create Payment Request: {}", createPaymentRequest);

        // Validate request using ValidationService
        validationService.isValid(createPaymentRequest);

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
