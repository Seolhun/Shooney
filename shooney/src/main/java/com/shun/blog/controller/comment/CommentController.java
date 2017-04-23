package com.shun.blog.controller.comment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shun.blog.service.comment.CommentService;
import com.shun.blog.service.common.CommonService;

@Controller
@RequestMapping(value = "/reply", produces = "application/json")
public class CommentController {
	private static final Logger LOG = LoggerFactory.getLogger(CommentController.class);
	
	private CommonService commonService;
	private CommentService commentService;;
	
	@Autowired
	public CommentController(CommentService commentService, CommonService commonService) {
		this.commonService=commonService;
		this.commentService=commentService;
	}
}