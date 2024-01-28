package com.pratap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.pratap.model.Account;
import com.pratap.service.AccountService;

@SpringBootApplication
public class SpringDataJpaDataRestApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringDataJpaDataRestApplication.class, args);
		
		//AccountService service = context.getBean(AccountService.class);
		//service.save(new Account(2L, "prasanth", 1000.0));
		//System.out.println("done");
	}

}
