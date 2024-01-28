package com.pratap;

import org.springframework.batch.item.file.transform.LineAggregator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AccountLineAggregator implements LineAggregator<Account>{
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public String aggregate(Account item){
		try{
			return objectMapper.writeValueAsString(item);
		}catch(JsonProcessingException e){
			throw new RuntimeException("unable to serialize account " ,e);
		}
	}
}