package com.pratap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pratap.service.MessageService;

@Controller
public class MessageController {
	@Autowired
	MessageService service;

	@RequestMapping("/message")
	public ModelAndView messageHandler() {
		String message = service.getMessageService();

		ModelAndView mav = new ModelAndView("messagepage");
		mav.addObject("messagekey", message);
		return mav;

	}
}
