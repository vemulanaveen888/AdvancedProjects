package com.pratap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	
	@RequestMapping("/hello")
	public String handleHelloRequest() {
		System.out.println("in handleHelloRequest");
		return "hellopage";
	}
}

 
