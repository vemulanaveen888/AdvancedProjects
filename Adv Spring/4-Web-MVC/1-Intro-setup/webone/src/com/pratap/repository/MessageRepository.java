package com.pratap.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository {
	public String getHelloMessage();
	public String getHiMessage();
}
