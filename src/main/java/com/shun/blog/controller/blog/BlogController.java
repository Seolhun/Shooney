package com.shun.blog.controller.blog;

import com.shun.blog.model.blog.Blog;
import com.shun.blog.model.blog.BlogType;
import com.shun.blog.model.common.Paging;
import com.shun.blog.model.file.FileData;
import com.shun.blog.model.file.FileNameInvalidException;
import com.shun.blog.model.file.FileUploadOverException;
import com.shun.blog.model.menu.Menu;
import com.shun.blog.service.blog.BlogService;
import com.shun.blog.service.blog.BlogTypeService;
import com.shun.blog.service.common.CommonService;
import com.shun.blog.service.file.FileService;
import com.shun.blog.service.menu.MenuService;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/blog")
public class BlogController {
    private static final Logger LOG = LoggerFactory.getLogger(BlogController.class);

    private BlogService blogService;
    CommonService commonService;
    MessageSource messageSource;
    FileService fileService;
    MenuService menuService;
    BlogTypeService blogTypeService;

    @Autowired
    public BlogController(BlogService blogService, BlogTypeService blogTypeService,
                          CommonService commonService, MessageSource messageSource, FileService fileService, MenuService menuService) {
        this.blogService = blogService;
        this.blogTypeService = blogTypeService;
        this.commonService = commonService;
        this.messageSource = messageSource;
        this.fileService = fileService;
        this.menuService = menuService;
    }

    /**
     * 게시판 리스트
     *
     * param portfolioType
     * return String  -view
     * throws Exception
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String allBlogList(ModelMap model, HttpServletRequest request, @RequestParam(required = false, name = "bt") String boardType) throws Exception {
        Menu menu = commonService.setMenuConfig(request);
        List<Menu> menuList = menuService.findAllMenu(menu, menu.getMenuType());
        model.addAttribute("menuList", menuList);

        //페이징 세팅 및 파라미터 가져오기.
        Paging paging = commonService.beforeGetPaging(request);
        paging.setBoardType(boardType);

        // 전체 게시판 갯수 확인
        int totalCount = blogService.getCount(paging);
        paging.setTotalCount(totalCount);

        commonService.setAndValidationPaging(paging);

        List<Blog> blogs = new ArrayList<>();
        try {
            blogs = blogService.selectList(paging);
        } catch (NullPointerException e) {
            LOG.info("error : /blog/list Nullpoint Error");
        }

        model.addAttribute("blogs", blogs);
        model.addAttribute("paging", paging);
        model.addAttribute("blogTypes", blogTypeService.selectList());
        return "blog/blog-list";
    }

    /**
     * 게시판 등록 페이지 이동
     *
     * param -
     * return String  -view
     * throws Exception
     */
    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public String addBlog(HttpServletRequest request, Model model) throws Exception {
        Menu menu = commonService.setMenuConfig(request);
        List<Menu> menuList = menuService.findAllMenu(menu, menu.getMenuType());
        model.addAttribute("menuList", menuList);

        model.addAttribute("blog", new Blog());
        model.addAttribute("edit", false);
        model.addAttribute("blogTypes", blogTypeService.selectList());
        return "blog/blog-insert";
    }

    /**
     * 게시판 등록하기 - 파일업로드
     *
     * param blog, files
     * return String  -view
     * throws Exception
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insertBlogDo(Blog blog, @RequestParam(name = "files") MultipartFile[] files, BindingResult bindingResult, ModelMap model, HttpServletRequest request, RedirectAttributes redirect) throws Exception {
        //Blog 부분
        model.addAttribute("blog", blog);
        model.addAttribute("edit", false);
        model.addAttribute("blogTypes", blogTypeService.selectList());

        //게시판 유효성 검사.
        String mapping = "blog/blog-insert";
        if (blog.getTitle().length() < 5) {
            commonService.validCheckAndSendError(messageSource, bindingResult, request, blog.getTitle(), "blog", "title", "INVALID-TITLE");
            return mapping;
        } else if (blog.getContent().replaceAll("<p>", "").replaceAll("</p>", "").length() < 5) {
            commonService.validCheckAndSendError(messageSource, bindingResult, request, blog.getContent(), "blog", "content", "INVALID-CONTENT");
            return mapping;
        } else if (bindingResult.hasErrors()) {
            return mapping;
        }

        //유저 확인.
        try {
            blog.setCreatedBy(commonService.getAccessUserToModel().getNickname());
        } catch (NullPointerException e) {
            redirect.addAttribute("error", "anonymousUser");
            return "redirect:/login";
        }

        //Catch문을 통한 에러처리 로직필요.
        try {
            FileData fileData = new FileData();
            fileData.setBlogInFile(blog);
            fileData.setFiles(files);

            blogService.insert(blog);
            fileService.insert(fileData);

            BlogType blogType=new BlogType(blog.getBlogType());
            blogTypeService.update(blogType,+1);
        } catch (FileUploadOverException | FileUploadException | FileNameInvalidException | IOException e) {
            e.printStackTrace();
            return mapping;
        }

        return "redirect:/success";
    }

    /**
     * 게시물 자세히보기, 쿠키로 조회수 늘리기.
     *
     * param id
     * return String  -view
     * throws Exception
     */
    @RequestMapping(value = {"/detail/{id}"}, method = RequestMethod.GET)
    public String detailBlog(@PathVariable Long id, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Menu menu = commonService.setMenuConfig(request);
        List<Menu> menuList = menuService.findAllMenu(menu, menu.getMenuType());
        model.addAttribute("menuList", menuList);

        //쿠키 조회수 확인.
        String strId = String.valueOf(id);
        Blog blog = new Blog();
        if (checkHitCookie(request, response, strId)) {
            blog.setId(id);
            blog.setHits(1);
            blogService.update(blog);
        }

        blog = blogService.selectById(id);
        model.addAttribute("blog", blog);
        model.addAttribute("edit", false);
        model.addAttribute("accessUser", commonService.getAccessUserToModel());
        model.addAttribute("blogTypes", blogTypeService.selectList());
        return "blog/blog-detail";
    }

    /**
     * 게시판 리스트
     *
     * param @PathVariable Long id
     * return String  -view
     * throws Exception
     */
    @RequestMapping(value = {"/modify/{id}"}, method = RequestMethod.GET)
    public String editBlog(@PathVariable Long id, ModelMap model, HttpServletRequest request) throws Exception {
        Menu menu = commonService.setMenuConfig(request);
        List<Menu> menuList = menuService.findAllMenu(menu, menu.getMenuType());
        model.addAttribute("menuList", menuList);

        Blog blog = blogService.selectById(id);
        if (!(blog.getCreatedBy().equals(commonService.getAccessUserToModel().getNickname()))) {
            return "redirect:/deny";
        }

        model.addAttribute("blog", blog);
        model.addAttribute("edit", true);
        model.addAttribute("blogTypes", blogTypeService.selectList());
        return "blog/blog-modify";
    }

    @RequestMapping(value = {"/modify/{id}"}, method = RequestMethod.POST)
    public String editBlogDo(@Valid Blog blog, BindingResult result, @PathVariable Long id, ModelMap model) throws Exception {
        Blog dbBlog = blogService.selectById(id);
        if (!(dbBlog.getCreatedBy().equals(commonService.getAccessUserToModel().getNickname()))) {
            return "redirect:/deny";
        } else if (result.hasErrors()) {
            return "blog/blog-modify";
        }

        blogService.update(blog);
        model.addAttribute("success", "Blog " + blog.getCreatedBy() + "의 " + blog.getTitle() + "성공적으로 수정되었습니다.");
        return "result/success";
    }

    @RequestMapping(value = {"/delete/{id}"}, method = RequestMethod.GET)
    public String deleteBlog(@PathVariable Long id, Authentication auth) throws Exception {
        Blog dbBlog = blogService.selectById(id);
        if (!(dbBlog.getCreatedBy().equals(commonService.getAccessUserToModel().getNickname())) || !(commonService.getLoginAuthValidation(auth, "ROLE_SUPERADMIN"))) {
            return "redirect:/deny";
        }

        dbBlog.setDelFlag("Y");
        blogService.update(dbBlog);

        BlogType blogType=new BlogType(dbBlog.getBlogType());
        blogTypeService.update(blogType,-1);

        return "redirect:/blog/list";
    }


    //Blog Type Part

    /**
     * Insert Blog Type
     *
     * param
     * return String  -view
     * throws Exception
     */
    @RequestMapping(value = "/type/list", method = RequestMethod.GET)
    @ResponseBody
    public List<BlogType> getBoardType() throws Exception {
        List<BlogType> blogTypes = new ArrayList<>();
        try {
            blogTypes = blogTypeService.selectList();
        } catch (NullPointerException e) {
            LOG.info("error : /type/list NullPoint");
        }

        return blogTypes;
    }

    private boolean checkHitCookie(HttpServletRequest request, HttpServletResponse response, String id) {
        // 쿠키에 담을 값을 가져오기 위함.(uri는 테이블 값을 가져오기 위함 - 3번)
        boolean validHit = false;
        String tableName = "bh";
        String split = "A4G9";

        // 쿠키 가져오기(체크 하는것)
        Map<String, String> cookieMap = new HashMap<>();
        Cookie cookies[] = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                cookieMap.put(cookies[i].getName(), cookies[i].getValue());
            }
        }

        // 저장된 쿠키 중 읽었었던 해당 게시판의 번호 불러오기
        String originalNo = (String) cookieMap.get(tableName);
        Cookie cookie;
        if (originalNo == null) {
            cookie = new Cookie(tableName, id);
        } else {
            String[] upHitByNo = originalNo.split(split);
            for (int i = 0; i < upHitByNo.length; i++) {
                if (upHitByNo[i].equals(id)) {
                    return validHit;
                }
            }
            originalNo = originalNo + split + id;
            cookie = new Cookie(tableName, originalNo);
        }

        cookie.setMaxAge(24 * 60 * 60); // 365*24*60*60 365일
        response.addCookie(cookie);
        validHit = true;
        return validHit;
    }

    //파일 존재 여부 확인 Method
    private void validFileExist(MultipartHttpServletRequest multipartRequst, Map<String, MultipartFile> fileMap) throws Exception {
        Iterator<String> iterator = multipartRequst.getFileNames();
        // 첨부된 파일이 있으면 파일시퀀스 증가하고 가져오기.
        while (iterator.hasNext()) {
            MultipartFile multipartFile = multipartRequst.getFile(iterator.next());
            LOG.info("param : file : {}", multipartFile);
            if (multipartFile.isEmpty() == false) {

            }
        }
    }
}