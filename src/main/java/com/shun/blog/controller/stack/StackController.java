package com.shun.blog.controller.stack;

import com.shun.blog.model.common.AjaxResult;
import com.shun.blog.model.menu.Menu;
import com.shun.blog.model.stack.Stack;
import com.shun.blog.model.stack.StackFile;
import com.shun.blog.model.user.User;
import com.shun.blog.service.common.CommonService;
import com.shun.blog.service.menu.MenuService;
import com.shun.blog.service.stack.StackFileService;
import com.shun.blog.service.stack.StackService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/stack")
public class StackController {
    private static final Logger LOG = LoggerFactory.getLogger(StackController.class);

    private MenuService menuService;
    private CommonService commonService;
    private StackService stackService;
    private StackFileService stackFileService;

    @Autowired
    public StackController(MenuService menuService, CommonService commonService, StackService stackService, StackFileService stackFileService) {
        this.menuService = menuService;
        this.commonService = commonService;
        this.stackService = stackService;
        this.stackFileService = stackFileService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String moveNewsList(ModelMap model, HttpServletRequest request) throws Exception {
        Menu menu = commonService.setMenuConfig(request);
        List<Menu> menuList = menuService.findAllMenu(menu, menu.getMenuType());
        model.addAttribute("menuList", menuList);
        return "stack/stack-list";
    }
}
