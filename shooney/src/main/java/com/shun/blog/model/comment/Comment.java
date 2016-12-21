package com.shun.blog.model.comment;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "COMMENT")
public class Comment implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty
	@Column(name = "CONTENT", nullable = false)
	private String content;

	@Column(name = "WRITER")
	private String writer;

	@Column(name = "LATESTDATE", nullable = false)
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date latestDate;

	@Column(name = "LIKES")
	private int likes;

	@Column(name = "DELCHECK")
	private int delCheck;
	
	@Column(name = "BOARD_ID")
	private int board_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
}
