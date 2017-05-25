package com.shun.blog.controller.common.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CustomRequestHandler extends HandlerInterceptorAdapter {

	@Autowired
	private static final Logger logger = LoggerFactory.getLogger(CustomRequestHandler.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.info("TEST : -------  preHandle get Post Method(contact, recruit) -------");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv) throws Exception {
		logger.info("TEST : ------- After postHandle  -------");
		// we can add attributes in the modelAndView and use that in the view
		// page

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		logger.info("TEST : -------  after Completion  -------");
	}
}