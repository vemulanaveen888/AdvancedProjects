package com.pratap.controller;

import java.util.List;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

@Controller
public class HelloController {
	
	@Autowired
	WebApplicationContext context;
	
	@RequestMapping("/")
	public String homeMapping() {
		System.out.println("in homeMapping method");
		
		FilterChainProxy filterChainProxy = context.getBean("springSecurityFilterChain", FilterChainProxy.class);
		
		
		List<SecurityFilterChain> filterChains = filterChainProxy.getFilterChains();
		System.out.println("Number of Filterchain : "+filterChains.size());
		
		for(SecurityFilterChain filterChain : filterChains) {
			System.out.println("Fitler chain for request path : "+ filterChain.toString());
			List<Filter> filters = filterChain.getFilters();
			for(Filter filter : filters) {
				System.out.println(filter.getClass().getName());
			}
		}
		
		
		return "homepage";
	}
}
