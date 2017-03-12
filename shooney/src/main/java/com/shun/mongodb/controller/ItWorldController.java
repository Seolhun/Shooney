package com.shun.mongodb.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.HttpStatusException;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.shun.mongodb.model.it.ItWorld;
import com.shun.mongodb.service.it.ItWorldService;

@Controller
@RequestMapping(value = "/itworld")
public class ItWorldController {
	@Autowired
	private ItWorldService itWorldService;

	private static final Logger logger = LoggerFactory.getLogger(ItWorldController.class);

	@RequestMapping(value = "/{website}/add", method = RequestMethod.GET)
	public @ResponseBody String getITworldInfoList(ModelMap model, @PathVariable String website) {
		new Thread() {
			public void run() {
//				71500
				for (int i = 73232; i <= 102755; i++) {
					ItWorld itWorld=new ItWorld();
					// 리스트 가져오기
					try {
						Document doc = Jsoup.connect("http://www.itworld.co.kr/insight/" + i).timeout(8000)
								.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.95 Safari/537.36")
								.ignoreHttpErrors(true).get();
						
						if (doc == null) {
							continue;
						}
						Element title = doc.select(".node_title").first();
						Element writer = doc.select(".node_source").first();
						Element content = doc.select(".node_body div").first();
						Elements tags = doc.select(".node_tags a");
						logger.error("TEST : " + title.html());
						logger.error("TEST : " + writer.html());
						logger.error("TEST : " + i);
						logger.error("DEF : ----------------------------------------");
						itWorld.setId(String.valueOf(i-71499));
						itWorld.setTitle(title.html());
						itWorld.setWriter(writer.html());
						itWorld.setContent(content.html());
						itWorld.setBoardNo(i);
						List<String> tagList = new ArrayList<>();
						for (int j = 0; j < tags.size() / 2; j++) {
							String tag = tags.get(j).html();
							tagList.add(tag);
						}
						itWorld.setTags(tagList);
						itWorldService.saveItWorld(itWorld);
					} catch (HttpStatusException e) {
						logger.error("ERROR : HttpStatusException");
					} catch (NullPointerException e) {
						logger.error("ERROR : NullPointerException");
					} catch (IOException e) {
						logger.error("ERROR : IOException");
					}
				}
			} 
		}.start();
		
		return "true";
	}

	@RequestMapping(value = "/{website}/list", method = RequestMethod.GET)
	public String moveITworld(ModelMap model, @PathVariable String website) {
		return "itworld/list";
	}
	
	@RequestMapping(value = "/{website}/{id}", method = RequestMethod.GET)
	public String getITworldInfo(ModelMap model, @PathVariable String id) {
		ItWorld itWorld=itWorldService.findItWorldById(id);
		model.addAttribute("itWorld", itWorld);
		return "itworld/list";
	}
}
