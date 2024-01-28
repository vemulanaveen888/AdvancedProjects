package com.pratap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.pratap.model.Account;
import com.pratap.service.AccountService;

@SpringBootApplication
public class SpringDataJpa2BootApplication {
	@Autowired
	AccountService service;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpa2BootApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo() {
		return new CommandLineRunner() {
			public void run(String... args) throws Exception {
				service.save(new Account(124L, "prasanth", 6000D));
			}
		};
	}

}
