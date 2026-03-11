package com.stripeIntegration.payments.controller;

import com.stripeIntegration.payments.httpservice.HttpRequest;
import com.stripeIntegration.payments.httpservice.httpServiceCall;
import com.stripeIntegration.payments.pojo.createPaymentRequest;
import com.stripeIntegration.payments.service.interfaces.paymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/payments")
@Slf4j

@RequiredArgsConstructor
public class  paymentController {
    private final paymentService paymentService;
    private final httpServiceCall httpServiceCall;
    @PostMapping
   public String createPayment(@RequestBody createPaymentRequest createPaymentRequest){
        log.info("CreatingPaymentRequest: {}", createPaymentRequest);

       String response=paymentService.createPayment();
       log.info("Response from paymentService: {}", response);
         return "Payment created successfully from paymentController"+response;
    }
    @GetMapping
    public String httpcall(){
        String response=httpServiceCall.makeHttpCall(new HttpRequest());
        log.info("Response from httpServiceCall in paymentController: {}", response);
        return response;

    }




}
