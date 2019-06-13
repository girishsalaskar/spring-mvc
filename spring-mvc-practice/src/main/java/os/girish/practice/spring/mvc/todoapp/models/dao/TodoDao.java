package os.girish.practice.spring.mvc.todoapp.models.dao;

import java.util.List;

import os.girish.practice.spring.mvc.todoapp.models.Todo;
import os.girish.practice.spring.mvc.todoapp.models.User;

public interface TodoDao {

	public void save(Todo todo);
	
	public List<Todo> fetchAll();
	
	public List<Todo> getByUser(User todo);
	
	public Todo getById(int id);

	public void deleteById(int id);
}
