package com.shun.blog.controller.blog;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shun.blog.model.blog.Blog;
import com.shun.blog.model.blog.EntityName;
import com.shun.blog.model.common.Paging;
import com.shun.blog.model.file.FileData;
import com.shun.blog.model.file.FileNameInvalidException;
import com.shun.blog.model.file.FileUploadOverException;
import com.shun.blog.model.portfolio.PortfolioName;
import com.shun.blog.service.blog.BlogService;
import com.shun.blog.service.comment.CommentService;
import com.shun.blog.service.common.CommonService;
import com.shun.blog.service.file.FileService;
import com.shun.blog.service.user.UserService;

@Controller
@RequestMapping("/blog")
public class BlogController {
	private BlogService blogService;
//	private UserService userService;
//	private CommentService commentService;
	private CommonService commonService;
	private MessageSource messageSource;
	private FileService fileService;
	
	@Autowired
	public BlogController(UserService userService, CommentService commentService, BlogService blogService,
			CommonService commonService, MessageSource messageSource, FileService fileService) {
//		this.userService = userService;
//		this.commentService = commentService;
		this.blogService = blogService;
		this.commonService=commonService;
		this.messageSource=messageSource;
		this.fileService=fileService;
	}
	
	private static final Logger LOG = LoggerFactory.getLogger(BlogController.class);

	/**
	 * 게시판 리스트
	 * 
	 * @param String portfolioType
	 * @return String  -view
	 * @throws Exception
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String allBlogList(ModelMap model, HttpServletRequest request, @RequestParam(required=false, name="pf") String portfolioType) throws Exception{
		//페이징 세팅 및 파라미터 가져오기.
		Paging paging=commonService.beforePagingGetData(request);
		paging.setPortfolioType(portfolioType);
		
		// 전체 게시판 갯수 확인
		int totalCount = blogService.getCount(paging);
		paging.setTotalCount(totalCount);
		
		commonService.setAndValidationPaging(paging);
		
		List<Blog> blogs =new ArrayList<>();
		try {
			blogs = blogService.selectList(paging);
		} catch (NullPointerException e) {
			
		}
		
		model.addAttribute("blogs", blogs);
		model.addAttribute("paging", paging);
		model.addAttribute("pfNames", PortfolioName.values());
		return "blog/blog-list";
	}

	/**
	 * 게시판 등록 페이지 이동
	 * 
	 * @param -
	 * @return String  -view
	 * @throws Exception
	 */
	@GetMapping(value={"/insert"})
	public String addBlog(ModelMap model) {
		model.addAttribute("blog", new Blog());
		model.addAttribute("edit", false);
		model.addAttribute("enNames", EntityName.values());
		model.addAttribute("pfNames", PortfolioName.values());
		return "blog/blog-insert";
	}

	/**
	 * 게시판 등록하기 - 파일업로드
	 * 
	 * @param Blog blog, FileData fileData
	 * @return String  -view
	 * @throws Exception
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insertBlogDo(Blog blog, BindingResult bindingResult,  ModelMap model, HttpServletRequest request, @RequestParam(name="files") MultipartFile[] files, RedirectAttributes redirect) throws Exception {
		LOG.info("param : insertBlogDo : {}",blog.toString());
		LOG.info("param : insertBlogDo : {}",files.toString());
		
		//Blog 부분
		model.addAttribute("blog", blog);
		model.addAttribute("edit", false);
		//Entity Name과 PortfolioName은 변동이 생길 수 있기 때문에 후에 디비로 바꾼다.
		model.addAttribute("enNames", EntityName.values());
		model.addAttribute("pfNames", PortfolioName.values());
		
		//게시판 유효성 검사.
		String mapping="blog/blog-insert";
		if(blog.getTitle().length()<5){
			commonService.validCheckAndSendError(messageSource, bindingResult, request, blog.getTitle(), "blog", "title", "INVALID-TITLE");
			return mapping;
		} else if(blog.getContent().replaceAll("<p>", "").replaceAll("</p>","").length()<5){
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
			FileData fileData=new FileData();
			fileData.setBlogInFile(blog);
			fileData.setFiles(files);
			fileService.insert(fileData);
		} catch (FileUploadOverException e) {
			e.printStackTrace();
			return mapping;
		} catch (FileNameInvalidException e) {
			e.printStackTrace();
			return mapping;
		} catch (FileUploadException e) {
			e.printStackTrace();
			return mapping;
		} catch (IOException e) {
			e.printStackTrace();
			return mapping;
		}
		
		return "redirect:/success";
	}

	/**
	 * 게시물 자세히보기, 쿠키로 조회수 늘리기.
	 * 
	 * @param Long id
	 * @return String  -view
	 * @throws Exception
	 */
	@RequestMapping(value = { "/detail/{id}" }, method = RequestMethod.GET)
	public String detailBlog(@PathVariable Long id, ModelMap model, HttpServletRequest request, HttpServletResponse response, Principal principal) throws Exception {
		String strId=String.valueOf(id);
		
		Blog blog=new Blog();		
		if(checkHitCookie(request, response, strId)){
			blog.setBlogId(id);;
			blog.setHits(1);
			blogService.update(blog);
		}
		
		blog = blogService.selectById(id);
		
		model.addAttribute("blog", blog);
		model.addAttribute("edit", false);
		model.addAttribute("accessUser", commonService.getAccessUserToModel());
		model.addAttribute("enNames", blog.getEntityName());
		model.addAttribute("pfNames", blog.getPortfolioType());
		return "blog/blog-detail";
	}

	@RequestMapping(value = { "/modify/{id}" }, method = RequestMethod.GET)
	public String editBlog(@PathVariable Long id, ModelMap model) throws Exception {
		Blog blog = blogService.selectById(id);

		if (!(blog.getCreatedBy().equals(commonService.getAccessUserToModel().getNickname()))) {
			return "redirect:/deny";
		}

		model.addAttribute("blog", blog);
		model.addAttribute("edit", true);
		model.addAttribute("enNames", EntityName.values());
		model.addAttribute("pfNames", PortfolioName.values());
		return "blog/blog-insert";
	}

	@RequestMapping(value = { "/{kind}/modify" }, method = RequestMethod.POST)
	public String editBlogDo(@Valid Blog blog, BindingResult result, ModelMap model, @PathVariable String kind) throws Exception{
		if (result.hasErrors()) {
			model.addAttribute("edit", true);
			model.addAttribute("kind", kind);
			return "blog/blog-insert";
		}

//		if (!(blog.getWriter().equals(initializeUser().getNickname()))) {
//			return "redirect:/" + kind + "/r" + id;
//		}

		blogService.update(blog);

//		model.addAttribute("success", "Blog " + blog.getWriter() + "의 " + blog.getTitle() + "성공적으로 수정되었습니다.");
		model.addAttribute("entity", kind);
		return "result/success";
	}

	@RequestMapping(value = { "/{kind}/delete" }, method = RequestMethod.GET)
	public String deleteBlog(@PathVariable String kind, @RequestParam(required=true) Long id) throws Exception {
		Blog blog = blogService.selectById(id);
//		if (!(blog.getWriter().equals(initializeUser().getNickname()))) {
//			return "redirect:/" + kind + "/r" + id;
//		}
//		blog.setDelCheck(1);
		blogService.update(blog);
		return "redirect:/" + kind + "/list";
	}

	// @RequestMapping(value = { "/{tableName}/detail" }, method =
	// RequestMethod.GET)
	// public String getBlogDetail(ModelMap model, HttpServletRequest request,
	// HttpServletResponse response, @PathVariable String tableName,
	// @RequestParam int id) throws Exception {
	// // RequestMethod.GET일 때의 기본언어 설정
	// Language language = commonFn.setLanguageData(text_ko, text_en, request);
	// String target = "Blog";
	// String targetName = "common.main.information";
	// commonFn.setDefaultSetting(model, language, target, targetName);
	//
	// Blog blog = new Blog();
	// blog.setId(id);
	// // 조회수 증가
	// if (checkHitCookie(blog, request, response)) {
	// blog.setTableName(tableName);
	// bDao.updateData(blog, 2);
	// }
	//
	// // 불러올 게시물 설정
	// blog.setTableName(tableName);
	// blog = bDao.getDetailbyKey(blog);
	//
	// // 불러올 댓글 페이징 설정
	// Paging paging = new Paging();
	// paging.setTableName(tableName);
	// paging.setLimit(commonFn.checkVDInt(request.getParameter("limit"), 5));
	// paging.setcPage(commonFn.checkVDInt(request.getParameter("cPage"), 1));
	// String rawQuestion = request.getParameter("sText");
	// paging.setQuestion(rawQuestion);
	//
	// // 총 페이지 가져오기.
	// int totalCount = bDao.getTotalReplyCount(blog, paging);
	// paging.setTotalPage(totalCount);
	// setPaging(paging);
	// blog.setReplyList(bDao.getAllReply(blog, paging));
	//
	// int reno = commonFn.checkVDInt(request.getParameter("reno"), 0);
	// if (reno > 0) {
	// Reply reply=new Reply();
	// reply.setId(reno);
	// reply=bDao.getReply(reply);
	// model.addAttribute("modifyReply", reply);
	// }
	//
	// // 리다이렉트를 통해 온 에러메시지를 확인하는 곳.
	// try {
	// String error = request.getParameter("error");
	// if (error != null ) {
	// model.addAttribute("error", error);
	// }
	//
	// String replyError = request.getParameter("replyError");
	// if (replyError != null) {
	// model.addAttribute("replyError", replyError);
	// }
	// } catch (NullPointerException e) {
	// //e.printStackTrace();
	// }
	// String logUser=commonFn.getPrincipal();
	// Reply reply=new Reply();
	// model.addAttribute("reply", reply);
	// model.addAttribute("logUser", logUser);
	// model.addAttribute("paging", paging);
	// model.addAttribute("tableName", tableName);
	// model.addAttribute("blog", blog);
	// return "blog/detail";
	// }

	private boolean checkHitCookie(HttpServletRequest request, HttpServletResponse response, String id) {
		// 쿠키에 담을 값을 가져오기 위함.(uri는 테이블 값을 가져오기 위함 - 3번)
		boolean validHit = false;
		String tableName="bh";
		String split="A4G9";

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
		Cookie cookie = null;
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
			LOG.info("param : file : {}",multipartFile);
			if (multipartFile.isEmpty() == false) {
				
			}
		}
	}
}