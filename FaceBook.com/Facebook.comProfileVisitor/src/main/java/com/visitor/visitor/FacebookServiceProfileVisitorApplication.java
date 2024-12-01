package com.visitor.visitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FacebookServiceProfileVisitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(FacebookServiceProfileVisitorApplication.class, args);
	}

}
