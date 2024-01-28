package com.pratap;

import org.springframework.batch.item.ItemWriter;
import org.springframework.classify.Classifier;

public class AccountClassifier implements Classifier<Account, ItemWriter<? super Account>> {

	private static final long serialVersionUID = 1L;
	
	private ItemWriter<Account> evenItemWriter;
	private ItemWriter<Account> oddItemWriter;
	
	AccountClassifier(ItemWriter<Account> evenItemWriter , ItemWriter<Account> oddItemWriter){
		this.evenItemWriter = evenItemWriter;
		this.oddItemWriter = oddItemWriter;
	}
	@Override
	public ItemWriter<? super Account> classify(Account account) {
		return account.getAccid()%2==0 ? evenItemWriter : oddItemWriter;
	}

}
