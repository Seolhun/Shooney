package com.shun.blog.controller.stack;

import com.shun.blog.model.common.AjaxResult;
import com.shun.blog.service.common.CommonService;
import com.shun.blog.service.stack.StackService;
import com.shun.mongodb.model.news.NewsData;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/news")
public class StackRestController {
	private static final Logger LOG = LoggerFactory.getLogger(StackRestController.class);
	
	private StackService stackService;
	private CommonService commonService;
	
	@Autowired
	public StackRestController(StackService stackService, CommonService commonService) {
		this.stackService=stackService;
		this.commonService=commonService;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public AjaxResult saveNews(ModelMap model) {
		LOG.info("where : saveNews");
		AjaxResult result=new AjaxResult();

		result.setResult("success");
		return result;
	}
	
//	@RequestMapping(value = "/stop", method = RequestMethod.GET)
//	public AjaxResult stopThreadNews(AjaxResult ajaxResult) {
//		LOG.info("where : stopThreadNews");
//		stopNewsThread();
//		ajaxResult.setResult("success");
//		return ajaxResult;
//	}

	private Thread getStacksThread(Long startNumber){
		Thread thread=new Thread(){
			public void run() {
			LOG.info("return : getNewsThread : Message");
			// 리스트 가져오기
			try {
				String webSiteName="";
				String address="";

				Document doc=null;
				doc = Jsoup.connect(address).timeout(8000).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.95 Safari/537.36").ignoreHttpErrors(true).get();
				if (doc != null){
					return;
				}

				//모두 돌았는데도 404일때 신문기사가 없으므로 돌아간다.(계속진행 시킨다.)
				NewsData newsData=new NewsData();
				String newsTitle=doc.getElementsByTag("title").html();
				String newsWriter=doc.select("meta[name=author]").attr("content");
				String newsSource=doc.select("meta[name=source]").attr("content");
				String newsHeadImage=doc.select("meta[name=parsely-image-url]").attr("content");
				String newsContent=doc.getElementById("drr-container").html();
				Elements newsTags=doc.getElementsByClass("tags").select("ul > li >a");
				List<String> tags=new ArrayList<>();
				for (Element tagName : newsTags) {
					String tag=tagName.html();
					tags.add(tag);
				}

//				stackService.save(newsData);
			} catch (HttpStatusException e) {
				LOG.error("ERROR : HttpStatusException");
			} catch (NullPointerException e) {
				LOG.error("ERROR : NullPointerException");
			} catch (IOException e) {
				LOG.error("ERROR : IOException");
			}
			}
		};
		return thread;
	}
	
	private void stopNewsThread() {
		// 현재 돌고있는 쓰레드를 객체로 반환.
		Thread thread = Thread.currentThread();
		String name = thread.getName();
		System.out.println("현재 쓰레드 이름 : " + name);
		thread.run();
	}

	}
