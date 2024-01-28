package com.pratap;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pratap.model.Account;
import com.pratap.service.AccountService;

public class App {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext("beans.xml");
		AccountService service = context.getBean(AccountService.class);
		
		System.out.println(service.getClass().getName());
		//service.withdraw(new Account(1), 1000);
		
		service.transferAmount(new Account(2), new Account(1), 1000);
		
		System.out.println("done");
	}

}
