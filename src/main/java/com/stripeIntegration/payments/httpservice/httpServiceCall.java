package com.stripeIntegration.payments.httpservice;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

@Slf4j

@Component
@RequiredArgsConstructor
public class httpServiceCall {


    private final RestClient restClient;
    public String makeHttpCall(HttpRequest httpRequest){

        ResponseEntity <String> response= restClient.method(httpRequest.getHttpMethod())
                .uri(httpRequest.getUrl())
                .headers((HttpHeaders t)-> t.addAll(httpRequest.getHttpHeaders()))
                .body(httpRequest.getRequestData())
                .retrieve()
                .toEntity(String.class);

        log.info("Restclient response is  " + response.getBody());
        return  response.getBody();
    }
    @PostConstruct
    public String init(){
        log.info("init method in httpServiceCall restClient:{}",restClient);
        return "Init method called in httpServiceCall " + restClient;
    }

}
