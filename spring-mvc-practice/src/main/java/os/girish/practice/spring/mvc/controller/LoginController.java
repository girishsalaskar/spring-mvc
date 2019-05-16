package os.girish.practice.spring.mvc.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import os.girish.practice.spring.mvc.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	private static Log logger = LogFactory.getLog(LoginController.class);
	
	@GetMapping(value="/todoapp/login.mvc")
	public String showLoginPage() {
		logger.fatal("Fatal message");
		return "login/login";
	}
	
	@RequestMapping(value="/todoapp/login.mvc", method=RequestMethod.POST)
	public String handleLogin(ModelMap model, 
			@RequestParam String name, @RequestParam String password) {
		if(loginService.validateUser(name, password)) {
			model.put("name", name);
			model.put("password", password);
			return "login/welcome";
		} else {
			model.put("errorMessage", "Invalid user name and/or password!");
			return "login/login";
		}
	}
	@RequestMapping(value="/todoapp/register.mvc", method=RequestMethod.GET)
	public String showRegisterPage() {
		return "login/register";
	}
}
