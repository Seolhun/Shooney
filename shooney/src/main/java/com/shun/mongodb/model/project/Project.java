package com.shun.mongodb.model.project;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.shun.mongodb.model.BaseEntity;

@SuppressWarnings("serial")
@Document(collection = "project")
public class Project extends BaseEntity implements Serializable {

	@Indexed(unique = true)
	private String id;

	private String title;

	private String content;

	private Date latestDate;

	private List<String> images;

	public Project() {
		// id = 0;
	}

	public Project(String id, String title, String content, Date latestDate) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.latestDate = latestDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public Date getLatestDate() {
		return latestDate;
	}

	public void setLatestDate(Date latestDate) {
		this.latestDate = latestDate;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", title=" + title + ", content=" + content + ", latestDate=" + latestDate
				+ ", images=" + images + "]";
	}

}
