
package com.stripeIntegration.payments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@OpenAPIDefinition(
	info = @Info(
		title = "Stripe Payment Integration Service",
		version = "1.0.0",
		description = "REST API for creating Stripe checkout sessions and managing payment requests",
		contact = @Contact(
			name = "Stripe Integration Team",
			email = "support@stripepayments.com"
		),
		license = @License(
			name = "Apache 2.0",
			url = "https://www.apache.org/licenses/LICENSE-2.0.html"
		)
	)
)
public class StripeProviderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StripeProviderServiceApplication.class, args);
	}

}

