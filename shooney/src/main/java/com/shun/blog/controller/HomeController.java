package com.shun.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(ModelMap model) {
		return "index";
	}
	
	@RequestMapping(value = "/myinfo/pro", method = RequestMethod.GET)
	public String myInfoProfile(ModelMap model) {
		return "myinfo/profile";
	}
	
	@RequestMapping(value = "/myinfo/ati", method = RequestMethod.GET)
	public String myInfoAttitude(ModelMap model) {
		return "myinfo/attitude";
	}
	
	@RequestMapping(value = "/myinfo/goal", method = RequestMethod.GET)
	public String myInfoGoal(ModelMap model) {
		return "myinfo/goal";
	}
}
