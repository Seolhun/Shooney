package com.shun.blog.model.comment;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.shun.blog.model.blog.Blog;
import com.shun.blog.model.common.AbstractCommon;
import com.shun.blog.model.common.Paging;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Data
@Entity(name = "TB_COMMENT")
public class Comment extends AbstractCommon implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COMMENT_ID")
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

	@Transient
	private int currentPage;
	
	@Transient
	@JsonSerialize
	@JsonDeserialize
	private Paging paging;
}
