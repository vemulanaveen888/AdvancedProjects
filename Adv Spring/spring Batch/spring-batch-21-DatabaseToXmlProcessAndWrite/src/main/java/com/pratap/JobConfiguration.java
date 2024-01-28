package com.pratap;

import java.io.File;
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
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.xstream.XStreamMarshaller;

@Configuration
public class JobConfiguration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private DataSource dataSource;

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
	public StaxEventItemWriter<Account> accountItemWriter() throws Exception {
		XStreamMarshaller marshaller = new XStreamMarshaller();
		Map<String, Class> aliases = new HashMap<>();
		aliases.put("account", Account.class);
		marshaller.setAliases(aliases);

		StaxEventItemWriter<Account> writer = new StaxEventItemWriter<>();
		writer.setRootTagName("accounts");
		writer.setMarshaller(marshaller);

		String accountOutputPath = File.createTempFile("accountoutput", ".xml").getAbsolutePath();
		System.out.println(">> output path : 	" + accountOutputPath);

		writer.setResource(new FileSystemResource(accountOutputPath));
		writer.afterPropertiesSet();

		return writer;
	}

	/*
	 * @Bean public UpperCaseItemProcessor itemProcessor() { return new
	 * UpperCaseItemProcessor(); }
	 */

	/*
	 * @Bean public FilteringItemProcessor itemProcessor() { return new
	 * FilteringItemProcessor(); }
	 */

	@Bean
	public ValidatingItemProcessor<Account> itemProcessor() {
		ValidatingItemProcessor<Account> accountValidatingItemProcessor 
				= new ValidatingItemProcessor<Account>(new AccountValidator());
		accountValidatingItemProcessor.setFilter(true);
		return accountValidatingItemProcessor;
	}

	/*
	 * @Bean public CompositeItemProcessor<Account, Account> itemProcessor() throws
	 * Exception { List<ItemProcessor<Account, Account>> delegates = new
	 * ArrayList<>(); delegates.add(new FilteringItemProcessor()); delegates.add(new
	 * UpperCaseItemProcessor());
	 * 
	 * CompositeItemProcessor<Account, Account> processor = new
	 * CompositeItemProcessor<>(); processor.setDelegates(delegates);
	 * processor.afterPropertiesSet();
	 * 
	 * return processor; }
	 */

	@Bean
	public Step step1() throws Exception {
		return stepBuilderFactory.get("step1").<Account, Account>chunk(10).reader(pagingItemReader())
				.processor(itemProcessor()).writer(accountItemWriter()).build();

	}

	@Bean
	public Job job1() throws Exception {
		return jobBuilderFactory.get("myjob4584474").start(step1()).build();
	}
}
