package com.pratap.repository;

import org.springframework.stereotype.Repository;

@Repository
public class MessageRepositoryImpl implements MessageRepository {

	@Override
	public String getHelloMessage() {
		return "Hello Spring Framework";
	}

	@Override
	public String getHiMessage() {
		return "Hi Spring Framework";
	}

}
