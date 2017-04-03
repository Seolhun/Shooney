package com.shun.mongodb.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Configuration
@EnableMongoRepositories(basePackages = "com.shun.mongodb.repository")
@PropertySource(value = { "classpath:datasource.properties" })
public class MongoConfig extends AbstractMongoConfiguration {
	
    @Autowired
    private Environment environment;
    
	@Override
	public Mongo mongo() throws Exception {
		return new MongoClient(environment.getRequiredProperty("mongodb.config.ip"), Integer.parseInt(environment.getRequiredProperty("mongodb.config.port")));
	}
	
	@Override
	protected String getDatabaseName() {
		return environment.getRequiredProperty("mongodb.config.db");
	}
	
	@Bean
	public MongoClient mongoClient() throws Exception {
		MongoCredential credential = MongoCredential.createCredential(environment.getRequiredProperty("mongodb.config.user"), environment.getRequiredProperty("mongodb.config.adminDb"), environment.getRequiredProperty("mongodb.config.pwd").toCharArray());
		ServerAddress serverAddress = new ServerAddress(environment.getRequiredProperty("mongodb.config.ip"), Integer.parseInt(environment.getRequiredProperty("mongodb.config.port")));
		MongoClient mongoClient = new MongoClient(serverAddress,Arrays.asList(credential)); 
		return mongoClient;
	}
	
	@Bean
	public MongoDbFactory mongoDbFactory() throws Exception {
		SimpleMongoDbFactory simpleMongoDbFactory=new SimpleMongoDbFactory(mongoClient(), environment.getRequiredProperty("mongodb.config.db"));
		return simpleMongoDbFactory;
	}

	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate mongoTemplate=new MongoTemplate(mongoDbFactory());
		return mongoTemplate;
	}
}