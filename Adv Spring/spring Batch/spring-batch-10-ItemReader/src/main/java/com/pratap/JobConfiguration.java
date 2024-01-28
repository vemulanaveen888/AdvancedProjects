package com.pratap;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfiguration {

	@Autowired
	private JobBuilderFactory  jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public StatelessItemReader statelessItemReader() {
		List<String> data = new  ArrayList<>();
		data.add("foo");
		data.add("bar");
		data.add("baz");
		
		return new StatelessItemReader(data);
	}
	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1")
				.<String,String>chunk(2)
				.reader(statelessItemReader())
				.writer(new ItemWriter<String>() {
					public void write(List<? extends String> items) throws Exception {
						System.out.println("chunk : " +items);
						for(String item : items	) {
							System.out.println("current item : "+item);
						}
					}
				}).build();
	}
	@Bean
	public Job job1() {
		return jobBuilderFactory.get("myjob1111")
				.start(step1())
				.build();
		
	}
}
