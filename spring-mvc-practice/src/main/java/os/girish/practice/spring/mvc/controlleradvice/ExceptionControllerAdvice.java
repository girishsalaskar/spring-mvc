package os.girish.practice.spring.mvc.controlleradvice;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ControllerAdvice
@EnableWebMvc
public class ExceptionControllerAdvice {

	private Logger logger = Logger.getRootLogger();
	
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value=Exception.class)
	public String exceptionHandler(HttpServletRequest req, Exception exc) {
		logger.fatal(exc.getMessage()+" at "+req.getRequestURL(), exc);
		return "redirect:/error.do";
	}
}
