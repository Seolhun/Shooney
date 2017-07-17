package com.shun.blog.controller.notice;

import com.shun.blog.model.blog.Blog;
import com.shun.blog.model.blog.BlogType;
import com.shun.blog.model.common.AjaxResult;
import com.shun.blog.model.common.Paging;
import com.shun.blog.model.menu.Menu;
import com.shun.blog.service.blog.BlogService;
import com.shun.blog.service.blog.BlogTypeService;
import com.shun.blog.service.common.CommonService;
import com.shun.blog.service.menu.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin/notice")
public class AdminNoticeController {
    private static final Logger LOG = LoggerFactory.getLogger(AdminNoticeController.class);

    private CommonService commonService;
    private MenuService menuService;

    @Autowired
    public AdminNoticeController(CommonService commonService, MenuService menuService) {
        this.commonService = commonService;
        this.menuService = menuService;
    }

    /**
     * Admin Blog List
     *
     * param -
     * return String - view
     * throws Exception
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String adminBlogList(ModelMap model, HttpServletRequest request, @RequestParam(required = false, name = "bt") String boardType) throws Exception {
        Menu menu=commonService.setMenuConfig(request);
        List<Menu> menuList=menuService.findAllMenu(menu, menu.getMenuType());
        model.addAttribute("menuList", menuList);

        //페이징 세팅 및 파라미터 가져오기.
        Paging paging = commonService.beforeGetPaging(request);
        paging.setBoardType(boardType);

        return "admin/notice/admin-notice-list";
    }

}