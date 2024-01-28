package com.pratap;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class JobConfiguration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private DataSource dataSource;
	
	@Bean
	public FlatFileItemReader<Account> accountItemReader() {
		FlatFileItemReader<Account> reader  = new FlatFileItemReader<>();
		reader.setLinesToSkip(1);
		reader.setResource(new ClassPathResource("account.csv"));
		
		DefaultLineMapper<Account> accountLineMapper = new DefaultLineMapper<>();
		
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		tokenizer.setNames("accid","name" ,"balance");
		accountLineMapper.setLineTokenizer(tokenizer);
		
		accountLineMapper.setFieldSetMapper(new AccountFieldSetMapper());
		
		accountLineMapper.afterPropertiesSet();
		
		reader.setLineMapper(accountLineMapper);
		return reader;	
	}

	@Bean
	public JdbcBatchItemWriter<Account> accountItemWriter() {
		JdbcBatchItemWriter<Account> itemWriter = new JdbcBatchItemWriter<>();
		itemWriter.setDataSource(dataSource);
		itemWriter.setSql("INSERT INTO ACCOUNT VALUES(:accid , :name , :balance)");
		itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
		itemWriter.afterPropertiesSet();
		return itemWriter;
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1")
				.<Account,Account>chunk(10)
				.reader(accountItemReader())
				.writer(accountItemWriter())
				.build();
	}

	@Bean
	public Job job1() {
		return jobBuilderFactory.get("myjob678").start(step1()).build();
	}
}
