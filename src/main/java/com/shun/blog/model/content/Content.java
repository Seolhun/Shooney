package com.shun.blog.model.content;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity(name="TB_CONTENT")
public class Content {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CONTENT_ID")
	private Long contentId;
	
	@Column(name = "CONTENT_WHERE", length=30)
	private String contentWhere;
	
	@Column(name = "CONTENT_TITLE",length=150)
	private String contentTitle;

	@Column(name = "CONTENT_CONTENT", length=300)
	private String content;
	
	@Column(name = "CONTENT_DEPTH", length=10, nullable = false)
	private int contentDepth;
	
	@Column(name = "CONTENT_PARENT_ID", length=10, nullable = true)
	private Long contentParentId;
	
	@Column(name = "CONTENT_CREATED_BY", length = 60)
	private String createdBy;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CONTENT_CREATED_DATE")
	private Date createdDate;

	@Transient
	private int type;
	
	@Transient
	private List<Content> contentList;
}
