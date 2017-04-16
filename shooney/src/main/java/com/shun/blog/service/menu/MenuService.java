package com.shun.blog.service.menu;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.shun.blog.model.menu.Menu;

public interface MenuService {
	void insertMenu(Menu menu) throws Exception;
	
	List<Menu> findAllByType(Menu menu, HttpServletRequest request) throws Exception;
}