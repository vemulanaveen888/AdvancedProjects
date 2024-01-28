package com.pratap;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class JobConfiguration {

	@Autowired
	JobBuilderFactory  jobBuilderFactory;
	
	@Autowired
	StepBuilderFactory  setpBuilderFactory;
	
	@Bean
	public Step  step1() {
		return setpBuilderFactory.get("step1")
					.tasklet(new  HelloTasklet())
					.build();
	}
	
	@Bean
	public Job  job1() {
		return jobBuilderFactory.get("job1").start(step1()).build();
	}
	
}
