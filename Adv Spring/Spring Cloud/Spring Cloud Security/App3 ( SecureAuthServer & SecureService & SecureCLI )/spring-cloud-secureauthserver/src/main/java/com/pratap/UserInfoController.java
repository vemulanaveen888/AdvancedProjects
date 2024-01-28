package com.pratap;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoController {
	@RequestMapping("/user")
	public Principal user(Principal user) {
		System.out.println("/user endpoint invoked");
		return user;
	}
}
