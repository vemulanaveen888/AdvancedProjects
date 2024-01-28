package com.pratap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pratap.service.MessageService;

@Controller
public class MessageController {
	@Autowired
	private MessageService messgeService;

	@RequestMapping("/hello")
	public ModelAndView handleHelloRequest() {
		System.out.println("in handleHelloRequest");
		String message = messgeService.processHelloMesage();

		ModelAndView mav = new ModelAndView("hellopage");
		mav.addObject("messagekey", message);

		// return "hellopage"; //viewname ( DS needs to resolve a viewname to a View
		// object )
		return mav;
	}

	@RequestMapping("/hi")
	public ModelAndView handleHiRequest() {
		System.out.println("in handleHiRequest");

		String message = messgeService.processHiMesage();

		ModelAndView mav = new ModelAndView("hipage");
		mav.addObject("messagekey", message);
		// return "hipage";
		return mav;
	}

}
