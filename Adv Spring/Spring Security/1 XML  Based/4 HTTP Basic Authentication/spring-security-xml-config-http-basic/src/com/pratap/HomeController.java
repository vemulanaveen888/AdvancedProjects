package com.pratap;

import java.util.Iterator;
import java.util.List;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

@Controller
public class HomeController {

	@Autowired
	WebApplicationContext context;

	@RequestMapping("/")
	public String sayHello() {
		
		FilterChainProxy filterChainProxy = context.getBean("springSecurityFilterChain", FilterChainProxy.class);
		List<SecurityFilterChain> filterChains = filterChainProxy.getFilterChains();
		for (SecurityFilterChain filterChain : filterChains) {
			System.out.println(filterChain);
			List<Filter> filters = filterChain.getFilters();
			System.out.println("Number of filters : "+filters.size());
			for (Filter filter : filters) {
				System.out.println(filter);
			}
		}
		return "hellopage";
	}
}
