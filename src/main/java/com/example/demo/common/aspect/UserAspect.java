package com.example.demo.common.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContext;

import com.example.demo.service.security.SecurityService; 
//<!-- https://mvnrepository.com/artifact/org.aspectj/aspectjweaver -->
//Use to add new behavior to existing code without modifying the code itself.
@Component
@Aspect
public class UserAspect {

	@Autowired
	private SecurityService securityService;
	
	@Before(value = "execution(* com.example.demo.controller.BaseConroller.getAllUsers())")
	public void testUserAspect() {
		
		System.out.println("Test userAspect execution");
	}
	
	@Before(value = "@annotation(aspectTestUserAnnotation)")
	//This will be called every time when @AspectTestUserAnnotation is used  
    public void testUserAspectMarkedWithAnnotation(AspectTestUserAnnotation aspectTestUserAnnotation) {
		
		System.out.println("Test userAspect market with annotation");
	}
	
	
	@Before(value = "@annotation(aspectJWTrequierdAnnotation)")
	//This will be called every time when @AspectTestUserAnnotation is used  
    public void testUserAspectMarkedWithAnnotation(AspectJWTrequierdAnnotation aspectJWTrequierdAnnotation) {
		
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		String tokenString = request.getHeader("Token");
		
		if (!StringUtils.hasLength(tokenString)) {
			throw new IllegalArgumentException("Missing token header");
		}
		
		if (!securityService.checkClaims("TestSubject", tokenString)) {
			throw new IllegalArgumentException("Subject in token is wrong...");
		}
		
		System.out.println("Test userAspect market with @AspectJWTrequierdAnnotation");
		
	}
	
}
