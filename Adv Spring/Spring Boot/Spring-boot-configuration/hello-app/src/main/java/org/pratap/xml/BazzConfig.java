package org.pratap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BazzConfig {
	@Bean
	public Bazz bz() {
		return new Bazz();
	}
}
