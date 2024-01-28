package com.pratap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = {"com.pratap.controller","com.pratap.service","com.pratap.repository"})
public class AppServletConfig {

	@Bean
	public ViewResolver viewResolver() {
		return new InternalResourceViewResolver("/WEB-INF/jsps/", ".jsp");
	}
}


//			/WEB-INF/jsps/hellopage.jsp