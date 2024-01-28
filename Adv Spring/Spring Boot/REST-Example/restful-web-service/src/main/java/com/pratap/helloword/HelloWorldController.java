package com.pratap.helloword;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	// http://localhost:8080/hello-world
	
	//@RequestMapping(path = "/hello-world" , method = RequestMethod.GET)
	@GetMapping(path="/hello-world")
	public String helloWorld() {
		return "Hello World";
	}
	
	// finalize the HTTP method	GET
	// confirm URL	"hello-world-bean"
	// localhost:8080/hello-world-bean
	@GetMapping(path="/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("HelloWorldBean");
	}
	
	
	@GetMapping(path="/hello-world-bean/path-variable/{name}")
	public HelloWorldBean helloWorldBeanWithPathVariable(@PathVariable("name") String name  ) {
		return new HelloWorldBean(String.format("Hello World , %s",name));
	}
}
