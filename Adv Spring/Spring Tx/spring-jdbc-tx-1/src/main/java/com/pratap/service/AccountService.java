package com.pratap.service;

import com.pratap.model.Account;

public interface AccountService {
	public  void  withdraw(Account  acc ,  double amt);
	public  void  deposit(Account  acc , double amt);
	public  void transferAmount( Account source , Account dest , double amt);
	
}
