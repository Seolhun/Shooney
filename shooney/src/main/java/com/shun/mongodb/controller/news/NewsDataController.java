package com.shun.mongodb.controller.news;

import java.io.IOException;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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

import com.shun.blog.model.common.AjaxResult;
import com.shun.mongodb.model.news.NewsData;
import com.shun.mongodb.service.news.NewsDataService;

@Controller
@RequestMapping(value = "/news")
public class NewsDataController {
	private static final Logger LOG = LoggerFactory.getLogger(NewsDataController.class);
	
	@Autowired
	private NewsDataService newsDataService;

	@RequestMapping(value = "/{website}/add", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult getITworldInfoList(ModelMap model, @PathVariable String website, NewsData newsData) {
		AjaxResult result=new AjaxResult();
		new Thread() {
			public void run() {
//				71500
				for (int i = 3100000; i <= 3100002; i++) {
					// 리스트 가져오기
					try {
						Document doc = Jsoup.connect("http://www.cio.com/article/"+i+"/taxi-hailing-apps-mytaxi-and-hailo-will-merge-to-counter-uber-in-europe.html").timeout(8000)
								.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.95 Safari/537.36")
								.ignoreHttpErrors(true).get();
						
						//404일때 돌아간다.
						if (doc == null) {
							continue;
						}
						
						String newTitle=doc.getElementsByAttribute("og:title").attr("content");
						String newTitle2=doc.getElementsByAttribute("sailthru.tags").attr("content");
//						Element title = doc.select(".node_title").first();
//						Element writer = doc.select(".node_source").first();
//						Element content = doc.select(".node_body div").first();
						Elements tags = doc.select(".node_tags a");
						LOG.error("TEST : " + newTitle);
						LOG.error("TEST : " + newTitle2);
//						LOG.error("TEST : " + writer.html());
//						LOG.error("TEST : " + i);
//						LOG.error("DEF : ----------------------------------------");
//						List<String> tagList = new ArrayList<>();
//						for (int j = 0; j < tags.size() / 2; j++) {
//							String tag = tags.get(j).html();
//							tagList.add(tag);
//						}
//						newsDataService.saveItWorld(newsData);
					} catch (HttpStatusException e) {
						LOG.error("ERROR : HttpStatusException");
					} catch (NullPointerException e) {
						LOG.error("ERROR : NullPointerException");
					} catch (IOException e) {
						LOG.error("ERROR : IOException");
					}
				}
			} 
		}.start();
		result.setResult("success");
		return result;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String moveITworld(ModelMap model, @PathVariable String website) {
		return "news/news-list";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getITworldInfo(ModelMap model, @PathVariable String id) {
		NewsData itWorld=newsDataService.selectById(id);
		model.addAttribute("itWorld", itWorld);
		return "itworld/list";
	}
}
