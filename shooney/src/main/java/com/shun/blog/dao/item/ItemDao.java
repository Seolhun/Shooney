package com.shun.blog.dao.item;

import java.util.List;

import com.shun.blog.model.item.Monster;

public interface ItemDao {

	Monster findById(int Id);

	List<Monster> findAllItems();

	void saveItem(Monster item);

	void deleteUserById(int Id);
}
