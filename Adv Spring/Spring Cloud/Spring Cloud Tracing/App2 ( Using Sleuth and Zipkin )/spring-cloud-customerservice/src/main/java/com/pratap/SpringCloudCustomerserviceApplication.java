package com.pratap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class SpringCloudCustomerserviceApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringCloudCustomerserviceApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudCustomerserviceApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Autowired
	RestTemplate webTemplate;

	@RequestMapping(value = "/customer/{cid}", method = RequestMethod.GET)
	public @ResponseBody String getCustomer(@PathVariable Integer cid) {
		LOGGER.info("entered Calling Dataservice");
		
		ResponseEntity<String> contact = webTemplate
				.getForEntity("http://localhost:8081/fastpass/customer/" + cid + "/contactdetails", String.class);
		ResponseEntity<String> vehicle = webTemplate
				.getForEntity("http://localhost:8082/fastpass/customer/" + cid + "/vehicledetails", String.class);

		LOGGER.info("existing Calling Dataservice");
		
		return contact.getBody() + " | " + vehicle.getBody();

	}

}
