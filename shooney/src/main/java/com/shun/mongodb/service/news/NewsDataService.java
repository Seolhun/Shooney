package com.shun.mongodb.service.news;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shun.mongodb.model.news.NewsData;

public interface NewsDataService {
	
	NewsData findOneById(String id);
	
	List<NewsData> findByIdx(Long idx);

    Page<NewsData> findByIdx(Long idx, Pageable pageable);
	
	void save(NewsData newsData);
	
	void update(NewsData newsData);
	
	void delete(String id);

	List<NewsData> selectList(); 
}