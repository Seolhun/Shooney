package com.shun.blog.controller.home;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shun.blog.model.log.AccessLog;
import com.shun.blog.model.menu.Menu;
import com.shun.blog.service.common.CommonService;
import com.shun.blog.service.log.AccessLogService;
import com.shun.blog.service.menu.MenuService;

@Controller
public class HomeController {
	private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);
	
	private MenuService menuService;
	private CommonService commonService;
	private AccessLogService accessLogService;
	
	@Autowired	
	public HomeController(MenuService menuService, CommonService commonService, AccessLogService accessLogService){
		this.menuService=menuService;
		this.commonService=commonService;
		this.accessLogService=accessLogService;
	}
	
	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public String main(HttpServletRequest request, Model model) throws Exception {
		Menu menu=commonService.setMenuConfig(request);
		List<Menu> menuList=menuService.findAllByType(menu, menu.getMenuType());
		
		AccessLog accessLog=new AccessLog();
		Integer total=accessLogService.getCountByDate(accessLog);
		accessLog.setType(1);
		accessLog.setCalculator(-1);
		Integer yesterday=accessLogService.getCountByDate(accessLog);
		
		accessLog.setType(2);
		accessLog.setCalculator(1);
		Integer today=accessLogService.getCountByDate(accessLog);
		
		Map<String, Integer> historys=new HashMap<>();
		historys.put("yesterday", yesterday);
		historys.put("today", today);
		historys.put("total", total);
		
		model.addAttribute("historys", historys);
		model.addAttribute("menuList", menuList);
		return "index";
	}
	
	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String success(HttpServletRequest request, Model model) throws Exception {
		Menu menu=commonService.setMenuConfig(request);
		List<Menu> menuList=menuService.findAllByType(menu, menu.getMenuType());
		model.addAttribute("menuList", menuList);
		
		LOG.info("where success");
		return "result/success";
	}
	
	@RequestMapping(value = "/deny", method = RequestMethod.GET)
	public String deny(HttpServletRequest request, Model model) throws Exception {
		Menu menu=commonService.setMenuConfig(request);
		List<Menu> menuList=menuService.findAllByType(menu, menu.getMenuType());
		model.addAttribute("menuList", menuList);
		
		LOG.info("where deny");
		return "result/deny";
	}
	
	@RequestMapping(value = "/myinfo/profile", method = RequestMethod.GET)
	public String myInfoProfile(HttpServletRequest request, Model model) throws Exception {
		Menu menu=commonService.setMenuConfig(request);
		List<Menu> menuList=menuService.findAllByType(menu, menu.getMenuType());
		model.addAttribute("menuList", menuList);
		
		LOG.info("where myInfoProfile");
		return "myinfo/profile";
	}
	
	@RequestMapping(value = "/myinfo/attitude", method = RequestMethod.GET)
	public String myInfoAttitude(HttpServletRequest request, Model model) throws Exception {
		Menu menu=commonService.setMenuConfig(request);
		List<Menu> menuList=menuService.findAllByType(menu, menu.getMenuType());
		model.addAttribute("menuList", menuList);
		
		LOG.info("where myInfoMission");
		return "myinfo/attitude";
	}
	
	@RequestMapping(value = "/myinfo/goal", method = RequestMethod.GET)
	public String myInfoGoal(HttpServletRequest request, Model model) throws Exception {
		Menu menu=commonService.setMenuConfig(request);
		List<Menu> menuList=menuService.findAllByType(menu, menu.getMenuType());
		model.addAttribute("menuList", menuList);
		
		LOG.info("where myInfoGoal");
		return "myinfo/goal";
	}
}
