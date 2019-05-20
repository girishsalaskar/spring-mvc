package os.girish.practice.spring.mvc.todoapp.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import os.girish.practice.spring.mvc.todoapp.models.Todo;
import os.girish.practice.spring.mvc.todoapp.services.TodoService;

/**
 * This is simple controller created with the help of <code>@Controller</code> annotation.
 * For maintaining session, <code>@SessionAttributes</code> annotation is used.
 * @author Girish Salaskar
 * @see Controller
 * @see SessionAttributes
 *
 */
@Controller
@SessionAttributes("userName")
public class TodoController {

	/**
	 * This is simple service created with the help of <code>@Autowired</code> annotation.
	 * @see Autowired
	 */
	@Autowired
	private TodoService todoService;

	/**
	 * This is simple GET request created with the help of <code>@GetMapping</code> annotation.
	 * @see GetMapping
	 * @param map Used to add/get request/session attributes
	 * @return View path
	 */
	@GetMapping(value="/todoapp/list.mvc")
	public String displayTodos(ModelMap map) {
		String userName = (String)map.get("userName");
		if(userName==null || userName=="") {
			map.addAttribute("errorMessage", "You must login first!");
			//return "redirect:/todoapp/login.mvc";
			return "/login/login";
		}
		map.addAttribute("name", userName);
		map.addAttribute("todoList", todoService.getTodo(userName));
		return "/todo/list";
	}

	/**
	 * This is simple GET request created with the help of <code>@GetMapping</code> annotation.
	 * @see GetMapping
	 * @param map Used to add/get request/sesssion attributes
	 * @return View path
	 */
	@GetMapping(value="/todoapp/addNew.mvc")
	public String showTodoForm(ModelMap map) {
		String userName = (String)map.get("userName");
		if(userName==null || userName=="") {
			map.addAttribute("errorMessage", "You must login first!");
			//return "redirect:/todoapp/login.mvc";
			return "/login/login";
		}
		map.addAttribute("todo", new Todo());
		return "/todo/add";
	}
	
	@PostMapping(value="/todoapp/addNew.mvc")
	public String storeTodoForm(ModelMap map, @Valid Todo todo, BindingResult result) {
		String userName = (String)map.get("userName");
		if(userName==null || userName=="") {
			map.addAttribute("errorMessage", "You must login first!");
			//return "redirect:/todoapp/login.mvc";
			return "/login/login";
		}
		if(result.hasErrors()) {
			return "/todo/add";
		}
		todoService.addTodo(userName, todo.getDesc(), new java.util.Date());
		map.clear();
		return "redirect:/todoapp/list.mvc";
	}
	
	@GetMapping(value="/todoapp/delete.mvc")
	public String deleteTodo(ModelMap map, @RequestParam String id) {
		todoService.removeTodo(Integer.parseInt(id));
		return "redirect:/todoapp/list.mvc";
	}
}
