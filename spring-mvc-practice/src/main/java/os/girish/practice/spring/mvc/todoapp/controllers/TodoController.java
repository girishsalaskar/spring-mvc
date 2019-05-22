package os.girish.practice.spring.mvc.todoapp.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import os.girish.practice.spring.mvc.todoapp.models.Todo;
import os.girish.practice.spring.mvc.todoapp.services.TodoService;

/**
 * This is simple controller created with the help of <code>@Controller</code>
 * annotation. For maintaining session, <code>@SessionAttributes</code>
 * annotation is used.
 * 
 * @author Girish Salaskar
 * @see Controller
 * @see SessionAttributes
 *
 */
@Controller
@SessionAttributes("userName")
public class TodoController {

	private static Logger logger = Logger.getLogger(TodoController.class);
	/**
	 * This is simple service created with the help of <code>@Autowired</code>
	 * annotation.
	 * 
	 * @see Autowired
	 */
	@Autowired
	private TodoService todoService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	/**
	 * This is simple GET request created with the help of <code>@GetMapping</code>
	 * annotation.
	 * 
	 * @see GetMapping
	 * @param map Used to add/get request/session attributes
	 * @return View path
	 */
	@GetMapping(value = "/todoapp/list.mvc")
	public String displayTodos(ModelMap map) {
		logger.debug("List displaying...");
		String userName = (String) map.get("userName");
		if (userName == null || userName == "") {
			map.addAttribute("errorMessage", "You must login first!");
			// return "redirect:/todoapp/login.mvc";
			return "/login/login";
		}
		map.addAttribute("name", userName);
		map.addAttribute("todoList", todoService.getTodos(userName));
		return "/todo/list";
	}

	/**
	 * This is simple GET request created with the help of <code>@GetMapping</code>
	 * annotation.
	 * 
	 * @see GetMapping
	 * @param map Used to add/get request/sesssion attributes
	 * @return View path
	 */
	@GetMapping(value = "/todoapp/addNew.mvc")
	public ModelAndView showTodoForm(ModelMap map) {
		String userName = (String) map.get("userName");
		if (userName == null || userName == "") {
			map.addAttribute("errorMessage", "You must login first!");
			// return "redirect:/todoapp/login.mvc";
			// return "/login/login";
			return new ModelAndView("/login/login");
		}
		ModelAndView view = new ModelAndView("/todo/add", "todoApp", new Todo());
		/*
		 * map.addAttribute("todo", new Todo()); return "/todo/add";
		 */
		return view;
	}

	@PostMapping(value = "/todoapp/addNew.mvc")
	public String storeTodoForm(@Valid @ModelAttribute("todoApp") Todo todo, BindingResult result, ModelMap map) {
		String userName = (String) map.get("userName");
		if (userName == null || userName == "") {
			map.addAttribute("errorMessage", "You must login first!");
			// return "redirect:/todoapp/login.mvc";
			return "/login/login";
		}
		if (result.hasErrors()) {
			return "/todo/add";
		}
		todoService.addTodo(userName, todo.getDesc(), new java.util.Date());
		map.clear();
		return "redirect:/todoapp/list.mvc";
	}

	@GetMapping(value = "/todoapp/delete.mvc")
	public String deleteTodo(ModelMap map, @RequestParam int id) {
		todoService.removeTodo(id);
		return "redirect:/todoapp/list.mvc";
	}

	@GetMapping(value = "/todoapp/update.mvc")
	public String showUpdateTodo(ModelMap map, @RequestParam int id) {
		map.addAttribute("todoApp", todoService.getTodo(id));
		return "/todo/add";
	}

	@PostMapping(value = "/todoapp/update.mvc")
	public String updateTodo(@Valid @ModelAttribute("todoApp") Todo todo, BindingResult result, ModelMap map) {
		if (result.hasErrors()) {
			return "/todo/add";
		}
		todo.setUser(map.get("userName").toString());
		todoService.updateTodo(todo);
		return "redirect:/todoapp/list.mvc";
	}
}
