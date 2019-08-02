package os.girish.practice.spring.mvc.todoapp.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import os.girish.practice.spring.mvc.todoapp.models.User;
import os.girish.practice.spring.mvc.todoapp.services.UserService;

@Controller
@SessionAttributes("loggedInUser")
public class UserController {

	@Autowired
	private UserService userService;
	
	private static Log logger = LogFactory.getLog(UserController.class);
	
	@GetMapping(value="/todoapp/login.mvc")
	public String showLoginPage(ModelMap map) {
		logger.debug("Login GET Method");
		String userName = (String)map.get("userName");
		if(userName!=null && userName!="") {
			return "redirect:/todoapp/list.mvc";
		}
		return "login/login";
	}
	
	@RequestMapping(value="/todoapp/login.mvc", method=RequestMethod.POST)
	public String handleLogin(ModelMap model, 
			@RequestParam String name, @RequestParam String password) {
		logger.debug("Login POST Method");
		User loggedInUser = userService.isValidUser(name, password);
		if(loggedInUser!=null) {
			model.clear();
			model.put("loggedInUser", loggedInUser);
			return "redirect:/todoapp/list.mvc";
		} else {
			model.put("errorMessage", "Invalid user name and/or password!");
			return "login/login";
		}
	}
	@RequestMapping(value="/todoapp/register.mvc", method=RequestMethod.GET)
	public ModelAndView showRegisterPage() {
		logger.debug("Register GET Method");
		ModelAndView view = new ModelAndView("/login/register", "userModel", new User());
		return view;
	}
	
	@RequestMapping(value="/todoapp/register.mvc", method=RequestMethod.POST)
	public String registerUser(@ModelAttribute("userModel") User user, BindingResult result, ModelMap map) {
		userService.saveUser(user);
		return "redirect:/todoapp/login.mvc";
	}
	
	@RequestMapping(value="/todoapp/logout.mvc")
	public String logOutUser(ModelMap model) {
		model.remove("loggedInUser");
		return "redirect:/todoapp/login.mvc";
	}
}
