package com.pratap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {

	// @RequestMapping(path = "/hello")
	// @RequestMapping(path = {"/hello","/hi"})
	
	// By default @RequestMapping supports all the HTTP methods, 
	// To restrict mapping to certain HTTP method, use method attribute of @RequestMapping annotation
	
	// @RequestMapping(path = "/hello", method = RequestMethod.GET)
	
	@RequestMapping(path = "/hello", method = {RequestMethod.GET, RequestMethod.POST})
	public String handleHelloRequest() {
		System.out.println("From handleHelloRequest");
		return "hellopage"; // String: viewname
	}
	
	
	
	
	// @RequestMapping(path = "/hi", method = {RequestMethod.GET})
	@GetMapping(path="/hi")
	public String handleHiRequest() {
		System.out.println("From handleHiRequest");
		return "hipage"; // String: viewname
	}
	
	
	
	
	// @RequestMapping(path = "/create", method = RequestMethod.POST)
	@PostMapping("/create")
	public void handleCreateRequest() {
		System.out.println("From handleCreateRequest");
		
	}
	
	
	@GetMapping("/course?")
	public void handleSingleWildCardRequest() {
		System.out.println("From handleSingleWildCardRequest");	
	}
	
	@GetMapping("/spring*")		
	public void handleSpringRequest() {
		System.out.println("From handleSpringRequest");	
	}
	
	@GetMapping("/aws/*")	// GET  /aws/ec2	
	public void handleAWSRequest() {
		System.out.println("From handleAWSRequest");	
	}
	
	@GetMapping("/onlineclass/**")		// GET  /onlineclass/hyd/java
	public void handleOnlineclassRequest() {
		System.out.println("From handleOnlineclassRequest");	
	}

	
	
	//  Mapping using url + method + params
	
	@GetMapping(path = "/paramcountry" , params = "country")		// GET /params?city  
	public void handleRequestWithParamCountryPreesent() {
		System.out.println("From handleRequestWithParamCountryPreesent");	
	}
	
	@GetMapping(path = "/paramnotstate" , params = "!state")		// GET /params?city  
	public void handleRequestWithParamStateAbsent() {
		System.out.println("From handleRequestWithParamCountryPreesent");	
	}
	
	@GetMapping(path = "/params" , params = "city=hyd")		// GET /params?city  
	public void handleRequestWithParams() {
		System.out.println("From handleRequestWithParams");	
	}

	@GetMapping(path = "/multiparams" , params = {"city=hyd","state=TG"})		// GET /params?city  
	public void handleRequestWithMultiParams() {
		System.out.println("From handleRequestWithParams");	
	}

	
	//  Mapping using url + method + params + headers
	
	@GetMapping(path = "/paramwithheader" , params = {"city=hyd"}, headers = "key1=value1")		// GET /params?city  
	public void handleRequestWithParamWithHeader() {
		System.out.println("From handleRequestWithParamWithHeader");	
	}
	
	
	
	//  Mapping using url + method + produces
	// The "produces" attribute of the mapping annotation is highly usefull for Rest ful application
	
	// The client need to send an additional HTTP Header named "Accept=application/json"
	
	@GetMapping(path = "/produces" , produces = "application/json")		// GET /produces  
	public void handleRequestWithProduces() {
		System.out.println("From handleRequestWithProduces");	
	}
	
	
	// The client need to send an additional HTTP Header named "content_type=application/json"
	@PostMapping(path = "/consumes" , consumes = "application/json")		// POST /consumes  
	public void handleRequestWithConsumes() {
		System.out.println("From handleRequestWithConsumes");	
	}
}
