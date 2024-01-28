package com.pratap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.pratap.dao.AccountRepository;
import com.pratap.model.Account;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository repo;

	@Autowired
	private PlatformTransactionManager transactionManager;

	@Override
	public void withdraw(Account acc, double amt) {
		TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());

		try {
			repo.updateAccountBalanceByWithdraw(acc.getAccid(), amt);
			transactionManager.commit(transactionStatus);
		} catch (Exception e) {
			e.printStackTrace();
			transactionManager.rollback(transactionStatus);
		}

	}

	@Override
	public void deposit(Account acc, double amt) {
		TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());

		try {
			repo.updateAccountBalanceByDeposit(acc.getAccid(), amt);
			transactionManager.commit(transactionStatus);
		} catch (Exception e) {
			e.printStackTrace();
			transactionManager.rollback(transactionStatus);
		}
	}

	@Override
	public void transferAmount(Account source, Account dest, double amt) {
		TransactionStatus transactionStatus =
				transactionManager.getTransaction(new DefaultTransactionDefinition());

		try {
			//repo.updateAccountBalanceByWithdraw(source.getAccid(), amt);
			//repo.updateAccountBalanceByDeposit(dest.getAccid(), amt);
			
			withdraw(source, amt);
			deposit(dest, amt);

			transactionManager.commit(transactionStatus);
		} catch (Exception e) {
			e.printStackTrace();
			transactionManager.rollback(transactionStatus);
		}
	}
}
