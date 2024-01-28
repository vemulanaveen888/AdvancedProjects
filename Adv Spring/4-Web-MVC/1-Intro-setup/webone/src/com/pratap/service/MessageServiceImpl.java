package com.pratap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pratap.repository.MessageRepository;

@Service
public class MessageServiceImpl implements MessageService{
	@Autowired
	private MessageRepository messageRepository;
	
	
	@Override
	public String processHelloMesage() {
		return messageRepository.getHelloMessage();
	}

	@Override
	public String processHiMesage() {
		return messageRepository.getHiMessage();
	}

}
