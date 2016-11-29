package com.shun.blog.dao.item;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.shun.blog.dao.AbstractDao;
import com.shun.blog.model.json.Monster;

@Repository("itemDao")
public class ItemDaoImpl extends AbstractDao<Integer, Monster> implements ItemDao {

	static final Logger logger = LoggerFactory.getLogger(ItemDaoImpl.class);
	
	public Monster findById(int Id) {
		Monster item = getByKey(Id);
		return item;
	}
	
	@SuppressWarnings("unchecked")
	public List<Monster> findAllItems() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Monster> items =(List<Monster>) criteria.list();
		return items;
	}

	public void saveItem(Monster item) {
		persist(item);
	}

	public void deleteUserById(int Id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("Id", Id));
		Monster item = (Monster)crit.uniqueResult();
		delete(item);
	}
}
