package com.shun.blog.model.blog;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;

import com.shun.blog.model.comment.Comment;
import com.shun.blog.model.file.FileData;

import lombok.Data;

@Entity
@Table(name = "TB_BLOG")
@Data
public class Blog implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BLOG_ID")
	private Long blogId;
	
	@Column(name = "BLOG_IDX")
	private Long idx;
	
	@Column(name = "BLOG_TITLE",length=150 , nullable = false)
	private String title;

	@Column(name = "BLOG_CONTENT", length=300, nullable = false)
	private String content;
	
	@Column(name = "BLOG_HITS", nullable=false)
	private int hits=0;

	@Column(name = "BLOG_LIKES", nullable=false)
	private int likes=0;

	@Column(name = "BLOG_DEPTH", nullable=false)
	private int depth=0;

	@Column(name = "BLOG_ENTITY_NAME", length=30)
	private String entityName;

	@Column(name = "BLOG_PORTFOLIO_TYPE", length=30)
	private String portfolioType;
	
	@Column(name = "BLOG_CREATED_BY", length = 60, nullable = false)
	private String createdBy;

	@Column(name = "BLOG_MODIFIED_BY", length = 60)
	private String modifiedBy;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BLOG_CREATED_DATE")
	private Date createdDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BLOG_MODIFIED_DATE")
	private Date modifiedDate;
	
	@Column(name = "BLOG_DELCHECK", nullable=false)
	private int delCheck=0;
	
	@OneToMany(mappedBy = "blogInFile", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<FileData> fileDataList;
	
	@OneToMany(mappedBy = "blogInComment", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Comment> commentList;
	
	@Transient
	private List<MultipartFile> files;
}
