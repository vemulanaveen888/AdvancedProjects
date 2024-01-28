package com.pratap;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.integration.async.AsyncItemProcessor;
import org.springframework.batch.integration.async.AsyncItemWriter;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.OraclePagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
public class JobConfiguration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	DataSource dataSource;

	@Bean
	public JdbcPagingItemReader<Account> reader() {
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
	public ItemProcessor<Account, Account> processor() {
		return new ItemProcessor<Account, Account>() {
			@Override
			public Account process(Account item) throws Exception {
				Thread.sleep(new Random().nextInt(10));
				return new Account(item.getAccid(), item.getName().toUpperCase(), item.getBalance());
			}
		};
	}

	@Bean
	public AsyncItemProcessor asyncItemProcessor() throws Exception {
		AsyncItemProcessor<Account, Account> processor = new AsyncItemProcessor<>();
		processor.setDelegate(processor());
		processor.setTaskExecutor(new SimpleAsyncTaskExecutor());
		processor.afterPropertiesSet();
		return processor;
	}

	@Bean
	public JdbcBatchItemWriter<Account> writer() {
		JdbcBatchItemWriter<Account> writer = new JdbcBatchItemWriter<>();
		writer.setDataSource(dataSource);
		writer.setSql("INSERT INTO NEW_CUSTOMER VALUES(:accid, :name , :balance)");
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
		writer.afterPropertiesSet();
		return writer;
	}

	@Bean
	public AsyncItemWriter asyncItemWriter() throws Exception {
		AsyncItemWriter<Account> writer = new AsyncItemWriter<>();
		writer.setDelegate(writer());
		writer.afterPropertiesSet();
		return writer;
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1")
				.<Account, Account>chunk(10)
				.reader(reader())
				.processor(processor())
				.writer(writer())
				// .taskExecutor(new SimpleAsyncTaskExecutor())
				.build();
	}
	
	// AsyncItemProcessor & AsyncItemWriter
	@Bean
	public Step step2() throws Exception {
		return stepBuilderFactory.get("step1")
				.chunk(10)
				.reader(reader())
				.processor(asyncItemProcessor())
				.writer(asyncItemWriter())
				// .taskExecutor(new SimpleAsyncTaskExecutor())
				.build();
	}

	@Bean
	public Job job1() throws Exception {
		return jobBuilderFactory.get("job1").start(step1()).build();
	}
}
