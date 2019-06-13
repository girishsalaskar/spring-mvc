package os.girish.practice.spring.mvc.todoapp.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.jboss.logging.Param;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import os.girish.practice.spring.mvc.todoapp.models.Todo;
import os.girish.practice.spring.mvc.todoapp.models.User;
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
@SessionAttributes("loggedInUser")
public class TodoController {

	private static Logger logger = Logger.getLogger(TodoController.class);
	/**
	 * This is simple service created with the help of <code>@Autowired</code>
	 * annotation.
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
		Object loggedInUserObj = map.get("loggedInUser");
		if (loggedInUserObj == null || !(loggedInUserObj instanceof User)) {
			map.addAttribute("errorMessage", "You must login first!");
			return "/login/login";
		}
		User loggedInUser = (User) loggedInUserObj;
		map.addAttribute("name", loggedInUser.getUserName());
		List<Todo> list = todoService.getTodosByUser(loggedInUser);
		if (list != null)
			map.addAttribute("todoList", list);
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
		Object loggedInUserObj = map.get("loggedInUser");
		if (loggedInUserObj == null || !(loggedInUserObj instanceof User)) {
			map.addAttribute("errorMessage", "You must login first!");
			return new ModelAndView("/login/login");
		}
		//User loggedInUser = (User) loggedInUserObj;
		ModelAndView view = new ModelAndView("/todo/add", "todoApp", new Todo());
		return view;
	}

	@PostMapping(value = "/todoapp/addNew.mvc")
	public String storeTodoForm(@Valid @ModelAttribute("todoApp") Todo todo, BindingResult result, ModelMap map) {
		Object loggedInUserObj = map.get("loggedInUser");
		if (loggedInUserObj == null || !(loggedInUserObj instanceof User)) {
			map.addAttribute("errorMessage", "You must login first!");
			return "/login/login";
		}
		if (result.hasErrors()) {
			return "/todo/add";
		}
		User loggedInUser = (User) loggedInUserObj;
		todo.setUser(loggedInUser);
		todoService.saveDb(todo);
		map.clear();
		return "redirect:/todoapp/list.mvc";
	}

	@GetMapping(value = "/todoapp/delete.mvc")
	public String deleteTodo(ModelMap map, @RequestParam int id) {
		Object loggedInUserObj = map.get("loggedInUser");
		if (loggedInUserObj == null || !(loggedInUserObj instanceof User)) {
			map.addAttribute("errorMessage", "You must login first!");
			return "/login/login";
		}
		todoService.deleteById(id);
		logger.fatal("Deleted ID : ");
		return "redirect:/todoapp/list.mvc";
	}

	@GetMapping(value = "/todoapp/update.mvc")
	public String showUpdateTodo(ModelMap map, @RequestParam int id) {
		Object loggedInUserObj = map.get("loggedInUser");
		if (loggedInUserObj == null || !(loggedInUserObj instanceof User)) {
			map.addAttribute("errorMessage", "You must login first!");
			return "/login/login";
		}
		Todo todo = todoService.getTodoById(id);
		map.addAttribute("todoApp", todo);
		return "/todo/add";
	}

	@PostMapping(value = "/todoapp/update.mvc")
	public String updateTodo(@Valid @ModelAttribute("todoApp") Todo todo, BindingResult result, ModelMap map) {
		Object loggedInUserObj = map.get("loggedInUser");
		if (loggedInUserObj == null || !(loggedInUserObj instanceof User)) {
			map.addAttribute("errorMessage", "You must login first!");
			return "/login/login";
		}
		if (result.hasErrors()) {
			return "/todo/add";
		}
		User user = (User)loggedInUserObj;
		todo.setUser(user);
		todoService.saveDb(todo);
		return "redirect:/todoapp/list.mvc";
	}
	
	@ResponseBody
	@GetMapping(value="/todoapp/changestatus.mvc")
	public String changeStatus(ModelMap map, @RequestParam(value="todoId") int todoId, @RequestParam(value="todoStatus") boolean status) {
		Object loggedInUserObj = map.get("loggedInUser");
		if (loggedInUserObj == null || !(loggedInUserObj instanceof User)) {
			map.addAttribute("errorMessage", "You must login first!");
			return "/login/login";
		}
		Todo todo = todoService.getTodoById(todoId);
		todo.setDone(status);
		todoService.saveDb(todo);
		return "Todo ID : "+todoId+" TodoStatus : "+status;
	}
}
