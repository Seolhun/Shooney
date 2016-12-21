package com.shun.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.shun.blog.controller.common.CommonFn;
import com.shun.blog.service.comment.CommentService;

@RestController
public class CommentController {
	@Autowired
	CommentService cService;

	@Autowired
	CommonFn cFn;
	
	
}
