package com.yyh.web.view.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice("com.yyh.web.view")
public class commonExceptionHandler {
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ModelAndView handlerIllegalArgumentException(Exception e) {
		ModelAndView mav  = new ModelAndView();
		mav.addObject("exception", e);
		mav.setViewName("/common/IllegalArgumentError.jsp");
		return mav;
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ModelAndView handlerNullPointerException(Exception e) {
		ModelAndView mav  = new ModelAndView();
		mav.addObject("exception", e);
		mav.setViewName("/common/nullPointerError.jsp");
		return mav;
	}
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception e) {
		ModelAndView mav  = new ModelAndView();
		mav.addObject("exception", e);
		mav.setViewName("/common/Error.jsp");
		return mav;
	}
}
