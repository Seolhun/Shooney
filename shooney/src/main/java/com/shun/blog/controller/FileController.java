package com.shun.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shun.blog.controller.common.CommonFn;
import com.shun.blog.service.board.BoardService;

@Controller
public class FileController {
	@Autowired
	BoardService boardService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	CommonFn commonFn;
	
	@RequestMapping(value = "/fi/get", method = RequestMethod.GET)
	public void getFile(){
		
	}
	@RequestMapping(value = "/fi/set", method = RequestMethod.GET)
	public void setFile(){
	
	}
}
