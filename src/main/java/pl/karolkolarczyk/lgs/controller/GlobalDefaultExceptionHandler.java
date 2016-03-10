package pl.karolkolarczyk.lgs.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import pl.karolkolarczyk.lgs.exception.UnacceptableResultException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	public static final String DEFAULT_ERROR_VIEW = "error";

	@ExceptionHandler(UnacceptableResultException.class)
	public ModelAndView handleUnacceptableResultException(UnacceptableResultException ex) {
		ModelAndView model = new ModelAndView("unacceptable-result-error");
		model.addObject("firstPlayerScore", ex.getFirstPlayerScore());
		model.addObject("secondPlayerScore", ex.getSecondPlayerScore());
		return model;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) {
		ModelAndView mav = new ModelAndView(DEFAULT_ERROR_VIEW);
		mav.addObject("exception", e);
		mav.addObject("url", request.getRequestURL());
		return mav;
	}

}
