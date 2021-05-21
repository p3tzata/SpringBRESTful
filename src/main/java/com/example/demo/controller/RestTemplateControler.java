package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.User;

@RestController
@RequestMapping(value = "/public/restTemplate", produces = "application/json")
public class RestTemplateControler {
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/testGet")
	public ResponseEntity<?> testGet(@RequestParam(value="userId") Long userId){

		User user = restTemplate.getForObject("http://127.0.0.1:8080/public/baseTest/getUser/"+userId, User.class);
		
		return new ResponseEntity<>(user,HttpStatus.OK);
		
	}
	
}
