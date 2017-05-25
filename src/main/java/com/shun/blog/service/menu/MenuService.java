package com.shun.blog.service.menu;

import java.util.List;

import com.shun.blog.model.menu.Menu;

public interface MenuService {
	void insertMenu(Menu menu) throws Exception;
	
	List<Menu> findAllByType(Menu menu, String userRole) throws Exception;
}