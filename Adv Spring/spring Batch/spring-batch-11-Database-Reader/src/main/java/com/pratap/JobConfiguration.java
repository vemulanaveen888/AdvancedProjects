package com.pratap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.OraclePagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfiguration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private DataSource dataSource;

	@Bean
	public JdbcCursorItemReader<Account> cursorItemReader() {
		JdbcCursorItemReader<Account> reader = new JdbcCursorItemReader<>();
		reader.setSql("SELECT ACCID, NAME, BALANCE FROM ACCOUNT ORDER BY ACCID");
		reader.setDataSource(dataSource);
		reader.setRowMapper(new AccountRowMapper());
		return reader;
	}

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
	public ItemWriter<Account> accountItemWriter() {
		return new ItemWriter<Account>() {
			public void write(List<? extends Account> items) throws Exception {
				for (Account account : items)
					System.out.println(account);
			}
		};
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1")
				.<Account, Account>chunk(10)
				 //.reader(cursorItemReader())
				.reader(pagingItemReader())
				.writer(accountItemWriter()).build();

	}

	@Bean
	public Job job1() {
		return jobBuilderFactory.get("myjob5446").start(step1()).build();
	}
}
