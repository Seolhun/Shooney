package com.shun.mongodb.service.news;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shun.mongodb.model.news.NewsData;
import com.shun.mongodb.model.news.NewsDataRepository;


@Service
@Transactional
public class NewsDataServiceImpl implements NewsDataService {
	private static final Logger LOG = LoggerFactory.getLogger(NewsDataServiceImpl.class);
	
	@Autowired(required=true)
	private NewsDataRepository newsDataRepository;

	@Override
	public NewsData selectById(String idx) {
		return newsDataRepository.selectById(idx);
	}

	@Override
	public void insert(NewsData newsData) {
		newsDataRepository.save(newsData);
	}

	@Override
	public void update(NewsData newsData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String idx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<NewsData> selectList() {
		// TODO Auto-generated method stub
		return null;
	}
}
