package os.girish.practice.spring.mvc.todoapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import os.girish.practice.spring.mvc.todoapp.models.User;
import os.girish.practice.spring.mvc.todoapp.models.dao.UserDao;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public boolean validateUser(String user, String password) {
		return user.equalsIgnoreCase("admin") && password.equals("password");
	}

	public void saveUser(User user) {
		userDao.saveUser(user);
	}

	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}
	
	public User isValidUser(String userName, String password) {
		return userDao.findByUserNamePassword(userName, password);
	}
}