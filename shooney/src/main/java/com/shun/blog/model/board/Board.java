package com.shun.blog.model.board;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotEmpty;

import com.shun.blog.model.comment.Comment;
import com.shun.blog.model.file.FileData;

import lombok.Data;

@Entity
@Table(name = "TB_BOARD")
@Data
public class Board implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BOARD_ID")
	private Long id;
	
	@Column(name = "BOARD_IDX", nullable=false)
	private Long idx;

	@NotEmpty
	@Column(name = "BOARD_TITLE",length=150 , nullable = false)
	private String title;

	@NotEmpty
	@Column(name = "BOARD_CONTENT", length=300, nullable = false)
	private String content;
	
	@Column(name = "BOARD_HITS", nullable=false)
	private int hits=0;

	@Column(name = "BOARD_LIKES", nullable=false)
	private int likes=0;

	@Column(name = "BOARD_DEPTH", nullable=false)
	private int depth=0;

	@Column(name = "BOARD_ENTITYNAME", length=30)
	private String entityName;

	@Column(name = "BOARD_PFNAME", length=30)
	private String pfName;
	
	@Column(name = "BOARD_CREATED_BY", nullable = false, length = 60)
	private String createdBy;

	@Column(name = "BOARD_MODIFIED_BY", length = 60)
	private String modifiedBy;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BOARD_CREATED_DATE")
	private Date createdDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BOARD_MODIFIED_DATE")
	private Date modifiedDate;
	
	@Column(name = "BOARD_DELCHECK", nullable=false)
	private int delCheck=0;
	
	@OneToMany(mappedBy = "boardInFile", cascade = CascadeType.ALL)
	private Set<FileData> files=new HashSet<>();
	
	@OneToMany(mappedBy = "boardInComment", cascade = CascadeType.ALL)
	private Set<Comment> comments=new HashSet<>();
}
