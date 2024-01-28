package com.pratap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.pratap.interceptors.Interceptor1;
import com.pratap.interceptors.Interceptor2;

@Configuration
@ComponentScan(basePackages = "com.pratap")
@EnableWebMvc
public class AppServletConfig implements WebMvcConfigurer{

	@Bean
	public ViewResolver viewResolver() {
		return new InternalResourceViewResolver("/WEB-INF/jsps/", ".jsp");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new Interceptor1()).addPathPatterns("/hello");
		registry.addInterceptor(new Interceptor2());
	}
}
