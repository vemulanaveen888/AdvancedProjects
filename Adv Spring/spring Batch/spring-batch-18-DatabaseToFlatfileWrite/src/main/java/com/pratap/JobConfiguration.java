package com.pratap;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.OraclePagingQueryProvider;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class JobConfiguration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	DataSource dataSource;


	@Bean
	public JdbcPagingItemReader<Account> pagingItemReader() {
		JdbcPagingItemReader<Account> reader = new JdbcPagingItemReader<>();
		reader.setDataSource(dataSource);
		reader.setFetchSize(10);
		reader.setRowMapper(new AccountRowMapper());

		OraclePagingQueryProvider queryProvider = new OraclePagingQueryProvider();
		queryProvider.setSelectClause("accid , name , balance");
		queryProvider.setFromClause("from account");

		Map<String, Order> sortKeys = new HashMap<>();
		sortKeys.put("accid", Order.ASCENDING);

		queryProvider.setSortKeys(sortKeys);
		reader.setQueryProvider(queryProvider);

		return reader;
	}

	@Bean
	public FlatFileItemWriter<Account> accountItemWriter() throws Exception{
		FlatFileItemWriter<Account> writer = new FlatFileItemWriter<>();
		writer.setLineAggregator(new PassThroughLineAggregator<>());
		String accountOutputPath = File.createTempFile("accountoutput", ".out").getAbsolutePath();
		System.out.println("output file >>: "+accountOutputPath);
		writer.setResource(new FileSystemResource(accountOutputPath));
		writer.afterPropertiesSet();
		return writer;
		
	}

	@Bean
	public Step step1() throws Exception {
		return stepBuilderFactory.get("step1")
				.<Account, Account>chunk(10)
				.reader(pagingItemReader())
				.writer(accountItemWriter()).build();

	}

	@Bean
	public Job job1() throws Exception {
		return jobBuilderFactory.get("myjob536").start(step1()).build();
	}
}
