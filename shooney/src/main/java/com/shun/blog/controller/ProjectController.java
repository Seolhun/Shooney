package com.shun.blog.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ProjectController {
	@RequestMapping(value = "/po/project/list", method = RequestMethod.GET)
	public String webp(ModelMap model) {
		
		return "portfolio/project/list";
	}
}
