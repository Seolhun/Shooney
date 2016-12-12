package com.shun.blog.model.portfolio.crawl;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ITWORLDBOARD")
public class ITworldBoard {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name = "TITLE", nullable = false)
	private String title;

	@Transient
	@Column(name = "IMAGE", nullable = false)
	private String image;

	@Column(name = "CONTENT", nullable = false)
	private String content;
	
	@Column(name = "TAGS", nullable = false)
	private String tags;

	@Column(name = "WRITER", nullable = false)
	private String writer;	
	
	@Column(name = "REGDATE", nullable = false)
	private Date regdate;
	
	@Column(name = "LATESTDATE", nullable = false)
	private Date latestdate;
	
	@Column(name = "HIT", nullable = false)
	private int hit;
	
	@Column(name = "LIKES", nullable = false)
	private int likes;

	@Column(name = "DELCHECK", nullable = false)
	private int delcheck;

	@Column(name = "DEPTH", nullable = false)
	private int depth;

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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public Date getLatestdate() {
		return latestdate;
	}

	public void setLatestdate(Date latestdate) {
		this.latestdate = latestdate;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDelcheck() {
		return delcheck;
	}

	public void setDelcheck(int delcheck) {
		this.delcheck = delcheck;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}
}
