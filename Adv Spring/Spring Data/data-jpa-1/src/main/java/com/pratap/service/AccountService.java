package com.pratap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pratap.model.Account;
import com.pratap.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	AccountRepository repo;

	public void save(Account  acc ) {
		System.out.println("repo type name : "+repo.getClass().getName());
		repo.save(acc);
	}
	
}
