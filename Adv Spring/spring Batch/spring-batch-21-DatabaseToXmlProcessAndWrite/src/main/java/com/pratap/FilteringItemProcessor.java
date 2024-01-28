package com.pratap;

import org.springframework.batch.item.ItemProcessor;

public class FilteringItemProcessor 
		implements ItemProcessor<Account, Account> {
	public Account process(Account item) throws Exception {
		if( item.getAccid() % 2 == 0) {
			return null;
		}
		return item;
	}
}
