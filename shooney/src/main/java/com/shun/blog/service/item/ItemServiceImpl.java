package com.shun.blog.service.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shun.blog.dao.item.ItemDao;
import com.shun.blog.model.item.Monster;

@Service("itemService")
@Transactional
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemDao dao;

	public Monster findById(int Id) {
		return dao.findById(Id);
	}

	public void saveItem(Monster Item) {
		dao.saveItem(Item);
	}

	@Override
	public void deleteUserById(int Id) {
		dao.deleteUserById(Id);
	}

	@Override
	public List<Monster> findAllItems() {
		return dao.findAllItems();
	}
}
