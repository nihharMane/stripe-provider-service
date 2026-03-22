package com.stripeIntegration.payments.httpservice;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClient;

import com.stripeIntegration.payments.exception.StripeProviderException;
import com.stripeIntegration.payments.constants.ErrorCode;

@Slf4j
@Component
@RequiredArgsConstructor
public class httpServiceCall {

    private final RestClient restClient;

    public ResponseEntity<String> makeHttpCall(HttpRequest httpRequest) {

        log.info("Making Http Call to external service");

        try {
            ResponseEntity<String> httpResponse = restClient.method(httpRequest.getHttpMethod())
                    .uri(httpRequest.getUrl())
                    .headers(t -> t.addAll(httpRequest.getHttpHeaders()))
                    .body(httpRequest.getRequestData())
                    .retrieve()
                    .toEntity(String.class);

            log.info("Restclient response is {}", httpResponse.getBody());
            return httpResponse;

        } catch (HttpClientErrorException | HttpServerErrorException e) {

            log.error("Http error during HTTP call: Status code:{}, Response body:{}",
                    e.getStatusCode(), e.getResponseBodyAsString(), e);

            // ✅ Only throw exception for 503 / 504
            if (e.getStatusCode() == HttpStatus.SERVICE_UNAVAILABLE ||
                    e.getStatusCode() == HttpStatus.GATEWAY_TIMEOUT) {

                throw new StripeProviderException(
                        ErrorCode.STRIPE_SERVICE_UNAVAILABLE.getCode(),
                        ErrorCode.STRIPE_SERVICE_UNAVAILABLE.getMessage(),
                        HttpStatus.INTERNAL_SERVER_ERROR
                );
            }

            // ✅ IMPORTANT: Return Stripe error response (DON'T throw)
            return ResponseEntity
                    .status(e.getStatusCode())
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(e.getResponseBodyAsString());
        }

        catch (Exception e) {

            log.error("Error during HTTP call: {}", e.getMessage(), e);

            throw new StripeProviderException(
                    ErrorCode.STRIPE_CONNECTING_ERROR.getCode(),
                    ErrorCode.STRIPE_CONNECTING_ERROR.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PostConstruct
    public void init() {
        log.info("init method in httpServiceCall restClient:{}", restClient);
    }
}