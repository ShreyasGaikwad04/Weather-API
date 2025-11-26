package com.example.weather_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.weather_api.dto.WeatherResponse;
import com.example.weather_api.service.WeatherService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class WeatherController {

	  @Autowired
	    private WeatherService weatherService;

	    @GetMapping("/city")
	    public WeatherResponse getWeather(@RequestParam String city) {
	        return weatherService.getWeather(city);
	    }
	    
	    
}
