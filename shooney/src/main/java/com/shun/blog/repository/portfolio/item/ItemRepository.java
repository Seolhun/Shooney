package com.shun.blog.repository.portfolio.item;

import java.util.List;

import com.shun.blog.model.portfolio.item.Monster;

public interface ItemRepository {

	Monster findById(int Id);

	List<Monster> findAllItems();

	void saveItem(Monster item);

	void deleteUserById(int Id);
}
