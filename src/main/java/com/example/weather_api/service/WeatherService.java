package com.example.weather_api.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.weather_api.dto.WeatherResponse;

@Service
public class WeatherService {

	@Autowired
	private RestTemplate restTemplate;
	
	 @Value("${weather.api.key}")
	private String apiKey;
	

	@Value("${weather.api.url}")
	private String apiUrl;
	
	
	 public WeatherResponse getWeather(String city) {

	        String url = apiUrl + "?q=" + city + "&appid=" + apiKey + "&units=metric";

	        String response = restTemplate.getForObject(url, String.class);
	        JSONObject json = new JSONObject(response);

	        WeatherResponse w = new WeatherResponse();

	        
	        w.setCity(city);
	        w.setCountry(json.getJSONObject("sys").getString("country"));

	        
	        w.setLat(json.getJSONObject("coord").get("lat").toString());
	        w.setLon(json.getJSONObject("coord").get("lon").toString());

	        // Temperature
	        JSONObject main = json.getJSONObject("main");
	        w.setTemperature(main.get("temp").toString());
	        w.setFeelsLike(main.get("feels_like").toString());
	        w.setTempMin(main.get("temp_min").toString());
	        w.setTempMax(main.get("temp_max").toString());
	        w.setHumidity(main.get("humidity").toString());
	        w.setPressure(main.get("pressure").toString());

	        
	        w.setWindSpeed(json.getJSONObject("wind").get("speed").toString());
	        w.setWindDegree(json.getJSONObject("wind").get("deg").toString());

	        
	        w.setCloudPercentage(json.getJSONObject("clouds").get("all").toString());

	        
	        if (json.has("visibility")) {
	            w.setVisibility(json.get("visibility").toString());
	        } else {
	            w.setVisibility("NA");
	        }

	        
	        w.setCondition(json.getJSONArray("weather").getJSONObject(0).getString("description"));

	        
	        w.setSunrise(json.getJSONObject("sys").get("sunrise").toString());
	        w.setSunset(json.getJSONObject("sys").get("sunset").toString());

	        return w;
	    }
	
}
