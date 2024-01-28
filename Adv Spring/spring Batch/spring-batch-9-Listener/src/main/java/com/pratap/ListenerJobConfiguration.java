package com.pratap;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListenerJobConfiguration {
	
	

	@Autowired
	JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public ItemReader<String> reader(){
		return new ListItemReader<>(Arrays.asList("one","two","three"));
	}
	@Bean
	public ItemWriter<String> writer(){
		return new ItemWriter<String>() {
			public void write(List<? extends String> items) throws Exception {
				for(String item : items	)
					System.out.println("writing item : "+item);
			}
		};
	}
	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1")
				.<String,String>chunk(2)
				.faultTolerant()
				.listener(new com.pratap.ChunkListener())
				.reader(reader())
				.writer(writer())
				.build();
	}
	@Bean 
	public Job job() {
		System.out.println("job");
		return jobBuilderFactory.get("job4")
				.start(step1())
				.listener(new JobLIstener())
				.build();
	}
}
