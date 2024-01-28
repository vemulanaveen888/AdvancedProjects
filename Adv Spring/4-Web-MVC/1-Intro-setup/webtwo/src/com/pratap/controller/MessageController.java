package com.pratap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MessageController {

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

}
