package com.shun.blog.service.menu;

import com.shun.blog.model.menu.Menu;

import java.util.List;

public interface MenuService {
	void insertMenu(Menu menu, String userRole);

	Menu selectMenuById(Long menuId);
	
	List<Menu> findAllMenu(Menu menu, String userRole);

	void updateMenu(Menu menu, String userRole);

	void deleteMenu(Menu menu, String userRole);

	List<Menu> findAllByAdmin(Menu menu);
}