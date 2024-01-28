package com.pratap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HiController {

	@RequestMapping("/hi")
	public String handleHiRequest() {
		System.out.println("in handleHiRequest");
		return "hipage";
	}
}
