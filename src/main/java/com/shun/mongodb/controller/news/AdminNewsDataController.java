package com.shun.mongodb.controller.news;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shun.blog.model.menu.Menu;
import com.shun.blog.service.common.CommonService;
import com.shun.blog.service.menu.MenuService;

@Controller
@RequestMapping("/admin/news")
public class AdminNewsDataController {

	private CommonService commonService;
	private MessageSource messageSource;
	private MenuService menuService;
	
	@Autowired
	public AdminNewsDataController(CommonService commonService, MessageSource messageSource, MenuService menuService){
		this.commonService=commonService;
		this.messageSource=messageSource;
		this.menuService=menuService;
	}
	
	private static final Logger LOG = LoggerFactory.getLogger(AdminNewsDataController.class);
	
	@RequestMapping(value = "/add/{websiteName}", method = RequestMethod.GET)
	public String moveNewsAdded(ModelMap model, @ModelAttribute @PathVariable(required=true) String websiteName, HttpServletRequest request) throws Exception {
		Menu menu=commonService.setMenuConfig(request);
		List<Menu> menuList=menuService.findAllByType(menu, menu.getMenuType());
		model.addAttribute("menuList", menuList);
		
		LOG.info("where : moveNewsList");
		return "admin/news/news-add";
	}
}