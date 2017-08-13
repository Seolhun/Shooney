package com.shun.blog.controller.notice;

import com.shun.blog.model.common.AjaxResult;
import com.shun.blog.model.menu.Menu;
import com.shun.blog.model.notice.Notice;
import com.shun.blog.service.common.CommonService;
import com.shun.blog.service.menu.MenuService;
import com.shun.blog.service.notice.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/notice")
public class AdminNoticeController {
    private static final Logger LOG = LoggerFactory.getLogger(AdminNoticeController.class);

    private MenuService menuService;
    private NoticeService noticeService;
    private CommonService commonService;

    @Autowired
    public AdminNoticeController(NoticeService noticeService, MenuService menuService, CommonService commonService) {
        this.noticeService = noticeService;
        this.menuService = menuService;
        this.commonService = commonService;
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public String moveNoticeList(Notice notice, HttpServletRequest request, ModelMap model) throws Exception {
        Menu menu=commonService.setMenuConfig(request);
        List<Menu> menuList=menuService.findAllMenu(menu, menu.getMenuType());
        model.addAttribute("menuList", menuList);

        List<Notice> notices = noticeService.findAllByAdmin(notice);
        model.addAttribute("notices", notices);
        LOG.info("return moveNoticeList : {}", notices.toString());
        return "admin/notice/admin-notice-list";
    }

    @RequestMapping(path = "/insert", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public AjaxResult insertNotice(@RequestBody Notice notice, AjaxResult ajaxResult, Authentication auth) throws Exception {
        LOG.info("param insertNotice : {}", notice.toString());
        noticeService.insertNotice(notice);
        notice.setCreatedBy(auth.getName());
        return ajaxResult;
    }

    @RequestMapping(path = "/update", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public AjaxResult updateNotice(@RequestBody Notice notice, AjaxResult ajaxResult) throws Exception {
        LOG.info("param updateNotice : {}", notice.toString());
        noticeService.updateNotice(notice);
        return ajaxResult;
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public AjaxResult deleteNotice(@RequestBody Notice notice, AjaxResult ajaxResult) throws Exception {
        LOG.info("param deleteNotice : {}", notice.toString());

        noticeService.deleteNotice(notice);
        return ajaxResult;
    }
}
