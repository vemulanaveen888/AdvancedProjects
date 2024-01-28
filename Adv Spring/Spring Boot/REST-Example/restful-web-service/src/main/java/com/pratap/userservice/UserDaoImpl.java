package com.pratap.userservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl {
	private static List<User> users = new ArrayList<>();
	private static int usersCounts = 3;
	
	static {
		users.add(new User(1, "jack", new Date()));
		users.add(new User(2, "jimi", new Date()));
		users.add(new User(3, "jones", new Date()));
	}
	
	public List<User> findAll() {
		return users;
	}
	
	public User findOne(int id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public User save(User user) {
		if (user.getId() == null) {
			user.setId(++usersCounts);
		}
		users.add(user);
		return user;
	}
	
	public User deleteById(int id) {
		Iterator<User> iterator = users.iterator();
		while(iterator.hasNext()) {
			User user = iterator.next();
			if( user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}
	
	
	
}
