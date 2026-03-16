package com.stripeIntegration.payments.service.helper;

import com.stripeIntegration.payments.constants.Constant;
import com.stripeIntegration.payments.httpservice.HttpRequest;
import com.stripeIntegration.payments.pojo.LineItems;
import com.stripeIntegration.payments.pojo.createPaymentRequest;
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
    private String stripeCreateSessionUrl;

    public HttpRequest createHttpStripeSession(createPaymentRequest request) {

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(stripeApiKey, "");
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();

        formData.add(Constant.Create_Session_Mode, Constant.PAYMENT_MODE);

        // use values from request
        formData.add(Constant.SUCCESS_URL, request.getSuccessUrl());
        formData.add(Constant.CANCEL_URL, request.getCancelUrl());

        int index = 0;

        for (LineItems item : request.getLineItems()) {

            formData.add(String.format(Constant.LINE_ITEMS_BRACKET_FORMAT, index, Constant.LINE_ITEMS_QUANTITY), String.valueOf(item.getQuantity()));
            formData.add(String.format(Constant.PRICE_DATA_BRACKET_FORMAT, index, Constant.LINE_ITEMS_PRICE_DATA_CURRENCY), item.getCurrency());
            formData.add(String.format(Constant.PRICE_DATA_BRACKET_FORMAT, index, Constant.LINE_ITEMS_PRICE_DATA_UNIT_AMOUNT), String.valueOf(item.getUnitAmount()));
            formData.add(String.format(Constant.PRODUCT_DATA_BRACKET_FORMAT, index, Constant.LINE_ITEMS_PRICE_DATA_PRODUCT_NAME), item.getProductName());

            index++;
        }

        HttpRequest httpRequest = new HttpRequest();
        httpRequest.setHttpMethod(HttpMethod.POST);
        httpRequest.setUrl(stripeCreateSessionUrl);
        httpRequest.setHttpHeaders(headers);
        httpRequest.setRequestData(formData);

        return httpRequest;
    }
}