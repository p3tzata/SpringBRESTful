package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.User;

@Service
public class UserServiceImpl implements UserService {

	private List<User> users;
	public UserServiceImpl() {
		users=new ArrayList<User>();
		users.add(new User(1L,"Ivo",new Date(System.currentTimeMillis())));
		users.add(new User(2L,"Gosho",new Date(System.currentTimeMillis())));
		users.add(new User(3L,"Ivan",new Date(System.currentTimeMillis())));
	}

	
	@Override
	public List<User> getAllUsers(){
		
		return Collections.unmodifiableList(users);
	}
	
	@Override
	public User getUser(Long userId) {
		
		 Optional<User> findAny = users.stream().filter(el-> el.getId()==userId).findAny();
		 if (findAny.isPresent()){
			 return findAny.get();
		 }
		 
		 return null;
		 
	}
	
}
