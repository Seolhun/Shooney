package com.shun.blog.repository.menu;

import com.shun.blog.model.menu.Menu;

import java.util.List;

public interface MenuRepository {
	void insertMenu(Menu menu);

	Menu selectMenuById(Long menuId);
	
	List<Menu> findAllMenu(Menu menu);

	List<Menu> findAllByAdmin(Menu menu);


}
