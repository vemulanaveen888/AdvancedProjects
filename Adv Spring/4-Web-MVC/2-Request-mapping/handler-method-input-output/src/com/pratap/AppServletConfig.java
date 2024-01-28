package com.pratap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = { "com.pratap.controller" })
@EnableWebMvc
public class AppServletConfig implements WebMvcConfigurer {

	@Bean
	public ViewResolver vr() {
		return new InternalResourceViewResolver("/WEB-INF/jsps/", ".jsp");
	}

}
