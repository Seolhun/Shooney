package com.shun.blog.controller.blog;

import com.shun.blog.model.blog.BlogType;
import com.shun.blog.model.common.AjaxResult;
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

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin/blog")
public class AdminBlogController {
    private static final Logger LOG = LoggerFactory.getLogger(AdminBlogController.class);

    private BlogTypeService blogTypeService;
    private CommonService commonService;
    private MessageSource messageSource;
    private MenuService menuService;

    @Autowired
    public AdminBlogController(CommonService commonService, MessageSource messageSource, MenuService menuService, BlogTypeService blogTypeService) {
        this.blogTypeService = blogTypeService;
        this.commonService = commonService;
        this.messageSource = messageSource;
        this.menuService = menuService;
    }

    /**
     * Insert Blog Type
     *
     * @param
     * @return String  -view
     * @throws Exception
     */
    @RequestMapping(value = "/type/insert", method = RequestMethod.POST, produces = "application/json")
    public AjaxResult insertBoardType(@RequestBody BlogType blogType, ModelMap model, HttpServletRequest request, AjaxResult ajaxResult) throws Exception {
        try {
            blogTypeService.insert(blogType);
        }catch (Exception e) {
            LOG.info("ERROR : /tpye/insert NullPoint");
            ajaxResult.setResult("error");
            return ajaxResult;
        }
        ajaxResult.setResult("success");
        return ajaxResult;
    }
}