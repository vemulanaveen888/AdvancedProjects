package com.pratap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.pratap.dao.AccountRepository;
import com.pratap.model.Account;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository repo;

	//@Autowired
	//private PlatformTransactionManager transactionManager;
	
	
	@Autowired
	private TransactionTemplate  template;

	@Override
	public void withdraw(Account acc, double amt) {
		template.execute(new TransactionCallback<Void>() {

			@Override
			public Void doInTransaction(TransactionStatus status) {
				repo.updateAccountBalanceByWithdraw(acc.getAccid(), amt);
				return null;
			}
			
		});
	}

	@Override
	public void deposit(Account acc, double amt) {
		template.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				repo.updateAccountBalanceByDeposit(acc.getAccid(), amt);
			}
		});
	}

	@Override
	public void transferAmount(Account source, Account dest, double amt) {
		
	}
}
