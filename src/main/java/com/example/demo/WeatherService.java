package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WeatherService {

		@Value("${key}")
		public String apiKey;
		
		public WeatherResponse getWeatherBasedOnCity(String q) throws JsonMappingException, JsonProcessingException {
			HttpHeaders headers = new HttpHeaders();
			headers.add("accept", MediaType.APPLICATION_JSON_VALUE);
			Map<String, String> parms = new HashMap<>();
			parms.put("q", "london");
			parms.put("key",apiKey);
			RestTemplate restTemplate = new RestTemplate();
			String result = restTemplate.getForObject("https://api.weatherapi.com/v1/timezone.json?q={q}&key={key}", String.class, parms);
			ObjectMapper mapper = new ObjectMapper();
			WeatherResponse response = mapper.readValue(result, new TypeReference<>() {});
			return response;
		}
 
}
