package com.example.demo;

import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class WeatherService {

		@Value("${key}")
		public String apiKey;
		
		@Autowired
		private WebClient webClient;
		//Calling weatherapi using RestTemplate
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
		
		
		//Calling external service api using webclient using reactive way
		public Mono<ToDoEntity> getTaskById(int id){
			
			return webClient.get()
						.uri("api/v1/task/{id}",id)
						.retrieve()
						.onStatus(httpStatus -> !httpStatus.is2xxSuccessful(), clientResponse -> handleErrorResponse(clientResponse.statusCode()))
						.bodyToMono(ToDoEntity.class);
			
		}
		
		 private Mono<? extends Throwable> handleErrorResponse(HttpStatusCode httpStatusCode) {

			    // Handle non-success status codes here (e.g., logging or custom error handling)
			    return Mono.error(new TodoServiceException("Failed to fetch employee. Status code: " + httpStatusCode));
			  }
 
}
