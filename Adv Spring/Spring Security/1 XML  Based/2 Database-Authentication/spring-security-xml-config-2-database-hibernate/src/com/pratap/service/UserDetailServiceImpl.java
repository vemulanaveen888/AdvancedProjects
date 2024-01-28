package com.pratap.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.pratap.dao.UserDao;

public class UserDetailServiceImpl implements UserDetailsService, UserService {

	@Autowired
	private UserDao userDao;

	
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		com.pratap.model.UserDetails user = userDao.findUserByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getId(), user.getPassword(), getAuthority());
	}

	private List getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}

	public List getUsers() {
		return userDao.getUserDetails();
	}
}
