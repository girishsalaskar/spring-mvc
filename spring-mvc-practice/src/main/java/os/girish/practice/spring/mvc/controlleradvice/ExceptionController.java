package os.girish.practice.spring.mvc.controlleradvice;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ControllerAdvice
@EnableWebMvc
public class ExceptionController {

	private Logger logger = Logger.getRootLogger();
	
	@ExceptionHandler(value=Exception.class)
	public String exceptionHandler(HttpServletRequest req, Exception exc) {
		logger.fatal(exc.getMessage()+" at "+req.getRequestURL(), exc);
		return "/exception";
	}
}
