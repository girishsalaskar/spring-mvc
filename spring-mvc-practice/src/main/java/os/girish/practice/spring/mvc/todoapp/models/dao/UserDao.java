package os.girish.practice.spring.mvc.todoapp.models.dao;

import java.util.List;

import os.girish.practice.spring.mvc.todoapp.models.User;

public interface UserDao {

	public List<User> getAllUsers();
	public default void saveUser(User user) {
		
	}
	
	public User findByUserId(String userId);
	
	public default User findById(int userId) {
		return null;
	}
	
	public default User findByUserNamePassword(String userId, String password) {
		return null;
	}
}
