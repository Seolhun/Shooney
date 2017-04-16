package com.shun.blog.repository.menu;

import java.util.List;

import com.shun.blog.model.menu.Menu;

public interface MenuRepository {
	void insertMenu(Menu menu) throws Exception;
	
	List<Menu> findAllByType(Menu menu) throws Exception;
}
