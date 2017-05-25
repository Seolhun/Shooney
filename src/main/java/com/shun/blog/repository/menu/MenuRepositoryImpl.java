package com.shun.blog.repository.menu;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.shun.blog.model.menu.Menu;
import com.shun.blog.repository.AbstractRepository;

@Repository
public class MenuRepositoryImpl extends AbstractRepository<Long, Menu> implements MenuRepository {
	static final Logger LOG = LoggerFactory.getLogger(MenuRepositoryImpl.class);
	
	@Override
	public void insertMenu(Menu menu) throws Exception {
		LOG.info("param : insertMenu {}", menu.toString());
		persist(menu);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> findAllByType(Menu menu) throws Exception {
		LOG.info("param : findAllByType {}", menu.toString());
		int menuDepth=menu.getMenuDepth(); 
		String menuType=menu.getMenuType();
		
		Criteria criteria = createEntityCriteria();
		criteria
			.add(Restrictions.eq("menuType", menuType))
			.add(Restrictions.eq("menuDepth", menuDepth))
			.add(Restrictions.eq("delFlag", "N"))
			.addOrder(Order.asc("menuOrder"))
			.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);		
		
		if(menuDepth==2){
			criteria
				.add(Restrictions.eq("menuParentId", menu.getMenuParentId()));
		}
		
		List<Menu> menuList =criteria.list();
		return menuList;
	}
}

