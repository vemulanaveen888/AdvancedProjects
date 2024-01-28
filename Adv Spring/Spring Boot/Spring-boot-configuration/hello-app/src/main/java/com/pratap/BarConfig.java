package com.pratap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BarConfig {
	@Bean
	public Bar b1() {
		return new Bar();
	}
}
