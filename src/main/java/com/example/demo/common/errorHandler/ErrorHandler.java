package com.example.demo.common.errorHandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class ErrorHandler {
	
	@SuppressWarnings("unchecked")
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Exception.class)
	public @ResponseBody <T> T errorHandler(Exception exception){
		
		
		Map<String,String> hashMap = new HashMap<>();
		
		if(exception instanceof org.springframework.web.bind.MissingServletRequestParameterException) {
			hashMap.put("error", "Missing parameters");
		} else {
			hashMap.put("error", "Generic error");
		}
		
		
		return (T) hashMap;
		
	}
	

}
