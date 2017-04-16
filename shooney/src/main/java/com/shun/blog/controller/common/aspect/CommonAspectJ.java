package com.shun.blog.controller.common.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
public class CommonAspectJ {
	
	private static final Logger LOG = LoggerFactory.getLogger(CommonAspectJ.class);

	@Pointcut("execution (** com.shun.blog.controller.*.*(..))")
	public void ControllerCheck() {
		LOG.info("tiem : PointCut");
	}
	
	@Around("execution(* com.shun.blog.controller.*Controller.*(..))")
	public void aroundCheck() throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		LOG.info("TEST : @Around = "+request.getRequestURI());
	}
	
	@Before("ControllerCheck()")
	public void injectLogger() throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		LOG.info("TEST : @Before = "+request.getRequestURI());
	}
	
	@AfterThrowing("ControllerCheck()")
	public void ExceptionLogger() throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		LOG.info("ERROR : @AfterThrowing = "+request.getRequestURI());
		LOG.info("ERROR : @AfterThrowing = "+request.getMethod());
	}
}
