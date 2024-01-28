package com.pratap;

import java.util.ArrayList;
import java.util.Scanner;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

public class AuthenticationSample {
	private static AuthenticationManager authenticationManager = new AuthenticationManagerImpl();

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter Username: ");
		String name = sc.nextLine();
		System.out.println("Please enter Password: ");
		String password = sc.nextLine();

		try {
			Authentication request = new UsernamePasswordAuthenticationToken(name, password);
			Authentication result = authenticationManager.authenticate(request);
			
			System.out.println("Response authentication object");
			System.out.println("authoriteis : 	"+result.getAuthorities());
			System.out.println("prinicipal : "+result.getPrincipal());
			System.out.println("credentials : "+result.getCredentials());
			System.out.println("authenticated : "+result.isAuthenticated());
			System.out.println("user details : "+result.getDetails());
			
			// this code will set the authentication object into the thread local
			SecurityContextHolder.getContext().setAuthentication(result);

		} catch (AuthenticationException e) {
			System.out.println("Authentication failed: " + e.getMessage());
			System.exit(1);
		}

		System.out.println("Successfully authenticated. Security context contains: "
				+ SecurityContextHolder.getContext().getAuthentication());
	}
}

class AuthenticationManagerImpl implements AuthenticationManager {

	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("pratap").password("password").roles("ADMIN").build());
		manager.createUser(User.withUsername("test").password("password").roles("USER").build());
		return manager;
	}

	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		System.out.println("Incoming auth details");
		System.out.println("authoriteis : 	"+auth.getAuthorities());
		System.out.println("prinicipal : "+auth.getPrincipal());
		System.out.println("credentials : "+auth.getCredentials());
		System.out.println("authenticated : "+auth.isAuthenticated());
		System.out.println("user details : "+auth.getDetails());
	
		UserDetails user=userDetailsService().loadUserByUsername(auth.getName());
		if(user !=null){
			if(user.getPassword().equals(auth.getCredentials())){
				if(user.getUsername().equals("pratap")){
				return new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials(), 
						new ArrayList<GrantedAuthority>(){
							{
								add(new SimpleGrantedAuthority("ROLE_ADMIN"));
							}
							});
				}
				else{
					System.out.println("User is"+user.getUsername());
					return new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials(), 
							new ArrayList<GrantedAuthority>(){
							{
								add(new SimpleGrantedAuthority("ROLE_USER"));
							}
							});
				}
			}
		}
		
		throw new BadCredentialsException("Bad Credentials");
		
	}
}
