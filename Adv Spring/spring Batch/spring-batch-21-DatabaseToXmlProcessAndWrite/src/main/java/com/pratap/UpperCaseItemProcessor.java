package com.pratap;

import org.springframework.batch.item.ItemProcessor;

public class UpperCaseItemProcessor implements ItemProcessor<Account, Account> {
	public Account process(Account item) throws Exception {
		return new Account(item.getAccid(),
						item.getName().toUpperCase(),
						item.getBalance());
	}
}
