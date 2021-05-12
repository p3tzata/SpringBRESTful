package com.example.demo.service.security;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Service
public class SecurityServiceImpl implements SecurityService {

	final private String secretKey="wifo80380ef";
	
	@Override
	public String createToken(String subject,long TTLMillis) {
		
	if (TTLMillis>0) {
		
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		
		byte[] apiKeySecretytes = DatatypeConverter.parseBase64Binary(secretKey);
		
		 Key signingKey = new SecretKeySpec(apiKeySecretytes, signatureAlgorithm.getJcaName());
		 
		JwtBuilder jwtBuilder = Jwts.builder()
		.setSubject(subject)
		.signWith(signatureAlgorithm, signingKey);
		
		long nowMillis=System.currentTimeMillis();
		jwtBuilder.setExpiration(new Date(nowMillis+TTLMillis));
		return jwtBuilder.compact();
		
		
	} else {
		
		throw new IllegalArgumentException("TTLMillis: must be greate then 0");
	}
		
	}

	@Override
	public String getSubjectFromJWT(String token) {
		
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
					 .parseClaimsJws(token)
					 .getBody();
		
		if(claims==null || claims.getSubject()==null) {
			throw new IllegalArgumentException("Token error: Claims or subject is null");
		}
		
		return claims.getSubject();
		
		
	}
	
	@Override
	public boolean checkClaims(String subject, String token) {
		
		String subjectFromJWT = this.getSubjectFromJWT(token);
		
		if(subjectFromJWT.equals(subject)) {
			return true;
		}
		
		return false;
		
	}
	
	
	
	
}
