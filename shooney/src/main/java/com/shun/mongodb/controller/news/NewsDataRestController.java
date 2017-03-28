package com.shun.mongodb.controller.news;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shun.blog.model.common.AjaxResult;
import com.shun.blog.model.common.Paging;
import com.shun.blog.service.common.CommonService;
import com.shun.mongodb.model.news.NewsData;
import com.shun.mongodb.model.news.NewsWebSite;
import com.shun.mongodb.service.news.NewsDataService;

@RestController
@RequestMapping(value = "/news")
public class NewsDataRestController {
	private static final Logger LOG = LoggerFactory.getLogger(NewsDataRestController.class);
	
	private NewsDataService newsDataService;
	private CommonService commonService;
	
	@Autowired
	public NewsDataRestController(NewsDataService newsDataService, CommonService commonService) {
		this.newsDataService=newsDataService;
		this.commonService=commonService;
	}
	@RequestMapping(value = "/save/{idx}", method = RequestMethod.GET)
	public AjaxResult saveNews(ModelMap model, @PathVariable Long idx) {
		LOG.info("where : saveNews");
		AjaxResult result=new AjaxResult();
		
		getNewsThread(idx).start();
		
		result.setResult("success");
		return result;
	}
	
	@RequestMapping(value = "/stop", method = RequestMethod.GET)
	public AjaxResult stopThreadNews(AjaxResult ajaxResult) {
		LOG.info("where : stopThreadNews");
		stopNewsThread();
		ajaxResult.setResult("success");
		return ajaxResult;
	}
	
	@RequestMapping(value = "/list-json", method = RequestMethod.GET)
	public Map<String, Object> getNewsListData(ModelMap model, HttpServletRequest request) {
		//Paging
		Paging paging=new Paging();
		int limit=commonService.checkVDInt(request.getParameter("limit"), 15);
		int currentPage=commonService.checkVDInt(request.getParameter("currentPage"), 0);
		
		if(currentPage<=0){
			currentPage=0;
		}
		
		paging.setLimit(limit);
		paging.setCurrentPage(currentPage);
		
		// 전체 게시판 갯수 확인
		long totalCount = newsDataService.count();
		paging.setTotalCount((int)totalCount);
		
		PageRequest pageRequest=new PageRequest(paging.getCurrentPage(), paging.getLimit(), Direction.DESC, "NEWS_IDX");
		Page<NewsData> newsDatas=newsDataService.findAll(pageRequest);
		
		Map<String, Object> resultMap=new HashMap<>();
		resultMap.put("newsDatas", newsDatas);
		resultMap.put("paging", paging);
		return resultMap;
	}

	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public NewsData getNewsDetail(ModelMap model, @PathVariable String id) {
		LOG.info("where : moveNewsList");
		NewsData newsData=newsDataService.findById(id);
		return newsData;
	}
	
	//페이지가 가능하나, 현재 적용 불가능(실력부족)
//	@RequestMapping(value = "/detail3/{idx}", method = RequestMethod.GET)
//	public Page<NewsData> getNews3(ModelMap model, @PathVariable Integer idx, Pageable pageable) {
//		LOG.info("where : moveNewsList");
////		NewsData newsData=newsDataService.findOne(newsId);
//		Page<NewsData> newsData3=newsDataService.findByIdx(idx, pageable);
//		return newsData3;
//	}
	
	private Thread getNewsThread(Long startNumber){
		Thread thread=new Thread(){
			public void run() {
				LOG.info("return : getNewsThread : Message");
//				for (int i = 3100000; i<= 3182689; i++) {
				for (Long i = startNumber; i< 310000; i++) {
					// 리스트 가져오기
					for (NewsWebSite newsWebSite : NewsWebSite.values()) {
						String address=newsWebSite.getWebAddress();
						String webSiteName=newsWebSite.name();
						LOG.info("return : NewsWebSite : {}",newsWebSite);
						LOG.info("return : NewsWebSite : {}",address);
						LOG.info("return : NewsWebSite : {}",webSiteName);
					}
					
					try {
						String webSiteName="";
						String address="";
						
						Document doc=null;
						for (NewsWebSite newsWebSite : NewsWebSite.values()) {
							address=newsWebSite.getWebAddress();
							webSiteName=newsWebSite.name();
							doc = Jsoup.connect(address+i).timeout(8000).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.95 Safari/537.36").ignoreHttpErrors(true).get();
							LOG.info("return : NewsWebSite : {}",newsWebSite);
							LOG.info("return : NewsWebSite : {}",address);
							LOG.info("return : NewsWebSite : {}",webSiteName);
							//하나라도 신문기사가 있으면 포문을 종료시키고 넘어간다.
							if (doc != null)
								break;
						}
						
						//모두 돌았는데도 404일때 신문기사가 없으므로 돌아간다.(계속진행 시킨다.)
						if (doc == null) {
							continue;
						}
						
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
							LOG.error("return tagName : " + tag);	
							tags.add(tag);
						}
						LOG.error("return newsIdx : " + i);
						LOG.error("return newsTitle: " + newsTitle);
						LOG.error("return newsWriter: " + newsWriter);
						LOG.error("return newsHeadImage: " + newsHeadImage);
						LOG.error("return newsContent: " + newsContent);
						LOG.error("return newsSource : " + newsSource);
						newsData.setId(webSiteName+"_"+i);
						newsData.setIdx(i);
						newsData.setTitle(newsTitle);
						newsData.setCreatedBy(newsWriter);
						newsData.setFromSource(newsSource);
						newsData.setHeaderImage(newsHeadImage);
						newsData.setContent(newsContent);
						newsData.setTags(tags);
						
						newsDataService.save(newsData);
						//Example						
//						File input = new File("/tmp/input.html");
//						Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
//
//						Elements links = doc.select("a[href]"); // a with href
//						Elements pngs = doc.select("img[src$=.png]");
//						  // img with src ending .png
//
//						Element masthead = doc.select("div.masthead").first();
//						  // div with class=masthead
//
//						Elements resultLinks = doc.select("h3.r > a"); // direct a after h3
					} catch (HttpStatusException e) {
						LOG.error("ERROR : HttpStatusException");
					} catch (NullPointerException e) {
						LOG.error("ERROR : NullPointerException");
					} catch (IOException e) {
						LOG.error("ERROR : IOException");
					}
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
