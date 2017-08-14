package com.shun.blog.repository.menu;

import com.shun.blog.model.menu.Menu;
import com.shun.blog.repository.AbstractRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MenuRepositoryImpl extends AbstractRepository<Long, Menu> implements MenuRepository {
	static final Logger LOG = LoggerFactory.getLogger(MenuRepositoryImpl.class);
	
	@Override
	public void insertMenu(Menu menu) {
		LOG.info("param : insertMenu {}", menu.toString());
		persist(menu);
	}

	@Override
	public Menu selectMenuById(Long menuId) {
	    Menu menu = getByKey(menuId);
        LOG.info("return selectMenuById dbMenu : {}", menu.toString());
        return menu;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> findAllMenu(Menu menu) {
		LOG.info("param : findAllMenu {}", menu.toString());
		int menuDepth=menu.getMenuDepth();
		String menuType=menu.getMenuType();

		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("menuType", menuType));
		criteria.add(Restrictions.eq("menuDepth", menuDepth));
		criteria.add(Restrictions.eq("deletedFlag", false));
		criteria.addOrder(Order.asc("menuOrder"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		if(menuDepth==2){
			criteria
				.add(Restrictions.eq("menuParentId", menu.getMenuParentId()));
		}

		List<Menu> menuList =criteria.list();
		return menuList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> findAllByAdmin(Menu menu) {
		LOG.info("param : findAllByAdmin {}", menu.toString());
		Criteria criteria = createEntityCriteria();
		criteria.addOrder(Order.asc("menuType"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Menu> menuList =criteria.list();
		return menuList;
	}


}

