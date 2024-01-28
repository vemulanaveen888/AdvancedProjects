package com.pratap.service;

import org.springframework.stereotype.Service;

import com.pratap.model.Account;

@Service
public interface AccountService {
	public void save(Account  acc );
}
