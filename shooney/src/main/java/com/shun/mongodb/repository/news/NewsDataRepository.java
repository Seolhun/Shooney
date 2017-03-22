package com.shun.mongodb.repository.news;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.shun.mongodb.model.news.NewsData;

@Repository
public interface NewsDataRepository extends MongoRepository<NewsData, String> {

	NewsData findByIdx(Long idx);

    List<NewsData> findAll();
    
    Page<NewsData> findAll(Pageable pageable);
}
