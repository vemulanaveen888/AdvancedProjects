package com.pratap;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class SysOutItemWriter implements ItemWriter<String> {

	@Override
	public void write(List<? extends String> items) throws Exception {
		System.out.println("The size of the chunk was : "+items.size());
		
		for(String item : items)
			System.out.println(">> 	"+item);
	}

}
