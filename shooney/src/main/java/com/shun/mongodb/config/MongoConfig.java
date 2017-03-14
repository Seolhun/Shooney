package com.shun.mongodb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories(basePackages = "com.shun.mongodb.model")
public class MongoConfig extends AbstractMongoConfiguration {
	@Override
	public Mongo mongo() throws Exception {
		return new MongoClient("127.0.0.1", 27017);
	}
	
	@Bean
	public MongoClient mongoClient() throws Exception {
		MongoClient mongoClient=new MongoClient();
		return mongoClient;
	}
	
	@Bean
	public MongoDbFactory mongoDbFactory() throws Exception {
		SimpleMongoDbFactory simpleMongoDbFactory=new SimpleMongoDbFactory(mongoClient(), "shooney");
		return simpleMongoDbFactory;
	}

	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate mongoTemplate=new MongoTemplate(mongoDbFactory());
		return mongoTemplate;
	}
	
	@Override
	protected String getDatabaseName() {
		return "shooney";
	}
}