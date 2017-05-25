package com.shun.blog.controller.menu;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shun.blog.model.log.AccessLog;
import com.shun.blog.model.menu.Menu;
import com.shun.blog.service.menu.MenuService;

@Controller
@RequestMapping(value = "/admin/menu")
public class MenuController {
	private static final Logger LOG=LoggerFactory.getLogger(MenuController.class);
	
	private MenuService menuService;
	
	@Autowired	
	public MenuController(MenuService menuService){
		this.menuService=menuService;
	}
	
	@RequestMapping(path="/list", method=RequestMethod.GET)
	public String moveMenuList(AccessLog log, HttpServletRequest request) throws Exception{ 
		LOG.info("where : moveMenuList");
		return "admin/admin-menu-list";
	}
	
	@RequestMapping(path="/add", method=RequestMethod.GET)
	public String moveMenuAddGet(AccessLog log, HttpServletRequest request) throws Exception{ 
		LOG.info("where : moveMenuAddGet");
		return "admin/admin-menu-add";
	}
	
	@RequestMapping(path="/add", method=RequestMethod.POST, produces="application/json")
	public String moveMenuAddPost(@RequestBody Menu menu, HttpServletRequest request) throws Exception{ 
		LOG.info("where : moveMenuAddPost");
		return "admin/admin-menu-add";
	}
}
