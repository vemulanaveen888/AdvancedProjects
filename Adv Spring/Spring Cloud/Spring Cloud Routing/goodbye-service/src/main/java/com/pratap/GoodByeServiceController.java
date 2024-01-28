package com.pratap;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoodByeServiceController {

	@RequestMapping("{name}")
	public String goodBye(@PathVariable String name) {
		return "Good bye! "+name;
	}
}
