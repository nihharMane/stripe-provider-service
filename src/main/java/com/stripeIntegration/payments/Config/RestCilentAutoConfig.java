package com.stripeIntegration.payments.Config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@Slf4j
public class RestCilentAutoConfig {

    @Bean
    public RestClient restClient(RestClient.Builder builder) {
        log.info("Creating RestClient");
        return builder.build();
    }
}
