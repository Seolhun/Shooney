package com.shun.blog.controller.menu;

import com.shun.blog.model.common.AjaxResult;
import com.shun.blog.model.menu.Menu;
import com.shun.blog.service.common.CommonService;
import com.shun.blog.service.menu.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/menu")
public class AdminMenuController {
    private static final Logger LOG = LoggerFactory.getLogger(AdminMenuController.class);

    private MenuService menuService;
    private CommonService commonService;

    @Autowired
    public AdminMenuController(MenuService menuService, CommonService commonService) {
        this.menuService = menuService;
        this.commonService = commonService;
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public String moveMenuList(Menu menu, HttpServletRequest request, ModelMap model) throws Exception {
        Menu menuType = commonService.setMenuConfig(request);
        List<Menu> menuList = menuService.findAllMenu(menu, menuType.getMenuType());
        model.addAttribute("menuList", menuList);

        List<Menu> menus = menuService.findAllByAdmin(menu);
        model.addAttribute("menus", menus);
        LOG.info("return moveMenuList : {}", menus.toString());
        return "admin/menu/admin-menu-list";
    }

    @RequestMapping(path = "/insert", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public AjaxResult insertMenu(@RequestBody Menu menu, HttpServletRequest request, AjaxResult ajaxResult) throws Exception {
        LOG.info("param insertMenu : {}", menu.toString());
        Menu menuType = commonService.setMenuConfig(request);
        menuService.insertMenu(menu, menuType.getMenuType());

        return ajaxResult;
    }

    @RequestMapping(path = "/update", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public AjaxResult updateMenu(@RequestBody Menu menu, AjaxResult ajaxResult) throws Exception {
        LOG.info("param updateMenu : {}", menu.toString());
        menuService.updateMenu(menu, menu.getMenuType());
        return ajaxResult;
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public AjaxResult deleteMenu(@RequestBody Menu menu) throws Exception {
        LOG.info("param deleteMenu : {}", menu.toString());
        AjaxResult ajaxResult = null;
        ajaxResult = menuService.deleteMenu(menu, menu.getMenuType());
        LOG.info("return deleteMenu : {}", ajaxResult.toString());
        return ajaxResult;
    }
}
