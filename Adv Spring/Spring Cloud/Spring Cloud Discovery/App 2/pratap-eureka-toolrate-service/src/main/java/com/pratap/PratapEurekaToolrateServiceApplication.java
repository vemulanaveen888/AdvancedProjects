package com.pratap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PratapEurekaToolrateServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PratapEurekaToolrateServiceApplication.class, args);
	}

}
