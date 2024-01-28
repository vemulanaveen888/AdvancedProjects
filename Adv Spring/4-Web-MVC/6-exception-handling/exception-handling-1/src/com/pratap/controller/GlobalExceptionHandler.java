package com.pratap.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ArithmeticException.class)
	public String arithmeticExcetpionResolver() {
		System.out.println("arithmeticExceptionResovler");
		return "arithmetic";
	}
	
	// @InitBinder
	// @ModelAttribute
}
