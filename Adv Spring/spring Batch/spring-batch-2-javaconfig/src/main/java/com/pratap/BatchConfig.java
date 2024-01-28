package com.pratap;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.SimpleJob;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class BatchConfig {

	@Bean
	public JobLauncher  launcher() throws Exception {
		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(getJobRepository());
        jobLauncher.afterPropertiesSet();
        return jobLauncher;
	}
	
	@Bean
	public Job  job1() throws Exception {
		SimpleJob  job = new  SimpleJob("job1");
		job.addStep(step1());
		job.setJobRepository(getJobRepository());
		return job;
	}
	@Bean
	public Step  step1() throws Exception {
		TaskletStep ts = new TaskletStep();
		ts.setJobRepository(getJobRepository());
		ts.setTransactionManager(transactionManager());
		ts.setTasklet(new HelloTasklet());
		return ts;
	}
	@Bean
	public JobRepository getJobRepository() throws Exception {
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDataSource(dataSource());
        factory.setTransactionManager(transactionManager());
        factory.afterPropertiesSet();
        return (JobRepository) factory.getObject();
    }
	
	@Bean
	public  DataSource  dataSource() {
		DriverManagerDataSource  ds = new  DriverManagerDataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
		ds.setUsername("system");
		ds.setPassword("pratap");
		return ds;
	}
	
	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
}
