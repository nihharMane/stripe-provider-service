package com.stripeIntegration.payments.service.interfaces;

import com.stripeIntegration.payments.pojo.PaymentResponse;
import com.stripeIntegration.payments.pojo.createPaymentRequest;

/**
 * Service interface for handling payment operations
 * Provides methods to create and manage Stripe checkout sessions
 */
public interface paymentService {

    /**
     * Creates a new Stripe checkout session with the provided payment details
     *
     * @param createPaymentRequest the request object containing success/cancel URLs and line items
     * @return PaymentResponse containing the session details and Stripe URL
     */
    PaymentResponse createPayment(createPaymentRequest createPaymentRequest);
}
