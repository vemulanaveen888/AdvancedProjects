package com.pratap;

import java.util.Hashtable;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringCloudDataservice1Application {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringCloudDataservice1Application.class);
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudDataservice1Application.class, args);
	}

	@RequestMapping(value="/customer/{cid}/contactdetails", method=RequestMethod.GET)
	public @ResponseBody String getCustomerContactDetails(@PathVariable Integer cid) throws InterruptedException {
		LOGGER.info("entered getCustomerContactDetails ( DataService1) ");
		//add arbitrary latency
		Random r = new Random();
		int multiplier = r.nextInt(5) * 1000;
		System.out.println("multiplier: " + multiplier);
		Thread.sleep(multiplier);
		 
		
		Hashtable<Integer, String> customers = new Hashtable<Integer, String>();
		customers.put(100, "Beverly Goldberg");
		customers.put(101, "Dave Kim");
		customers.put(102, "Lainey Lewis");
		
		String result = customers.get(cid);
		LOGGER.info("exiting getCustomerContactDetails ( DataService1)");
		return result;
	}
}
