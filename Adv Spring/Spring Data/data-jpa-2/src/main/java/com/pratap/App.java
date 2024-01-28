package com.pratap;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pratap.model.Account;
import com.pratap.service.AccountService;

public class App {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("com/pratap/beans.xml");

		AccountService service = context.getBean(AccountService.class);
		//service.save(new  Account(4L, "john" , 8000.0));
		
		//System.out.println("Account saved!");
		
		List<Account> list = service.findByName();
		System.out.println(list.size());
		
		
		//Long long1 = service.deleteByName("rani");
		//System.out.println("Deleted record count : "+long1);
		
		Account acc = service.getByNameAndBalance("sara", 5000.0);
		System.out.println(acc);
		
		List<Account> accounts = service.queryByNameIsLike("pr%");
		for(Account account : accounts)
			System.out.println(account);
		
		
		List<Account> accList = service.readByBalanceLessThan(10000.0	);
		for(Account acc1 : accList)
			System.out.println(acc1);
		
		
		System.out.println("Top 2 balance");
		List<Account> top2List = service.findTop2ByBalanceGreaterThan(0);
		for( Account  acc2 : top2List) {
			System.out.println(acc2);
		}
		
		Account account = service.getSingleAccount(1L);
		System.out.println(account);
		
		
		List<Account> accounts2 = service.getAccounts("prasanth");
		for(Account accc : accounts2)
			System.out.println(accc);
		
		List<Account> nameAndBalance = service.getAccountsByNameAndBalance("john", 8000.0	);
		for(Account  ac : nameAndBalance) {
			System.out.println(ac);
		}
		
		Long val = service.deleteByName("john");
		System.out.println("record deleted : "+val);
		
	}
}
