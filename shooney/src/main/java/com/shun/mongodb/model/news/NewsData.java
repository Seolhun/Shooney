package com.shun.mongodb.model.news;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.shun.mongodb.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Document(collection = "NewsData")
@Getter
@Setter
public class NewsData extends BaseEntity implements Serializable {
	
	@Indexed(unique = true)
	private String idx;

	private String newsTitle;

	private String newsContent;

	private List<String> newsTags;
	
	private Integer newsBoardNo;
	
	private Integer newsHit;

	private Integer newsLikes;

	private Date newsCreatedDate;
	
	private String newsCreatedBy;

	private Date newsModifiedDate;
	
	private String newsModifiedBy;

	private Integer newsDelcheck;

}
