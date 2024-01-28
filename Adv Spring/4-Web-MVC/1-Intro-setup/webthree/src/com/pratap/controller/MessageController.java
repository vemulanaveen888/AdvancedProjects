package com.pratap.controller;

import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MessageController {

	@Autowired
	WebApplicationContext context; // Spring Container

	@RequestMapping("/hello")
	public ModelAndView handleHelloRequest() {
		System.out.println("in handleHelloRequest");
		ModelAndView mav = new ModelAndView("hellopage");

		return mav;
	}

	@RequestMapping("/hi")
	public ModelAndView handleHiRequest() {
		System.out.println("in handleHiRequest");
		ModelAndView mav = new ModelAndView("hipage");
		return mav;
	}

	@RequestMapping("/checkcontainer")
	public void checkBeans(ServletResponse res) {

		System.out.println("in checkBeans");

		System.out.println(context.getBeanDefinitionCount());
		String[] names = context.getBeanDefinitionNames();
		for (String name : names)
			System.out.println(name);

	}

}
