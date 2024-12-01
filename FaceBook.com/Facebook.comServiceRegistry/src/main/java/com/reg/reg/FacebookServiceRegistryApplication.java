package com.reg.reg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class FacebookServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(FacebookServiceRegistryApplication.class, args);
	}

}
