package com.pratap;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class AppConfig {
	// JdbcTemplate
	@Bean
	public JdbcTemplate template(DataSource ds) {
		return new JdbcTemplate(ds);
	}
	
	// DataSource to Embedded DB ( H2 )
	
	@Bean
	public DataSource ds() {
		EmbeddedDatabase ds = new EmbeddedDatabaseBuilder()
			.setType(EmbeddedDatabaseType.H2)
			.setName("sample-db")
			.generateUniqueName(true)
			// .addDefaultScripts()
			.addScript("data.sql")
			.ignoreFailedDrops(true)
			.build();
		return ds;
	}
}
