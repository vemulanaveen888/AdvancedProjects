package com.pratap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.pratap.dao.AccountRepository;
import com.pratap.model.Account;

@Service
@Transactional(transactionManager = "transactionManager" , 
				isolation =Isolation.DEFAULT ,
				propagation = Propagation.REQUIRED , 
				readOnly = false)
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository repo;

	@Override
	public void withdraw(Account acc, double amt) {

		repo.updateAccountBalanceByWithdraw(acc.getAccid(), amt);

	}

	@Override
	public void deposit(Account acc, double amt) {

		repo.updateAccountBalanceByDeposit(acc.getAccid(), amt);

	}

	@Override
	public void transferAmount(Account source, Account dest, double amt) {

		repo.updateAccountBalanceByWithdraw(source.getAccid(), amt);
		repo.updateAccountBalanceByDeposit(dest.getAccid(), amt);

	}
}
