package com.shun.mongodb.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shun.mongodb.model.it.ItWebsite;
import com.shun.mongodb.model.it.ItWorld;
import com.shun.mongodb.service.it.ItWorldService;

@Controller
public class ItWorldController {
	@Autowired
	private ItWorldService iService;
	
	private static final Logger logger = LoggerFactory.getLogger(ItWorldController.class);
	
	@RequestMapping(value = "/it/{website}/add", method = RequestMethod.GET)
	public String getITworldInfoList(ModelMap model, @PathVariable String website, ItWorld itWorld) throws IOException {
		try {
			if(website.equals(ItWebsite.itworld.name())){
				for(int i=71500;i<=102755;i++){
					//리스트 가져오기
					Document doc = Jsoup.connect("http://www.itworld.co.kr/insight/"+i).get();
					Element title = doc.select(".node_title").first();
					Element writer = doc.select(".node_source").first();
					Element content = doc.select(".node_body div").first();
					Elements tags = doc.select(".node_tags a");

					itWorld.setTitle(title.html());
					itWorld.setWriter(writer.html());
					itWorld.setContent(content.html());
					itWorld.setBoardNo(i);
					List<String> tagList=new ArrayList<>();
					for(int j=0;j<tags.size()/2;j++){
						String tag=tags.get(j).html();
						tagList.add(tag);
					}
					itWorld.setTags(tagList);
					iService.saveItWorld(itWorld);
				}
			} else if(website.equals(ItWebsite.cio)){
				
			}
		} catch (NullPointerException e) {
			logger.error("TEST : NullPointError in IT Controller");
		}
		return "itworld/list";
	}
	
	@RequestMapping(value = "/it/{website}/list", method = RequestMethod.GET)
	public String getITworldInfo(ModelMap model, @PathVariable String website){
		return "itworld/list";
	}
}
