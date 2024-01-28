package com.pratap.repository;

import org.springframework.stereotype.Repository;

@Repository
public class MessageRepositoryImpl implements MessageRepository {

	public String getMessage() {
		return "Hello!";
	}

}
