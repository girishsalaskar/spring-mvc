package os.girish.practice.spring.mvc.todoapp.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import os.girish.practice.spring.mvc.todoapp.models.Todo;
import os.girish.practice.spring.mvc.todoapp.models.dao.TodoDao;
import os.girish.practice.spring.mvc.todoapp.models.dao.TodoDaoImplJdbc;

@Service
public class TodoService {

	@Autowired
	private TodoDao todoDao;

	private static Logger logger = Logger.getLogger(TodoService.class);
	private static List<Todo> todos = new ArrayList<Todo>();
	private static int todoCount = 0;

	/*static {
		todos.add(new Todo(++todoCount, "admin", "Learn Spring MVC", new Date(), false));
		todos.add(new Todo(++todoCount, "admin", "Learn Struts", new Date(), false));
		todos.add(new Todo(++todoCount, "admin", "Learn Hibernate", new Date(), false));
		logger.debug("List created!");
	}*/

	public void setTodoDao(TodoDaoImplJdbc todo) {
		todoDao = todo;
	}

	public List<Todo> getTodos(String user) {
		List<Todo> returns = new ArrayList<Todo>();
		for (Todo todo : todos) {
			if (todo.getUser().equals(user))
				returns.add(todo);
		}
		logger.debug("Todos return!");
		return returns;
	}

	public Todo getTodo(int id) {
		for (Todo todo : todos) {
			if (todo.getId() == id)
				return todo;
		}
		logger.debug("Todo return!");
		return null;
	}

	public void addTodo(String user, String desc, Date date) {
		todos.add(new Todo(++todoCount, user, desc, date, false));
		logger.debug("Todo add!");
	}

	public boolean removeTodo(int id) {
		Iterator<Todo> iterator = todos.iterator();
		while (iterator.hasNext()) {
			Todo todo = iterator.next();
			if (todo.getId() == id) {
				iterator.remove();
				return true;
			}
		}
		logger.debug("Todo remove!");
		return false;
	}

	public void updateTodo(Todo todo) {
		todos.remove(todo);
		todos.add(todo);
		logger.debug("Todo update!");
	}

	public void deleteTodo(int id) {
		Iterator<Todo> iterator = todos.iterator();
		while (iterator.hasNext()) {
			Todo todo = iterator.next();
			if (todo.getId() == id) {
				iterator.remove();
			}
		}
		logger.debug("Todo delete!");
	}

	public void saveDb(Todo todo) {
		todoDao.save(todo);
	}

	public List<Todo> getAllDb() {
		return todoDao.fetchAll();
	}
}
