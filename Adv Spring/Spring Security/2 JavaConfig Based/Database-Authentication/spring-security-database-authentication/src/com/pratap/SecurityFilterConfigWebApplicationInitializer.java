package com.pratap;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import com.pratap.config.RootConfig;
import com.pratap.config.SecurityConfig;

public class SecurityFilterConfigWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

	public SecurityFilterConfigWebApplicationInitializer() {
		super(RootConfig.class, SecurityConfig.class);
	}

}


// create DelegatingFilterProxy
// create a root container , but also creates the spring beans configured in the config class