package com.example.demo.controller;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.service.security.SecurityService;


@RestController
@RequestMapping(value = "/public/baseTest",produces = "application/json")
public class BaseConroller {

	@Autowired
	SecurityService securityService;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/generateJWT")
	public ResponseEntity<?> generateJWT() {
		
		String createToken = securityService.createToken("TestSubject", 5*60*1000);
		Map<String, String> map =new HashMap<>();
		map.put("JWT", createToken);
		
		 return new ResponseEntity<>(map,HttpStatus.OK);

	}

	@GetMapping("/getSubjectByToken")
	public ResponseEntity<?> getSubjectByToken(@RequestParam("token") String token ) {
		
		
		String subjectFromJWT = securityService.getSubjectFromJWT(token);
		
		 return new ResponseEntity<>(subjectFromJWT,HttpStatus.OK);

	}
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<?> getAllUsers() {
		
		List<User> allUsers = userService.getAllUsers();
		
		 return new ResponseEntity<>(allUsers,HttpStatus.OK);

	}
	

	@GetMapping("/getUser/{userId}")
	public ResponseEntity<?> getAllUsers(@PathVariable Long userId, WebRequest webRequest) {
		
		 User user = userService.getUser(userId);
		 // Check if client send header If-Modified-Since = Wed, 5 Oct 2022 07:28:00 GMT
		 boolean checkNotModified = webRequest.checkNotModified(user.getUpdateDate().getTime());
		 System.out.println(checkNotModified);
		 
		 if (checkNotModified) {
			 return null;
		 } 
		
		 return new ResponseEntity<>(user,HttpStatus.OK);

	}

	
	
	
	
	
}
