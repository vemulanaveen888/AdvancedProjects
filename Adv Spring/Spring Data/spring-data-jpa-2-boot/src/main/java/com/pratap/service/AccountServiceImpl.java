package com.pratap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pratap.model.Account;
import com.pratap.repository.AccountRepository;

@Service("as")
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository repo;

	public void save(Account acc) {
		repo.save(acc);

	}

}
