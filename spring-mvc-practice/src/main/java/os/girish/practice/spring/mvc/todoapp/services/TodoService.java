package os.girish.practice.spring.mvc.todoapp.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import os.girish.practice.spring.mvc.todoapp.models.Todo;

@Service
public class TodoService {

	private static List<Todo> todos = new ArrayList<Todo>();
	private static int todoCount = 0;
	
	static {
		todos.add(new Todo(++todoCount, "admin", "Learn Spring MVC", new Date(),
				false));
		todos.add(new Todo(++todoCount, "admin", "Learn Struts", new Date(), false));
		todos.add(new Todo(++todoCount, "admin", "Learn Hibernate", new Date(),
				false));
	}
	
	public List<Todo> getTodo(String user) {
		List<Todo> returns = new ArrayList<Todo>();
		for(Todo todo:todos) {
			if(todo.getUser().equals(user))
				returns.add(todo);
		}
		return returns;
	}
	
	public void addTodo(String user, String desc, Date date) {
		todos.add(new Todo(++todoCount, user, desc, date, false));
	}
	
	public boolean removeTodo(int id) {
		Iterator<Todo> iterator = todos.iterator();
		while(iterator.hasNext()) {
			Todo todo = iterator.next();
			if(todo.getId()==id) {
				iterator.remove();
				return true;
			}
		}
		return false;
	}
}
