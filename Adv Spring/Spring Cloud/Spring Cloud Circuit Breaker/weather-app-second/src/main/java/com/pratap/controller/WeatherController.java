package com.pratap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pratap.service.WeatherService;

@RestController
public class WeatherController {
	@Autowired
	private WeatherService service;
	
	@GetMapping("/second/current/weather")
	public String getWeather() {
		return "The current weather is : "+service.getWeather2();
	}
	@GetMapping("/second/current/weather/second")
	public String getWeatherSecond() {
		return "The current weather is : "+service.getWeatherSecond2();
	}
}
