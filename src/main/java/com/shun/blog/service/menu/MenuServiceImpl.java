package com.shun.blog.service.menu;

import com.shun.blog.model.menu.Menu;
import com.shun.blog.repository.menu.MenuRepository;
import com.shun.blog.service.common.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(transactionManager = "txManager")
public class MenuServiceImpl implements MenuService {
    static final Logger LOG = LoggerFactory.getLogger(MenuServiceImpl.class);

    private MenuRepository menuRepository;
    private CommonService commonService;

    @Autowired
    MenuServiceImpl(MenuRepository menuRepository, CommonService commonService){
        this.menuRepository=menuRepository;
        this.commonService=commonService;
    }

    @Override
    @Caching(put = {@CachePut(key = "#menuType+'|findAllByType'", value = "menuList")})
    public void insertMenu(Menu menu, String menuType) {
        menu.setDelFlag("Y");
        menuRepository.insertMenu(menu);
    }

    @Override
    public Menu selectMenuById(Long menuId) {
        Menu menu = menuRepository.selectMenuById(menuId);
        return menu;
    }

    @Override
    @Caching(cacheable = {@Cacheable(key = "#menuType+'|findAllMenu'", value = "menuList")})
    public List<Menu> findAllMenu(Menu menu, String menuType) {
        List<Menu> menuList = menuRepository.findAllMenu(menu);
        for (int i = 0; i < menuList.size(); i++) {
            menu.setMenuParentId(menuList.get(i).getMenuId());
            //1을 먼저 가져온 후 depth 2를 가져온다(submenu)
            menu.setMenuDepth(2);
            List<Menu> submenuList = menuRepository.findAllMenu(menu);
            menuList.get(i).setSubmenuList(submenuList);
        }
        return menuList;
    }


    @Override
    @Caching(put = {@CachePut(key = "#menuType+'|findAllMenu'", value = "menuList")})
    public void updateMenu(Menu menu, String menuType) {
        Menu dbMenu = menuRepository.selectMenuById(menu.getMenuId());
        try {
            dbMenu.setMenuName(menu.getMenuName());
            dbMenu.setMenuDepth(menu.getMenuDepth());
            dbMenu.setMenuOrder(menu.getMenuOrder());

            dbMenu.setMenuType(menu.getMenuType());
            if (menu.getMenuUrl() != null) {
                dbMenu.setMenuUrl(menu.getMenuUrl());
            } else if (menu.getMenuParentId() != null) {
                dbMenu.setMenuParentId(menu.getMenuParentId());
            }
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    @Override
    @Caching(put = {@CachePut(key = "#menuType+'|findAllMenu'", value = "menuList")})
    public void deleteMenu(Menu menu, String menuType) {
        Menu dbMenu = menuRepository.selectMenuById(menu.getMenuId());
        if (dbMenu.getDelFlag().equals("Y")) {
            dbMenu.setDelFlag("N");
        } else {
            dbMenu.setDelFlag("Y");
        }
    }

    @Override
    public List<Menu> findAllByAdmin(Menu menu) {
        List<Menu> menuList = menuRepository.findAllByAdmin(menu);
        return menuList;
    }
}
