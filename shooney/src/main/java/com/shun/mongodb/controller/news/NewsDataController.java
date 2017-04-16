package com.shun.mongodb.controller.news;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shun.blog.model.menu.Menu;
import com.shun.blog.service.menu.MenuService;

@Controller
@RequestMapping(value = "/news")
public class NewsDataController {
	private static final Logger LOG = LoggerFactory.getLogger(NewsDataController.class);
	
	private MenuService menuService;
	
	@Autowired
	public NewsDataController(MenuService menuService) {
		this.menuService=menuService;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String moveNewsList(ModelMap model, HttpServletRequest request) throws Exception {
		List<Menu> menuList=menuService.findAllByType(request);
		model.addAttribute("menuList", menuList);
		
		LOG.info("where : moveNewsList");
		return "news/news-list";
	}
	
	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public String moveNewsDetail(ModelMap model, @PathVariable String id, HttpServletRequest request) throws Exception {
		List<Menu> menuList=menuService.findAllByType(request);
		model.addAttribute("menuList", menuList);
		
		LOG.info("where : moveNewsDetail");
		return "news/news-detail";
	}
}