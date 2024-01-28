package com.pratap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloServiceController {
	@RequestMapping
	public String hello() {
		return "Hello!";
	}
}
