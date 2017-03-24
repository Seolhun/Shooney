package com.shun.mongodb.controller.news;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shun.blog.service.common.CommonService;

@Controller
@RequestMapping("/admin/news")
public class AdminNewsDataController {

	@Autowired
	CommonService commonService;

	@Autowired
	MessageSource messageSource;
	
	private static final Logger LOG = LoggerFactory.getLogger(AdminNewsDataController.class);
	
	@RequestMapping(value = "/add/{websiteName}", method = RequestMethod.GET)
	public String moveNewsAdded(ModelMap model, @ModelAttribute @PathVariable(required=true) String websiteName) {
		LOG.info("where : moveNewsList");
		return "news/news-add";
	}
}