package com.example.demo.service.security;

public interface SecurityService {

	String createToken(String subject, long TTLMillis);
	
	String getSubjectFromJWT(String token);

}