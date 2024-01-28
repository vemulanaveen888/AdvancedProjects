package com.pratap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pratap.repository.MessageRepository;

@Service
public class MessageServiceImpl implements MessageService {
	@Autowired
	MessageRepository repo;
	
	@Override
	public String getMessageService() {
		return repo.getMessage();
	}
	
}
