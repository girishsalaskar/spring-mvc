package com.sucodar.practice.spring.mvc.jee;

public class LoginService {

	public boolean validateUser(String user, String password) {
		return user.equalsIgnoreCase("test") && password.equals("test");
	}
}
