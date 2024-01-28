package com.pratap;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class RetryItemWriter implements ItemWriter<String> {

	private boolean retry = false;
	private int attemptCount = 0;
	
	@Override
	public void write(List<? extends String> items) throws Exception {
		for(String item : items) {
			System.out.println("Writing item : "+item);
			if(retry && item.equalsIgnoreCase("-84")) {
				attemptCount++;
				if(attemptCount >= 5 ) {
					System.out.println("success...");
					retry=false;
					System.out.println(item);
					
				}else {
					System.out.println("Writing of itm "+item+" failed");
					throw new CustomRetryableException("write failed , Attempt  :" +attemptCount);
				}
				
			}else {
				System.out.println(item);
			}
		}

	}

	public void setRetry(boolean b) {
		retry = b;
		
	}

}
