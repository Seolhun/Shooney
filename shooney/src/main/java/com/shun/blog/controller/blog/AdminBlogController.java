package com.shun.blog.controller.blog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shun.blog.service.blog.BlogService;
import com.shun.blog.service.comment.CommentService;
import com.shun.blog.service.common.CommonService;
import com.shun.blog.service.user.UserService;

@Controller
@RequestMapping("/admin/blog")
public class AdminBlogController {

	private MessageSource messageSource;
	private BlogService blogService;
	private CommentService cService;
	private CommonService commonService;
	private UserService uService;	
	

	@Autowired
	public AdminBlogController(MessageSource messageSource, BlogService blogService, CommentService cService, CommonService commonService, UserService uService){
		this.messageSource=messageSource;
		this.blogService=blogService;
		this.cService=cService;
		this.commonService=commonService;
		this.uService=uService;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(AdminBlogController.class);

}