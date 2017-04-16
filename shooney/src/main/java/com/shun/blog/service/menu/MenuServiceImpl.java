package com.shun.blog.service.menu;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shun.blog.model.menu.Menu;
import com.shun.blog.model.menu.MenuType;
import com.shun.blog.repository.menu.MenuRepository;

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
	 * @param Content menu
	 * @return List<Menu> menuList
	 * @throws Exception
	 */
	@Override
	public List<Menu> findAllByType(HttpServletRequest request) throws Exception {
		Menu menu=new Menu(1);
		String uri=request.getRequestURI();
		//0="" || 1="shooney" || 2="admin or blog"
		String[] uriList=uri.split("/");
		try {
			if(uriList[2].equals("admin")){
				menu.setMenuType(MenuType.ADMIN.getType());
			} else {
				menu.setMenuType(MenuType.NORMAL.getType());
			}
		} catch (Exception e) {
			menu.setMenuType(MenuType.NORMAL.getType());
		}
				
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
