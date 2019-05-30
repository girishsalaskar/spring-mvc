package os.girish.practice.spring.mvc.todoapp.services;

import org.springframework.stereotype.Service;

@Service
public class LoginService {
	public boolean validateUser(String user, String password) {
		return user.equalsIgnoreCase("admin") && password.equals("password");
	}

}