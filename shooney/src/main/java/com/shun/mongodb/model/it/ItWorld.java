package com.shun.mongodb.model.it;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.shun.mongodb.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Document(collection = "itworld")
@Getter
@Setter
public class ItWorld extends BaseEntity implements Serializable {
	
	@Indexed(unique = true)
//	private String idx;
	private String id;

	private String title;

	private String content;

	private String writer;
	
	private List<String> tags;
	
	private int boardNo;

	private Date regdate;

	private Date latestdate;

	private int hit;

	private int likes;

	private int delcheck;

}
