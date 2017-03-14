package com.shun.blog.controller.board;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shun.blog.service.board.BoardService;
import com.shun.blog.service.comment.CommentService;
import com.shun.blog.service.common.CommonService;
import com.shun.blog.service.user.UserService;

@Controller
@RequestMapping("/admin/board")
public class AdminBoardController {

	@Autowired
	BoardService bService;

	@Autowired
	UserService uService;
	
	@Autowired
	CommentService cService;

	@Autowired
	CommonService commonService;

	@Autowired
	MessageSource messageSource;
	
	private static final Logger logger = LoggerFactory.getLogger(AdminBoardController.class);

}