package com.shun.mongodb.service.news;

import java.util.List;

import com.shun.mongodb.model.news.NewsData;

public interface NewsDataService {
	
	NewsData selectById(String idx);
	
	void insert(NewsData newsData);
	
	void update(NewsData newsData);
	
	void delete(String idx);

	List<NewsData> selectList(); 
}