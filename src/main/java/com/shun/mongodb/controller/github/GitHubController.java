package com.shun.mongodb.controller.github;

import com.shun.blog.model.menu.Menu;
import com.shun.blog.service.common.CommonService;
import com.shun.blog.service.menu.MenuService;
import com.shun.mongodb.model.github.search.GithubSearchType;
import com.shun.mongodb.model.github.search.SearchOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping(value = "/github")
public class GitHubController {
	private static final Logger LOG = LoggerFactory.getLogger(GitHubController.class);

	private MenuService menuService;
	private CommonService commonService;

	@Autowired
	public GitHubController(MenuService menuService, CommonService commonService){
		this.menuService=menuService;
		this.commonService=commonService;
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String githubList(HttpServletRequest request, Model model) throws Exception {
		Menu menu=commonService.setMenuConfig(request);
		List<Menu> menuList=menuService.findAllMenu(menu, menu.getMenuType());
		model.addAttribute("menuList", menuList);

		model.addAttribute("searchOptions", SearchOption.values());
		model.addAttribute("searchTypes", GithubSearchType.values());
		return "github/github-search";
	}
}
