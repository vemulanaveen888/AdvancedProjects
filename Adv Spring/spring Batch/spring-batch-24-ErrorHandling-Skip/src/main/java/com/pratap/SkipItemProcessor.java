package com.pratap;

import org.springframework.batch.item.ItemProcessor;

public class SkipItemProcessor implements ItemProcessor<String,String>{

	private boolean retry = false;
	private int attemptCount = 0;
	
	@Override
	public String process(String item) throws Exception {
		System.out.println("processing Item "+item);
		
		if(retry && item.equalsIgnoreCase("42")){
			attemptCount++;
			
			System.out.println("Processing Item "+item+" failed");
			throw new CustomRetryableException("Process failed , Attempts : "+attemptCount);
			
		}else {
			return String.valueOf(Integer.valueOf(item) * -1 );
		}
		
	}

	public void setSkip(boolean b) {
		retry = b ; 
		
	}
	

}
