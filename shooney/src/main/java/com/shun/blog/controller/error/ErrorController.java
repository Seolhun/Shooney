package com.shun.blog.controller.error;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorController {
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		return "result/fail";
	}
}

