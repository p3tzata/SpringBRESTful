package com.example.demo.common.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Misc {

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
}
