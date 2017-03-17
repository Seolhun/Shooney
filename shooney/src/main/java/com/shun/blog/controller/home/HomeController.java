package com.shun.blog.controller.home;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String main() {
		LOG.info("where main");
		return "index";
	}
	
	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String success() {
		LOG.info("where success");
		return "result/success";
	}
	
	@RequestMapping(value = "/deny", method = RequestMethod.GET)
	public String deny() {
		LOG.info("where deny");
		return "result/deny";
	}
	
	@RequestMapping(value = "/myinfo/pro", method = RequestMethod.GET)
	public String myInfoProfile() {
		LOG.info("where myInfoProfile");
		return "myinfo/profile";
	}
	
	@RequestMapping(value = "/myinfo/ati", method = RequestMethod.GET)
	public String myInfoAttitude() {
		LOG.info("where myInfoMission");
		return "myinfo/attitude";
	}
	
	@RequestMapping(value = "/myinfo/goal", method = RequestMethod.GET)
	public String myInfoGoal() {
		LOG.info("where myInfoGoal");
		return "myinfo/goal";
	}
}
