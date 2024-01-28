package com.pratap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigClientController {
	@Autowired
	ConfigClientAppConfiguration properties;

	@Value("${some.other.property}")
	private String someOtherProperty;
	
	@RequestMapping("/")
	public String printConfig() {
		StringBuilder sb = new StringBuilder();
		sb.append(properties.getProperty());
		sb.append("|");
		sb.append(someOtherProperty);
		return sb.toString();
		
	}
	
}
