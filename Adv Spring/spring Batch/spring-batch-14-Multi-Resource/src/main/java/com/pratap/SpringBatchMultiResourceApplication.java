package com.pratap;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class SpringBatchMultiResourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchMultiResourceApplication.class, args);
	}

}
