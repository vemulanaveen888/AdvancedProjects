package com.pratap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	@RequestMapping("/hello")
	public String handleRequest() {
		return "hellopage";
	}
	@RequestMapping("/")
	public String handleHomeRequest() {
		return "homepage";
	}
}
