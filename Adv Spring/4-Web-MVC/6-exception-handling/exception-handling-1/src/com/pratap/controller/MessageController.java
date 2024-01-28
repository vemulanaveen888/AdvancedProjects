package com.pratap.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.pratap.exception.FooException;

@Controller
public class MessageController {

	@GetMapping("/hello")
	public String handleHelloRequest() {
		System.out.println("in handleHelloRequest");
		if (true) {
			throw new ArithmeticException();
		}
		return "hellopage";
	}
	
	@GetMapping("/bye")
	public String handleByeRequest() {
		System.out.println("in handleByeRequest");
		if (true) {
			throw new NullPointerException();
		}
		return "hellopage";
	}
	
	@GetMapping("/seeyou")
	public String handleSeeYouRequest() {
		System.out.println("in handleSeeYouRequest");
		if (true) {
			throw new SeeYouException();
		}
		return "seeyoupage";
	}
	
	// if the request content_type is not "application/xml", spring raise HttpMediaTypeNotSupportedException
	
	@GetMapping(path = "/message", consumes = "application/xml")
	public String handleMessageRequest() {
		return "messagepage";
	}
	
	
	@GetMapping(path="/foo")
	public String handleFooRequest() {
		System.out.println("in handleFooRequest");
		if(true)
			throw new FooException();
		return "foopage";
	}
	
	@ExceptionHandler(NullPointerException.class)
	@ResponseStatus(code = HttpStatus.CREATED)
	public ModelAndView nullPointerHandler() {
		ModelAndView mav = new ModelAndView("nullpointerpage");
		mav.addObject("name", "pratap");
		return mav;
		// return "nullpointerpage";
	}

}
