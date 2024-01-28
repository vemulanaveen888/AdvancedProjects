package com.pratap;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class JobLIstener implements JobExecutionListener{

	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println(">> before job");
		
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("<< after job");
		
	}

}
