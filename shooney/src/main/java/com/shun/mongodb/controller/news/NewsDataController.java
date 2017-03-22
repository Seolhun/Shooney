package com.shun.mongodb.controller.news;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/news")
public class NewsDataController {
	private static final Logger LOG = LoggerFactory.getLogger(NewsDataController.class);
	
	@RequestMapping(value = "/add/{websiteName}", method = RequestMethod.GET)
	@Secured(value="SUPERADMIN")
	public String moveNewsAdded(ModelMap model, @ModelAttribute @PathVariable(required=true) String websiteName) {
		LOG.info("where : moveNewsList");
		return "news/news-add";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String moveNewsList(ModelMap model) {
		LOG.info("where : moveNewsList");
		return "news/news-list";
	}
}