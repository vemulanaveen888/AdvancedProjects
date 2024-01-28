package com.pratap;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.pratap.model.Account;
import com.pratap.service.AccountService;

public class App {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(AppConfig.class);
		
		AccountService service = context.getBean(AccountService.class);
		//service.deposit(new  Account(1), 2000.0);
		
		
		//service.transferAmount(new Account(2), new Account(1), 4000);
		
		service.withdraw(new Account(2), 4000);
		System.out.println("done");
	}

}
