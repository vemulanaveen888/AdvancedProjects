package com.pratap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConfigClientAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientAppApplication.class, args);
	}

}
