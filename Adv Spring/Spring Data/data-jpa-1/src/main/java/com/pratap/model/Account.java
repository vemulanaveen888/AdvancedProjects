package com.pratap.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {
	@Id
	private Long accid;
	private String name;
	private Double balance;

	public Account() {
	}

	public Account(Long accid, String name, Double balance) {
		this.accid = accid;
		this.name = name;
		this.balance = balance;
	}

	public Long getAccid() {
		return accid;
	}

	public void setAccid(Long accid) {
		this.accid = accid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}
	

}
