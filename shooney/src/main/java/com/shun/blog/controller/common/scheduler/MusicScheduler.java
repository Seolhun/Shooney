package com.shun.blog.controller.common.scheduler;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.shun.blog.model.portfolio.music.MusicType;

@Component
public class MusicScheduler {
	private static final String UPLOAD_LOCATION = "/Users/HunSeol/Desktop/shooney/file/";
	
//	Second Minute - hour - day(month) - month - day(week)
	@Scheduled(cron="0/2 * * * * ?")
	public void cronJob() {
		System.out.println("2초마다 인사한다. 정기적인 시간에.");
	}
	
	@Scheduled(cron="0 0 8 * * ?")
	public void getNaverMusics() throws IOException {
		SimpleDateFormat formatter =new SimpleDateFormat("yyyy.MM.dd HH:mm",Locale.KOREA);
		Date currentTime =new Date();
		String dTime = formatter.format(currentTime);
		System.out.println("시간 출력하기 : "+dTime);
		System.out.println("음악파일을 가져옵니다.");
		JSONObject obj = new JSONObject();
		String domain="";
		for(MusicType m : MusicType.values()){
			domain=m.getMusicType();
			Document doc = Jsoup.connect("http://music.naver.com/listen/top100.nhn?domain="+domain).get();
			// 태그 혹은 속성 명으로 가져올 수 있다.
			Elements titleEl = doc.select(".name a .ellipsis");
			Elements singerEl = doc.select("._artist .ellipsis");
			Elements imageEl = doc.select(".name a img");
			JSONArray titleList = new JSONArray();
			JSONArray singerList = new JSONArray();
			JSONArray imageList = new JSONArray();
			for (Element e : titleEl) {
				titleList.add(e.html());
				System.out.println(e.html());
			}
			for (Element e : singerEl) {
				singerList.add(e.html());
				System.out.println(e.html());
			}
			for (Element e : imageEl) {
				if (!e.absUrl("src").substring(e.absUrl("src").length() - 3, e.absUrl("src").length()).equals("gif")) {
					imageList.add(e.absUrl("src"));
					System.out.println(e.absUrl("src"));
				}
			}
			obj.put("singer", singerList);
			obj.put("title", titleList);
			obj.put("image", imageList);
	
			try {
				//FileWriter file = new FileWriter("/Users/hunseol/Desktop/test.txt");
				FileWriter file = new FileWriter(UPLOAD_LOCATION+domain+(currentTime)+".txt");
				file.write(obj.toJSONString());
				file.flush();
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.print(obj);
	}
}