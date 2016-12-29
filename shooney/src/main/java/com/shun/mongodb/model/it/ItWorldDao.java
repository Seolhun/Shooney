package com.shun.mongodb.model.it;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItWorldDao extends MongoRepository<ItWorld, String> {

	ItWorld findById(String id);
}
