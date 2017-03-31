package com.shun.blog.controller.error;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExceptionHandlingController {
	private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandlingController.class);

	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Order") // 404
	public class OrderNotFoundException extends RuntimeException {

	}

	@ControllerAdvice
	class GlobalDefaultExceptionHandler {
		final String DEFAULT_ERROR_VIEW = "result/error";
		@ExceptionHandler(value = Exception.class)
	  public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
	    // If the exception is annotated with @ResponseStatus rethrow it and let
	    // the framework handle it - like the OrderNotFoundException example
	    // at the start of this post.
	    // AnnotationUtils is a Spring Framework utility class.
	    if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
	      throw e;

	    LOG.info("return : Error happened. {}",  e.getClass().getName());
	    
	    // Otherwise setup and send the user to a default error-view.
	    ModelAndView mav = new ModelAndView();
	    mav.addObject("exception", e);
	    mav.addObject("url", req.getRequestURL());
	    mav.setViewName(DEFAULT_ERROR_VIEW);
	    return mav;
	  }
	}

	// Convert a predefined exception to an HTTP Status code
	@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Data integrity violation") // 409
	@ExceptionHandler(DataIntegrityViolationException.class)
	public void conflict() {
		// Nothing to do
	}
	
	@ControllerAdvice
	class GlobalControllerExceptionHandler {
		@ResponseStatus(HttpStatus.CONFLICT) // 409
		@ExceptionHandler(DataIntegrityViolationException.class)
		public void handleConflict() {
			// Nothing to do
		}
	}

	// Specify name of a specific view that will be used to display the error:
	@ExceptionHandler({ SQLException.class, DataAccessException.class })
	public String databaseError() {
		// Nothing to do. Returns the logical view name of an error page, passed
		// to the view-resolver(s) in usual way.
		// Note that the exception is NOT available to this view (it is not
		// added
		// to the model) but see "Extending ExceptionHandlerExceptionResolver"
		// below.
		return "databaseError";
	}

	// Total control - setup a model and return the view name yourself. Or
	// consider subclassing ExceptionHandlerExceptionResolver (see below).
	@ExceptionHandler(Exception.class)
	public ModelAndView handleError(HttpServletRequest req, Exception ex) {
		LOG.error("Request: " + req.getRequestURL() + " raised " + ex);
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", ex);
		mav.addObject("url", req.getRequestURL());
		mav.setViewName("error");
		return mav;
	}
}