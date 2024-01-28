package com.pratap.controller;

import java.util.List;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.WebApplicationContext;

@Controller
public class HelloController {
	@Autowired
	WebApplicationContext context;
	
	@GetMapping("/")
	public String home() {
		return "homepage";
		
	}
	@GetMapping("/hello")
	public String sayHello() {
		System.out.println("in sayHello");
		
		FilterChainProxy filterChainProxy = context.getBean("springSecurityFilterChain", FilterChainProxy.class);
		//System.out.println(filterChainProxy);
		
		List<SecurityFilterChain> filterChains = filterChainProxy.getFilterChains();
		
		for(SecurityFilterChain filterchain : filterChains) {
			System.out.println("filter chain : "+filterchain);
			
			List<Filter> filters = filterchain.getFilters();
			for(Filter filter : filters) {
				System.out.println("filter : "+filter);
			}
		}
		
		return "hellopage";
	}
}
