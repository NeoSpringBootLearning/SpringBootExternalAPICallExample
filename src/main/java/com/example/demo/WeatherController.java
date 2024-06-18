package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/weather/api/v1")
public class WeatherController {
	
		@Autowired
		private WeatherService weatherService;
		
		@GetMapping("/current")
		public String getWeatherDetails() {
			
			return "Current Weather is Hot and Humid";
		}
		@GetMapping("/currentcity")
		public ResponseEntity<WeatherResponse> getWeatherBasedOnCity(@RequestParam(value="q",defaultValue = "london") String q) throws JsonMappingException, JsonProcessingException {
			
				WeatherResponse response = weatherService.getWeatherBasedOnCity(q);
				return ResponseEntity.ok(response);
		}
		
		@GetMapping("/taskbyid/{id}")
		public ResponseEntity<Mono<ToDoEntity>> getTaskById(@PathVariable("id") int id)  {
			
				Mono<ToDoEntity> response = weatherService.getTaskById(id);
				return ResponseEntity.ok(response);
		}

}
