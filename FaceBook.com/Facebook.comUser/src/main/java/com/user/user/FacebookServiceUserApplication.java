package com.user.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootApplication
@EnableEurekaClient
public class FacebookServiceUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(FacebookServiceUserApplication.class, args);
	}



}
