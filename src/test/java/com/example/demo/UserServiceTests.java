package com.example.demo;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void test_getAllUsers() {
		
		List<User> allUsers = userService.getAllUsers();
		
		Assert.assertEquals(3, allUsers.size());
		
	}
	
	@Test
	public void test_getUser() {
		
		User user = userService.getUser(2L);
		
		Assert.assertEquals("Gosho", user.getUsername());
		
	}
	
	
}
