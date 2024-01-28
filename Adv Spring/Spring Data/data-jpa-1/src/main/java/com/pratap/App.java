package com.pratap;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.pratap.model.Account;
import com.pratap.service.AccountService;

public class App {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context =
							new AnnotationConfigApplicationContext(AppConfig.class);
		
		AccountService service = context.getBean(AccountService.class);
		service.save(new Account(3L,"sara",5000.0));
							
	}

}
