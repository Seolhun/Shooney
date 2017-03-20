package com.shun.mongodb.service.news;

import java.util.List;

import com.shun.mongodb.model.news.NewsData;

public interface NewsDataService {
	
	NewsData selectById(String idx);
	
	void saveItWorld(NewsData newsData);
	
	void updateItWorld(NewsData newsData);
	
	void deleteItWorldById(String idx);

	List<NewsData> selectList(); 
}