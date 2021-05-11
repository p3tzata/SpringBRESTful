package com.example.demo.entity;

import java.util.Date;

public class User {
	Long id;
	String username;
	Date updateDate;
	
	
	public User(Long id, String username, Date updateDate) {
		super();
		this.id = id;
		this.username = username;
		this.updateDate=updateDate;
	}
	
	public User() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	
}
