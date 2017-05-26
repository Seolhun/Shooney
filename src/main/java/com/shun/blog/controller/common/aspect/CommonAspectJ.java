package com.shun.blog.controller.common.aspect;

import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
public class CommonAspectJ {

	private static final Logger LOG = LoggerFactory.getLogger(CommonAspectJ.class);

	@Pointcut("execution (* com.shun.blog.controller.*.*(..))")
	public void ControllerCheck() {
		LOG.info("TEST : PointCut");
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
