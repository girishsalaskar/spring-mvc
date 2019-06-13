package os.girish.practice.spring.mvc.todoapp.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import os.girish.practice.spring.mvc.todoapp.models.Todo;
import os.girish.practice.spring.mvc.todoapp.models.User;
import os.girish.practice.spring.mvc.todoapp.models.dao.TodoDao;
import os.girish.practice.spring.mvc.todoapp.models.dao.TodoDaoImplHBNet;

@Service
public class TodoService {

	@Autowired
	private TodoDao todoDao;

	private static Logger logger = Logger.getLogger(TodoService.class);

	public void setTodoDao(TodoDaoImplHBNet todo) {
		todoDao = todo;
	}

	public void saveDb(Todo todo) {
		todoDao.save(todo);
		logger.debug("Added in db");
	}

	public List<Todo> getAllDb() {
		logger.debug("List is to be return");
		return todoDao.fetchAll();
	}

	public List<Todo> getTodosByUser(User todo) {
		return todoDao.getByUser(todo);
	}
	
	public Todo getTodoById(int id) {
		return todoDao.getById(id);
	}
	
	public void deleteById(int id) {
		todoDao.deleteById(id);
	}
}
