package com.pratap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.xstream.XStreamMarshaller;

@Configuration
public class JobConfiguration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	
	@Bean
	public StaxEventItemReader<Account> accountItemReader() {
		XStreamMarshaller unmarshaller = new  XStreamMarshaller();// xstream/ castor 
		Map<String, Class> aliases = new HashMap<>();
		aliases.put("account"	, Account.class); // what tag to be mapped what class object
		unmarshaller.setAliases(aliases);
		
		StaxEventItemReader<Account> reader = new StaxEventItemReader<>();
		reader.setResource(new ClassPathResource("accounts.xml"));
		reader.setFragmentRootElementName("account"); // what each chunk is delineted with
		reader.setUnmarshaller(unmarshaller);
		 
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
		return jobBuilderFactory.get("myjob77").start(step1()).build();
	}
}
