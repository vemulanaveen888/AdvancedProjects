package com.pratap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HelloServiceBootApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(HelloServiceBootApplication.class, args);

		HelloService service = context.getBean(HelloService.class);
		service.sayHello("pratap");
	}

	/*
	 * @Bean public HelloService helloService() { return new
	 * ConsoleHelloService("Howdy", "?"); }
	 */

}
