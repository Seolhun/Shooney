package com.shun.blog.model.comment;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.shun.blog.model.blog.Blog;
import com.shun.blog.model.common.Paging;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_COMMENT")
public class Comment implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COMMENT_ID", nullable = false)
	private Long commentId;
	
	@ManyToOne(fetch = FetchType.LAZY, optional=true, cascade=CascadeType.DETACH)
	@JoinColumn(foreignKey = @ForeignKey(name = "COMMENT_BLOG_FK"), name = "COMMENT_BLOG_ID", referencedColumnName = "BLOG_ID", nullable=false)
	private Blog blogInComment;
	
	@Column(name = "COMMENT_ENTITY_NAME", length=20, nullable = false)
	private String entityName;

	@Column(name = "COMMENT_CONTENT", length=300 ,nullable = false)
	private String content;
	
	@Column(name = "COMMENT_LIKES")
	private int likes=0;
	
	@Column(name = "COMMENT_CREATED_BY", nullable = false, length = 60)
	private String createdBy;

	@Column(name = "COMMENT_MODIFIED_BY", length = 60)
	private String modifiedBy;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "COMMENT_CREATED_DATE")
	private Date createdDate;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "COMMENT_MODIFIED_DATE")
	private Date modifiedDate;
	
	@Column(name = "COMMENT_DELCHECK", nullable=false)
	private int delCheck=0;
	
	@Transient
	private Paging paging;
}
