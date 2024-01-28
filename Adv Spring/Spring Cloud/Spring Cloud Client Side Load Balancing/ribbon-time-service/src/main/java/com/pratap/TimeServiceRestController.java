package com.pratap;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeServiceRestController {
	@Value("${server.port}")
	private int port;
	
	
	@RequestMapping("/")
	public String getCurrentTime() {
		return "The current time " + 
				new Date().toString()+" servcie running on "+port;

	}
}
