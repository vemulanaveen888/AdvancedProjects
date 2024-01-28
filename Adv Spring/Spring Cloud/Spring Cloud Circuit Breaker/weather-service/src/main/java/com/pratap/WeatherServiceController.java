package com.pratap;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherServiceController {
	private String[] weather =new String[]{"sunny", "rainy" , "cloudy" , "windy"};
	
	@GetMapping("/weather")
	public String getWeather() {
		int rand = ThreadLocalRandom.current().nextInt(weather.length);
		return weather[rand];
	}
}
