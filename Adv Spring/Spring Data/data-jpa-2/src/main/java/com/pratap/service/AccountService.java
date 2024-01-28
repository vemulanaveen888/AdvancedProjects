package com.pratap.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
	public List<Account>  findByName(){
		return repo.findByName("sara"	);	
	}
	
	@Transactional
	public Long deleteByName(String name) {
		return repo.deleteByName(name);
	}
	
	public Account getByNameAndBalance(String name , double balance) {
		return repo.getByNameAndBalance(name, balance);
	}
	
	public List<Account>  queryByNameIsLike(String pattern){
		return repo.queryByNameIsLike(pattern);
	}
	
	public List<Account>  readByBalanceLessThan(double balance){
		return repo.readByBalanceLessThan(balance);
	}
	
	public List<Account>  findTop2ByBalanceGreaterThan(double min){
		return repo.findTop2ByBalanceGreaterThan(min);
	}
	
	public Account getSingleAccount(Long id) {
		return repo.getSingleAccount(id);
	}
	public List<Account> getAccounts( String name){
		return repo.getAccounts(name);
	}
	
	public List<Account>  getAccountsByNameAndBalance(String name , double balance){
		return repo.getAccountsByNameAndBalance(name, balance);
	}
	
	@Transactional
	public int deleteRecordByName(String name) {
		return repo.deleteRecordByName(name);
	}
}
