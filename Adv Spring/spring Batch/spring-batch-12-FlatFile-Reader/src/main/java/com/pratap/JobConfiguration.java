package com.pratap;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
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

	
	@Bean
	public FlatFileItemReader<Account> accountItemReader() {
		FlatFileItemReader<Account> reader  = new FlatFileItemReader<>();
		reader.setLinesToSkip(1);
		reader.setResource(new ClassPathResource("account.csv"));
		
		DefaultLineMapper<Account> accountLineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		tokenizer.setDelimiter(",");
		tokenizer.setNames("accid","name" ,"balance");
		
		accountLineMapper.setLineTokenizer(tokenizer);
	
		accountLineMapper.setFieldSetMapper(new AccountFieldSetMapper());
	
		accountLineMapper.afterPropertiesSet();
		
		reader.setLineMapper(accountLineMapper);
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
				.<Account,Account>chunk(10)
				.reader(accountItemReader())
				.writer(accountItemWriter())
				.build();
	}

	@Bean
	public Job job1() {
		return jobBuilderFactory.get("myjob666").start(step1()).build();
	}
}
