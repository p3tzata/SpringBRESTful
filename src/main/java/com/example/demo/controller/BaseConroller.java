package com.example.demo.controller;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.common.aspect.AspectJWTrequierdAnnotation;
import com.example.demo.common.aspect.AspectTestUserAnnotation;
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
	
    private static final Logger _logger=LoggerFactory.getLogger(BaseConroller.class);
	
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
		_logger.info("[test] info log");
		return new ResponseEntity<>(allUsers,HttpStatus.OK);
		 
	}
	
	@GetMapping("/getUser/{userId}")
	@AspectTestUserAnnotation
	public ResponseEntity<?> getUser(@PathVariable Long userId) {
		
		 User user = userService.getUser(userId);
		
		 return new ResponseEntity<>(user,HttpStatus.OK);

	}
	
	@DeleteMapping("/deleteUser/{userId}")
	@AspectJWTrequierdAnnotation
	public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
		//Pseudo deleting.
		 HashMap<String, String> hashMap = new HashMap<>();
		 hashMap.put("result", "delete");
		
		 return new ResponseEntity<>(hashMap,HttpStatus.OK);

	}
	
	

	@GetMapping("/getUser_weekCacheValidator/{userId}")
	//Week because it's 1 second resolution
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
