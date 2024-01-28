package com.pratap.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloService {
	@Value("${service.instance.name}")
	private String instance;

	@RequestMapping("/")
	public String helloSerive() {
		return "Hello from, "+instance;
	}
}
