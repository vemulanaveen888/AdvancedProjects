package com.pratap.dao;

import java.util.List;

import com.pratap.model.UserDetails;

public interface UserDao {
	
	List<UserDetails> getUserDetails();
	UserDetails findUserByEmail(String email);

}
