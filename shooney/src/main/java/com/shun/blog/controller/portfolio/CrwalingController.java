package com.shun.blog.controller.portfolio;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shun.blog.controller.common.CommonFn;

@Controller
public class CrwalingController {
	@Autowired
	MessageSource messageSource;

	@Autowired
	CommonFn commonFn;
	
	@RequestMapping(value = "/portfolio//crawl/list", method = RequestMethod.GET)
	public String getITworldInfoList(ModelMap model) throws IOException {
		int page=52000;
		//리스트 가져오기
		Document doc = Jsoup.connect("http://www.itworld.co.kr/insight/"+page).get();
		// 태그 혹은 속성 명으로 가져올 수 있다.
//		Elements title = doc.select(".news_list_ div .news_list_title");
//		Elements image = doc.select(".news_list_ div .news_list_image");
//		Elements summary = doc.select(".news_list_ div .news_list_has_thumb_size .news_body_summary");
//		
//		Elements root = doc.select(".news_list_ .of-h .news_list_source a");
//		Elements root = doc.select(".news_list_ .of-h .news_list_time i");
		Element subject = doc.select(".node_title").first();
		Element source = doc.select(".node_source").first();
		Elements content = doc.select(".node_body p");
		System.out.println("TEST : "+subject.html());
		System.out.println("TEST : "+source.html());
		for (Element e : content) {
			System.out.println("TEST : "+e.html());
		}
		return "crawl/list";
	}
	
	@RequestMapping(value = "/portfolio//crawl/get", method = RequestMethod.GET)
	public String getITworldInfo(ModelMap model){

		
		return "crawl/list";
	}
}
