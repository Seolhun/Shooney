package com.shun.blog.model.portfolio.project;

import java.util.Date;
import java.util.List;

public class Project {

	private long id;

	private String title;

	private String content;

	private Date latestDate;

	private List<String> images;

	public Project() {
//		id = 0;
	}

	public Project(long id, String title, String content, Date latestDate) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.latestDate = latestDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
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
