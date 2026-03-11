package com.stripeIntegration.payments.service.helper;

import com.stripeIntegration.payments.constants.Constant;
import com.stripeIntegration.payments.httpservice.HttpRequest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;



@Slf4j
@Service
public class CreatePaymentHelper {

    @Value("${stripe.api.key}")
    private String stripeApiKey;

    @Value("${stripe.create.session.url}")
    private String stripeCreateSessionUrl ;

    public HttpRequest createHttpStripeSession() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(stripeApiKey,"");
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();

        formData.add(Constant.Create_Session_Mode, "payment");
        formData.add(Constant.SUCCESS_URL, "https://example.com/success");

        formData.add("line_items[0][quantity]", "2");
        formData.add("line_items[0][price_data][currency]", "EUR");
        formData.add("line_items[0][price_data][unit_amount]", "100");
        formData.add("line_items[0][price_data][product_data][name]", "Phone xxx");


        HttpRequest httpRequest = new HttpRequest();
        httpRequest.setHttpMethod(HttpMethod.POST);
        httpRequest.setUrl(stripeCreateSessionUrl);
        httpRequest.setHttpHeaders(headers);
        httpRequest.setRequestData(formData);
        return httpRequest;
    }
}
