package com.stripeIntegration.payments.controller;
import com.stripeIntegration.payments.httpservice.HttpRequest;
import com.stripeIntegration.payments.httpservice.httpServiceCall;
import com.stripeIntegration.payments.pojo.PaymentResponse;
import com.stripeIntegration.payments.pojo.createPaymentRequest;
import com.stripeIntegration.payments.service.interfaces.paymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/payments")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Payment Management", description = "APIs for managing Stripe payment sessions")
public class paymentController {
    private final paymentService paymentService;
    private final httpServiceCall httpServiceCall;

    @PostMapping
    @Operation(
        summary = "Create a Stripe Checkout Session",
        description = "Creates a new Stripe checkout session with the provided payment details including success/cancel URLs and line items"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Checkout session created successfully",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = PaymentResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid request payload - missing or incorrect fields"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Internal server error - failed to create checkout session"
        )
    })
     public PaymentResponse createPayment(@RequestBody createPaymentRequest createPaymentRequest) {

        log.info("CreatingPaymentRequest: {}", createPaymentRequest);
        PaymentResponse paymentResponse = paymentService.createPayment(createPaymentRequest);
        log.info("Response from paymentService: {}", paymentResponse);
        return paymentResponse;
    }

}
