package com.shun.mongodb.model.news;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.shun.mongodb.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Document(collection = "NewsData")
@Getter
@Setter
public class NewsData extends BaseEntity implements Serializable {
	
	@Field(value="NEWS_IDX")
	@Indexed(unique=false)
	private Long idx;
	
	@Field(value="NEWS_HEADER_IMAGE")
	private String headerImage;
	
	@Field(value="NEWS_TITLE")
	private String title;
	
	@Field(value="NEWS_CONTENT")
	private String content;

	@Field(value="NEWS_TAGS")
	private List<String> tags;
	
	@Field(value="NEWS_IMAGES")
	private List<String> images;
	
	@Field(value="NEWS_FROM_SOURCE")
	private String fromSource;

	@Field(value="NEWS_CREATED_DATE")
	private Date createdDate;
	
	@Field(value="NEWS_CREATED_BY")
	private String createdBy;

	@Field(value="NEWS_MODIFIED_DATE")
	private Date modifiedDate;
	
	@Field(value="news_Modified_By")
	private String modifiedBy;

	@Field(value="NEWS_DEL_CHECK")
	private Integer delCheck=0;
}
