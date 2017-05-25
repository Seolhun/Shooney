package com.shun.blog.service.menu;

import com.shun.blog.model.menu.Menu;
import com.shun.blog.repository.menu.MenuRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(transactionManager="txManager")
public class MenuServiceImpl implements MenuService {
	static final Logger LOG = LoggerFactory.getLogger(MenuServiceImpl.class);

	@Autowired
	private MenuRepository menuRepository;

	@Override
	public void insertMenu(Menu menu) throws Exception {
		menuRepository.insertMenu(menu);
	}
	
	/**
	 * menu정보 가ㅕ오기.
	 * 
	 * param Content menu
	 * return List<Menu> menuList
	 * throws Exception
	 */
	@Override
//	@Caching(cacheable={@Cacheable(key="#surveyId+'|survey'", value="survey")})
	@Caching(cacheable={@Cacheable(key="#userRole+'|findAllByType'", value="menuList")})
	public List<Menu> findAllByType(Menu menu, String userRole) throws Exception {
		List<Menu> menuList=menuRepository.findAllByType(menu);
		for (int i = 0; i < menuList.size(); i++) {
			menu.setMenuParentId(menuList.get(i).getMenuId());
			//1을 먼저 가져온 후 depth 2를 가져온다(submenu)
			menu.setMenuDepth(2);
			List<Menu> submenuList=menuRepository.findAllByType(menu);
			menuList.get(i).setSubmenuList(submenuList);
		}
		return menuList;
	}
}
