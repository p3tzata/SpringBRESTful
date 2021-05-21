package com.example.demo;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;


import org.jsoup.Jsoup;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

//@SpringBootTest
//@RunWith(SpringRunner.class)
//@TestInstance(Lifecycle.PER_CLASS) //To be allowed @BeforeAll
public class BaseControllerTests {
	
	//@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;
	private Gson gson;
	
	//@BeforeAll
	
	public void setUp() {
		this.mockMvc=MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
		this.gson=new Gson();
	}
	
	//@Test
	public void test_generateJWT() throws Exception {
	
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/public/baseTest/generateJWT"))
		            .andExpect(status().isOk())
		            .andReturn();
		
		
		
	}
	

	
	//@Test
	public void test_getAllUsers() throws IOException {
		
		String text = Jsoup.connect("http://127.0.0.1:8080/public/baseTest/getAllUsers")
		     .ignoreContentType(true)
		     .get()
		     .body()
		     .text();
		
		JsonArray asJsonArray = JsonParser.parseString(text).getAsJsonArray();
		Assert.assertEquals(3, asJsonArray.size());
		
		
		
	}
	
	
	//@Test
	public void test_getUser() throws IOException {
		
		String text = Jsoup.connect("http://127.0.0.1:8080/public/baseTest/getUser/1")
		     .ignoreContentType(true)
		     .get()
		     .body()
		     .text();
		System.out.println(text);
		User user = gson.fromJson(text, User.class);
		Assert.assertEquals("Ivo", user.getUsername());
		
		
		
	}
	
	
	
}
