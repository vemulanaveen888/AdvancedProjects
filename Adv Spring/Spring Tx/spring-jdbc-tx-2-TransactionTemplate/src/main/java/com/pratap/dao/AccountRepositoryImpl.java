package com.pratap.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepositoryImpl implements AccountRepository {
	@Autowired
	private JdbcTemplate  template;

	
	public void updateAccountBalanceByDeposit(int accid, double amt) {
		template.update("update account set balance=balance+? where accid=?", amt , accid);
		
	}

	
	public void updateAccountBalanceByWithdraw(int accid, double amt) {
		template.update("update account set balance=balance-? where accid=?", amt , accid);	
	}
	
	
}
