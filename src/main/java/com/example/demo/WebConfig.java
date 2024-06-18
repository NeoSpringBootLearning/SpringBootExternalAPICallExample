package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebConfig {

	    //webconfig
		@Bean
		public WebClient webClient() {
			
			WebClient webClient = WebClient.builder()
					.baseUrl("http://localhost:9090")
					.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.build();
			return webClient;
			
		}
}
