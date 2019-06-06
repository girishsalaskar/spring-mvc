package os.girish.practice.spring.mvc.todoapp.controllers.rest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import os.girish.practice.spring.mvc.todoapp.models.Todo;

@RestController
@SessionAttributes("userName")
public class TodoRestController {

//	@Autowired
//	private TodoService todoService;

	@GetMapping(value = "/todoapp/rest/list.mvc")
	public List<Todo> getAll(ModelMap map) {
//		List<Todo> todos = todoService.getTodos(map.get("userName").toString());
		return null;
	}

	@GetMapping(value = "/todoapp/rest/todo/{id}")
	public Todo getTodo(ModelMap map, @PathVariable("id") int id) {
//		return todoService.getTodo(id);
		return null;
	}

	@GetMapping(value = "/todoapp/rest/sample.mvc")
	public Map<Object, Object> sampleJson() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("integer", 10);
		map.put("string", "hi there");
		map.put("array", Arrays.asList(10, 20, 30));
		Todo todo = new Todo();
		todo.setDesc("Desc");
		todo.setDone(false);
		todo.setTarget(new java.util.Date());
		//todo.setUser("admin");
		map.put("object", todo);
		return map;
	}
}
