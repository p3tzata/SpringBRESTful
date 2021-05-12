package com.example.demo.common.bean;

import javax.servlet.Filter;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import com.sun.net.httpserver.Authenticator.Result;

@Configuration
public class CacheBean {

	@Bean
	public Filter shallowEtagHeaderFilter() {
		return new ShallowEtagHeaderFilter(); 
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public FilterRegistrationBean shallowEtagRegistration() {
		//Client will send header If-None-Match if MD hash is equal will response 304
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(this.shallowEtagHeaderFilter());
        filterRegistrationBean.addUrlPatterns("/public/baseTest/*");
		filterRegistrationBean.setName("shallowEtagHeaderFilter");
        filterRegistrationBean.setOrder(1);
		
		return filterRegistrationBean;
	}
	
	
	
}
