package com.shun.blog.model.blog;

import com.shun.blog.model.comment.Comment;
import com.shun.blog.model.file.FileData;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
@Entity
@Table(name = "TB_BLOG")
public class Blog implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BLOG_ID")
	private Long id;
	
	@Column(name = "BLOG_IDX")
	private Long idx;
	
	@OneToMany(mappedBy = "blogInFile")
	private List<FileData> fileDataList=new ArrayList<>();
	
	@OneToMany(mappedBy = "blogInComment")
	private List<Comment> commentList=new ArrayList<>();
	
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

	@Column(name = "BLOG_TYPE", length=30)
	private String blogType;
	
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
	
	@Column(name = "BLOG_DEL_FLAG", length = 1)
	private String delFlag="N";
	
	@Transient
	private List<MultipartFile> files;
	
	public Blog(){
		
	}

	public Blog(Long id){
		this.id=id;
	}
}
