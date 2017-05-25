package com.shun.blog.service.portfolio.item;

import java.util.List;

import com.shun.blog.model.portfolio.item.Monster;


public interface ItemService {
	
	Monster findById(int Id);
	
	void saveItem(Monster Item);
	
	void deleteUserById(int Id);

	List<Monster> findAllItems(); 
}