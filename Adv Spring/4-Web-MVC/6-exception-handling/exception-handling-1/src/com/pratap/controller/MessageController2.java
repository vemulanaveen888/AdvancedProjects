package com.pratap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MessageController2 {

	@GetMapping("/hi")
	public String handleHiRequest() {
		System.out.println("in handleHiRequest");

		if (true) {
			throw new ArithmeticException();
		}
		return "hipage";
	}

}
