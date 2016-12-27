package com.shun.mongodb.modelandview.project;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectDao extends MongoRepository<Project, String>{
	Project findProjectById(String id);
}
