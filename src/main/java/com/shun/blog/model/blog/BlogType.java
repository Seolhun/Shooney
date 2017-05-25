package com.shun.blog.model.blog;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "TB_BLOG_TYPE")
public class BlogType implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BLOG_TYPE_ID")
	private Long Id;
	
	@Column(name = "BLOG_TYPE_TITLE",length=150 , nullable = false)
	private String title;

	@Column(name = "BLOG_TYPE_COUNTS", nullable=false)
	private int counts=0;

	@Column(name = "BLOG_TYPE_DEPTH", nullable=false)
	private int depth=0;

	@Column(name = "BLOG_TYPE_CREATED_BY", length = 60, nullable = false)
	private String createdBy;

	@Column(name = "BLOG_TYPE_MODIFIED_BY", length = 60)
	private String modifiedBy;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BLOG_TYPE_CREATED_DATE")
	private Date createdDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BLOG_TYPE_MODIFIED_DATE")
	private Date modifiedDate;
	
	@Column(name = "BLOG_TYPE_DEL_FLAG")
	private String delFlag="N";

	@Version
	private int version;
}
