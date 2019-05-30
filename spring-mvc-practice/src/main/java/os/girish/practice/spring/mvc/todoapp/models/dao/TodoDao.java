package os.girish.practice.spring.mvc.todoapp.models.dao;

import java.util.List;

import os.girish.practice.spring.mvc.todoapp.models.Todo;

public interface TodoDao {

	public void save(Todo todo);
	
	public List<Todo> fetchAll();
}
