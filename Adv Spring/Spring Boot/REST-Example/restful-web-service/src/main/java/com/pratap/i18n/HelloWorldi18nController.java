package com.pratap.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldi18nController {
	
	@Autowired
	MessageSource  ms;
	
	@GetMapping(path="/hello-world-internationalized")
	public String helloWorldInternationalized() {
		return ms.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}
}
