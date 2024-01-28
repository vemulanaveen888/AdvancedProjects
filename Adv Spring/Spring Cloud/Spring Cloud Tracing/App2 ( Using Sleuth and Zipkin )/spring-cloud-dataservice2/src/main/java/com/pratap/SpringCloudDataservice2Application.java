package com.pratap;

import java.util.Hashtable;

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
public class SpringCloudDataservice2Application {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringCloudDataservice2Application.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudDataservice2Application.class, args);
	}

	@RequestMapping(value = "/customer/{cid}/vehicledetails", method = RequestMethod.GET)
	public @ResponseBody String getCustomerVehicleDetails(@PathVariable Integer cid) {
		LOGGER.info("entered getCustomerVehicleDetails ( DataService2)");
		Hashtable<Integer, String> vehicles = new Hashtable<Integer, String>();
		vehicles.put(100, "Lincoln Continental; Plate SNUG30");
		vehicles.put(101, "Chevrolet Camaro; Plate R7TYR43");
		vehicles.put(102, "Volkswagen Beetle; Plate 6CVI3E2");

		String result = vehicles.get(cid);
		LOGGER.info("exiting getCustomerVehicleDetails ( DataService1)");
		return result;
	}

}
