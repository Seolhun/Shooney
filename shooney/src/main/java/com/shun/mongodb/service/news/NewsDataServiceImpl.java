package com.shun.mongodb.service.news;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shun.mongodb.model.news.NewsData;
import com.shun.mongodb.repository.news.NewsDataRepository;


@Service
@Transactional
public class NewsDataServiceImpl implements NewsDataService {
	private static final Logger LOG = LoggerFactory.getLogger(NewsDataServiceImpl.class);
	
	@Autowired(required=true)
	private NewsDataRepository newsDataRepository;
	
	@Override
	public void save(NewsData newsData) {
		newsDataRepository.save(newsData);
	}

	@Override
	public NewsData findById(String id) {
		LOG.info("param : findOne : ", id);
		return newsDataRepository.findOne(id);
	}
	
	@Override
	public NewsData findByIdx(Long idx) {
		LOG.info("param : findByIdx : ", idx);
		return newsDataRepository.findByIdx(idx);
	}
	
	@Override
	public Page<NewsData> findAll(Pageable pageable) {
		LOG.info("param : findAll : ", pageable.toString());
		return newsDataRepository.findAll(pageable);
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
		return newsDataRepository.findAll();
	}
}