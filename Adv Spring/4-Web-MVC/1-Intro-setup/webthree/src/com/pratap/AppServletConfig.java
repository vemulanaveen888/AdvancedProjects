package com.pratap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.pratap.interceptor.DemoInterceptor;

@Configuration
@ComponentScan(basePackages = {"com.pratap.controller"})
@EnableWebMvc
// @Import(DelegatingWebMvcConfiguration.class)
//  extends WebMvcConfigurationSupport
public class AppServletConfig implements WebMvcConfigurer{

	@Bean
	public ViewResolver vr() {
		return new InternalResourceViewResolver("/WEB-INF/jsps/", ".jsp");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new DemoInterceptor());
	}
}


