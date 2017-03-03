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

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.shun.blog.model.file.FileData;

@Entity
@Table(name = "TB_BOARD")
public class Board implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty
	@Column(name = "TITLE", nullable = false)
	private String title;

	@NotEmpty
	@Column(name = "CONTENT", nullable = false)
	private String content;

	@Column(name = "WRITER")
	private String writer;

	@Column(name = "LATESTDATE", nullable = false)
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date latestDate;

	@Column(name = "HITS")
	private int hits;

	@Column(name = "LIKES")
	private int likes;

	@Column(name = "DELCHECK")
	private int delCheck;

	@Column(name = "DEPTH")
	private int depth;

	@Column(name = "ENTITYNAME")
	private String entityName;

	@Column(name = "PFNAME")
	private String pfName;
	
//	@OneToMany
//	@JoinTable(name = "COMMENT", joinColumns = { @JoinColumn(name = "BOARD_ID") })
//	private List<Comment> comments;
	
//	@Transient	
	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
	private Set<FileData> files=new HashSet<FileData>();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public Date getLatestDate() {
		return latestDate;
	}

	public void setLatestDate(Date latestDate) {
		this.latestDate = latestDate;
	}

	public int getDelCheck() {
		return delCheck;
	}

	public void setDelCheck(int delCheck) {
		this.delCheck = delCheck;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getPfName() {
		return pfName;
	}

	public void setPfName(String pfName) {
		this.pfName = pfName;
	}

	public Set<FileData> getFiles() {
		return files;
	}

	public void setFiles(Set<FileData> files) {
		this.files = files;
	}
}
