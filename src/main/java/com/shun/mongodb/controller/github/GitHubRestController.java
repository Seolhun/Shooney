package com.shun.mongodb.controller.github;

import com.google.gson.JsonObject;
import com.shun.blog.service.common.CommonService;
import com.shun.mongodb.service.github.GithubService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping(value = "/rest/github")
public class GitHubRestController {
	private static final Logger LOG = LoggerFactory.getLogger(GitHubRestController.class);

	private CommonService commonService;
	private GithubService githubService;

	@Autowired
	public GitHubRestController(CommonService commonService, GithubService githubService){
		this.commonService=commonService;
		this.githubService=githubService;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JsonObject githubList(HttpServletRequest request, Model model) throws Exception {
		JsonObject json = new JsonObject();
		return json;
	}
}
