package com.pratap;

import org.pratap.Bazz;
import org.pratap.xml.Sample;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication	// composed annotation
				// @SpringBootConfiguration  is a composed version of @Configuration
				// @EnableAutoConfiguration
				// @ComponentScan


public class App {	// Is a Configuration class

	public static void main(String[] args) {
		
		ConfigurableApplicationContext applicationContext = SpringApplication.run(App.class, args);
		
		System.out.println(applicationContext.getClass().getName());
			// AnnotationConfigServletWebServerApplicationContext ( spring-boot-starter-web )
			// AnnotationConfigReactiveWebServerApplicationContext ( spring-boot-starter-webflux )
			// AnnotationConfigApplicationContext	( spring-boot-starter )
		
		
		
		Foo f1 = applicationContext.getBean("f1", Foo.class);
		System.out.println(f1);
		
		Bar b1 = applicationContext.getBean("b1", Bar.class);
		System.out.println(b1);
		
		Bazz bz = applicationContext.getBean("bz", Bazz.class);
		System.out.println(bz);
		
		Sample samp1 = applicationContext.getBean("samp1", Sample.class);
		System.out.println(samp1);
	}
	
	@Bean
	public Foo  f1() {
		return new Foo();
	}
	
	
	

}
