package com.pratap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pratap.dao.AccountRepository;
import com.pratap.model.Account;

@Service
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
