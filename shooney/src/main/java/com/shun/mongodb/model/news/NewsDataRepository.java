package com.shun.mongodb.model.news;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsDataRepository extends MongoRepository<NewsData, String> {

	NewsData selectById(String idx);
}
