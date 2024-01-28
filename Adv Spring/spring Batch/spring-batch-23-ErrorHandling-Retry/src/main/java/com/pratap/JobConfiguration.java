package com.pratap;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
public class JobConfiguration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	@StepScope
	public ListItemReader<String> reader(){
		List<String> items = new  ArrayList<>();
		for(int i = 0; i <100; i++) {
			items.add(String.valueOf(i));
		}
		ListItemReader<String> reader = new ListItemReader<>(items);
		return reader;
	}
	@Bean
	@StepScope
	public RetryItemProcessor processor(@Value("#{jobParameters['retry']}") String retry) {
		RetryItemProcessor processor = new  RetryItemProcessor();
		processor.setRetry(StringUtils.hasText(retry) && retry.equalsIgnoreCase("processor"));
		return processor;
	}
	
	@Bean
	@StepScope
	public RetryItemWriter writer(@Value("#{jobParameters['retry']}") String retry) {
		RetryItemWriter writer = new  RetryItemWriter();
		writer.setRetry(StringUtils.hasText(retry) && retry.equalsIgnoreCase("writer"));
		return writer;
	}
	
	
	@Bean
	public Step step1()  {
		return stepBuilderFactory.get("step1")
			.<String,String>chunk(10)
			.reader(reader())
			.processor(processor(null))
			.writer(writer(null))
			.faultTolerant()
			.retry(CustomRetryableException.class)
			.retryLimit(15)
			.build();
			
	}
	

	@Bean
	public Job job1() throws Exception {
		return jobBuilderFactory.get("newjob1")
				.start(step1())
				.build();
	}
}
