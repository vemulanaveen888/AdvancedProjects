package com.pratap;

import java.util.Iterator;
import java.util.List;

import org.springframework.batch.item.ItemReader;

public class StatelessItemReader implements ItemReader<String> {
	
	private final Iterator<String> data;
	
	public StatelessItemReader(List<String> data) {
		this.data = data.iterator();
	}

	public String read() throws Exception {
		System.out.println(">> read() called");
		if (this.data.hasNext()) {
			return this.data.next();
		} else {
			return null;
		}
	}
}