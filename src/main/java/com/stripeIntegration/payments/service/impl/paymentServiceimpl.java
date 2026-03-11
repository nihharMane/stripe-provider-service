package com.stripeIntegration.payments.service.impl;

import com.stripeIntegration.payments.httpservice.HttpRequest;
import com.stripeIntegration.payments.httpservice.httpServiceCall;
import com.stripeIntegration.payments.service.helper.CreatePaymentHelper;
import com.stripeIntegration.payments.service.interfaces.paymentService;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class paymentServiceimpl implements paymentService {
    private final httpServiceCall httpServiceCall;
    private final CreatePaymentHelper createPaymentHelper;

    @Override
    public String createPayment() {
        HttpRequest httpRequest = createPaymentHelper.createHttpStripeSession();

        String httpResponse= httpServiceCall.makeHttpCall(httpRequest);
        log.info("createPayment");
        return "Payment created successfully from paymentServiceimpl" + httpResponse;
    }





}
