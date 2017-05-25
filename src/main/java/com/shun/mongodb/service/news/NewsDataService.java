package com.shun.mongodb.service.news;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shun.mongodb.model.news.NewsData;

public interface NewsDataService {
	
	void save(NewsData newsData);
	
	NewsData findById(String id);
	
	NewsData findByIdx(Long idx);

	Page<NewsData> findAll(Pageable pageable);
	
	long count();
	
	void update(NewsData newsData);
	
	void delete(String id);

	List<NewsData> selectList(); 
}