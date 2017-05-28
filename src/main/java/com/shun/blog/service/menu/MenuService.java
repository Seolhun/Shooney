package com.shun.blog.service.menu;

import com.shun.blog.model.menu.Menu;

import java.util.List;

public interface MenuService {
	void insertMenu(Menu menu, String userRole) throws Exception;

	Menu selectMenuById(Long menuId) throws Exception;
	
	List<Menu> findAllMenu(Menu menu, String userRole) throws Exception;

	void updateMenu(Menu menu, String userRole) throws Exception;

	void deleteMenu(Menu menu, String userRole) throws Exception;

	List<Menu> findAllByAdmin(Menu menu) throws Exception;
}