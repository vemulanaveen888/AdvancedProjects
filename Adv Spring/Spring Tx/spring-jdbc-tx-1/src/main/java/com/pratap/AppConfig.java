package com.pratap;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@ComponentScan("com.pratap")
public class AppConfig {

	@Bean
	public DataSource  dataSource() {
		DriverManagerDataSource  ds = new DriverManagerDataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
		ds.setUsername("system");
		ds.setPassword("pratap");
		return ds;
	}
	
	@Bean
	public JdbcTemplate  template() {
		return new JdbcTemplate(dataSource());
	}
	
	@Bean
	public PlatformTransactionManager  transactionManager() {
		return new DataSourceTransactionManager(dataSource());
		
	}
}
