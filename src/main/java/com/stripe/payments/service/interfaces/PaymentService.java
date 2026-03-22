package com.stripe.payments.service.interfaces;

import com.stripe.payments.pojo.CreatePaymentReq;
import com.stripe.payments.pojo.PaymentResponse;

public interface PaymentService {
	
	public PaymentResponse createPayment(CreatePaymentReq createPaymentReq);

}
