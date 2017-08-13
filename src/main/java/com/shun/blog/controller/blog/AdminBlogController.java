package com.shun.blog.controller.blog;

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
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin/blog")
public class AdminBlogController {
    private static final Logger LOG = LoggerFactory.getLogger(AdminBlogController.class);

    private BlogService blogService;
    private BlogTypeService blogTypeService;
    private CommonService commonService;
    private MessageSource messageSource;
    private MenuService menuService;

    @Autowired
    public AdminBlogController(CommonService commonService, MessageSource messageSource, BlogService blogService, MenuService menuService, BlogTypeService blogTypeService) {
        this.blogService = blogService;
        this.blogTypeService = blogTypeService;
        this.commonService = commonService;
        this.messageSource = messageSource;
        this.menuService = menuService;
    }

    /**
     * Admin Blog List
     * <p>
     * param -
     * return String - view
     * throws Exception
     *
     * @param model     the model
     * @param request   the request
     * @param boardType the board type
     * @return the string
     *
     * @throws Exception the exception
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String adminBlogList(ModelMap model, HttpServletRequest request, @RequestParam(required = false, name = "bt") String boardType) throws Exception {
        Menu menu = commonService.setMenuConfig(request);
        List<Menu> menuList = menuService.findAllMenu(menu, menu.getMenuType());
        model.addAttribute("menuList", menuList);

        //페이징 세팅 및 파라미터 가져오기.
        Paging paging = commonService.beforeGetPaging(request);
        paging.setBoardType(boardType);

        // 전체 게시판 갯수 확인
        int totalCount = blogService.getCount(paging);
        paging.setTotalCount(totalCount);

        List<Blog> blogs = null;
        try {
            blogs = blogService.selectList(paging);
        } catch (NullPointerException e) {
            LOG.info("error : /blog/list Nullpoint Error");
        }

        model.addAttribute("blogs", blogs);
        model.addAttribute("paging", paging);
        model.addAttribute("blogTypes", blogTypeService.selectList());
        return "admin/blog/admin-blog-list";
    }

    /**
     * Insert Blog Type
     * <p>
     * param
     * return String  -view
     * throws Exception
     *
     * @param blogType   the blog type
     * @param auth       the auth
     * @param ajaxResult the ajax result
     * @return the ajax result
     *
     * @throws Exception the exception
     */
    @RequestMapping(value = "/type/insert", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody AjaxResult insertBoardType(@RequestBody BlogType blogType, Authentication auth, AjaxResult ajaxResult) throws Exception {
        LOG.info("param : /type/insert {}", blogType.toString());
        try {
            blogType.setCreatedBy(auth.getName());
            blogTypeService.insert(blogType);
        } catch (Exception e) {
            LOG.info("ERROR : /tpye/insert NullPoint");
            ajaxResult.setResult("error");
            return ajaxResult;
        }
        ajaxResult.setResult("success");
        return ajaxResult;
    }
}