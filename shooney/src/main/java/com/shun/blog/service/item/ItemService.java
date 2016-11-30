package com.shun.blog.service.item;

import java.util.List;

import com.shun.blog.model.item.Monster;


public interface ItemService {
	
	Monster findById(int Id);
	
	void saveItem(Monster Item);
	
	void deleteUserById(int Id);

	List<Monster> findAllItems(); 
}