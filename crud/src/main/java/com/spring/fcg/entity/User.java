package com.spring.fcg.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class User {
	@NotEmpty(message="用户名不能为空")
	private String username;
	@NotEmpty(message="密码不能为空")
	@Length(min=6, message="密码长度不能少于6位")
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User() {
		super();
	}
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	@Override
	public String toString() {
		return "user [username=" + username + ", password=" + password + "]";
	}
	
}
