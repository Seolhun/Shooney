package com.shun.blog.controller.stack;

import com.shun.blog.model.menu.Menu;
import com.shun.blog.service.common.CommonService;
import com.shun.blog.service.menu.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/news")
public class StackController {
	private static final Logger LOG = LoggerFactory.getLogger(StackController.class);
	
	private MenuService menuService;
	private CommonService commonService;
	
	@Autowired
	public StackController(MenuService menuService, CommonService commonService) {
		this.menuService=menuService;
		this.commonService=commonService;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String moveNewsList(ModelMap model, HttpServletRequest request) throws Exception {
		Menu menu=commonService.setMenuConfig(request);
		List<Menu> menuList=menuService.findAllMenu(menu, menu.getMenuType());
		model.addAttribute("menuList", menuList);
		
		LOG.info("where : moveNewsList");
		return "news/news-list";
	}
	
}