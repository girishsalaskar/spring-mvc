package os.girish.practice.spring.mvc.todoapp.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExceptionController {

	@RequestMapping(value="/error.do")
	public ModelAndView handleError(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("exception");
		int status =(Integer)request.getAttribute("javax.servlet.error.status_code");
		view.addObject("code", status);
		switch(status) {
		case 500:
			view.addObject("message", "Internal Server error!");
			break;
		case 401:
			view.addObject("message", "Unauthorized!");
			break;
		case 404:
			view.addObject("message", "Requested page not found!");
			break;
		case 400:
			view.addObject("message", "Bad request!");
			break;
		}
		return view;
	}
}
