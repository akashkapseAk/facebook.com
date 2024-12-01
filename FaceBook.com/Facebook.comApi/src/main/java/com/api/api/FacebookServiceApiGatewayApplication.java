package com.api.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FacebookServiceApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(FacebookServiceApiGatewayApplication.class, args);
	}

}
