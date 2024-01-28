package com.pratap;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
public class AppConfig {
	@Bean
	public JdbcTemplate template(DataSource ds) {
		JdbcTemplate template = new JdbcTemplate(ds);
		return template;
	}
	/*
	 * @Bean public DataSource ds() { DriverManagerDataSource ds = new
	 * DriverManagerDataSource();
	 * ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
	 * ds.setUrl("jdbc:oracle:thin:@localhost:1521:orcl"); ds.setUsername("system");
	 * ds.setPassword("pratap"); return ds; }
	 */

	/*
	 * @Bean public DataSource ds() { BasicDataSource ds = new BasicDataSource();
	 * ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
	 * ds.setUrl("jdbc:oracle:thin:@localhost:1521:orcl"); ds.setUsername("system");
	 * ds.setPassword("pratap"); return ds; }
	 */
	/*
	 * @Bean public DataSource ds() { HikariDataSource ds = new HikariDataSource();
	 * ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
	 * ds.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:orcl");
	 * ds.setUsername("system"); ds.setPassword("pratap"); return ds; }
	 */

	@Bean
	public DataSource ds() throws PropertyVetoException {
		ComboPooledDataSource ds = new ComboPooledDataSource();
		ds.setDriverClass("oracle.jdbc.driver.OracleDriver");
		ds.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:orcl");
		ds.setUser("system");
		ds.setPassword("pratap");
		return ds;
	}

	// Dev + embeded DB
	@Bean
	public DataSourceInitializer dataSourceInitializer(DataSource ds) {
		DataSourceInitializer dsi = new DataSourceInitializer();
		dsi.setDataSource(ds);
		dsi.setDatabasePopulator(dbPopulator());
		return dsi;
	}

	private DatabasePopulator dbPopulator() {
		ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
		resourceDatabasePopulator.addScript(new ClassPathResource("data.sql"));
		
		return resourceDatabasePopulator;
	}
}
