package com.pratap.dao;

public interface AccountRepository {
	public void updateAccountBalanceByDeposit(int accid , double amt);
	public void updateAccountBalanceByWithdraw(int accid , double amt);
}
