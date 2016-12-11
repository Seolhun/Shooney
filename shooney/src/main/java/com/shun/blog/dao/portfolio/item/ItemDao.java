package com.shun.blog.dao.portfolio.item;

import java.util.List;

import com.shun.blog.model.portfolio.item.Monster;

public interface ItemDao {

	Monster findById(int Id);

	List<Monster> findAllItems();

	void saveItem(Monster item);

	void deleteUserById(int Id);
}
