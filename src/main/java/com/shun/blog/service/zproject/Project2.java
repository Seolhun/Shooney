package com.shun.blog.service.zproject;
//package com.shun.blog.model.project;
//
//import java.io.Serializable;
//import java.util.Date;
//import java.util.List;
//
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.index.Indexed;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//@SuppressWarnings("serial")
//@Document(collection = "project")
//public class Project implements Serializable {
//
//	@Id
//	@Indexed(unique = true)
//	private int id;
//
//	private String title;
//
//	private String content;
//
//	private Date latestDate;
//
//	private List<String> images;
//
//	public Project() {
//		// id = 0;
//	}
//
//	public Project(int id, String title, String content, Date latestDate) {
//		this.id = id;
//		this.title = title;
//		this.content = content;
//		this.latestDate = latestDate;
//	}
//
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public String getTitle() {
//		return title;
//	}
//
//	public void setTitle(String title) {
//		this.title = title;
//	}
//
//	public String getContent() {
//		return content;
//	}
//
//	public void setContent(String content) {
//		this.content = content;
//	}
//
//	public Date getLatestDate() {
//		return latestDate;
//	}
//
//	public void setLatestDate(Date latestDate) {
//		this.latestDate = latestDate;
//	}
//
//	public List<String> getImages() {
//		return images;
//	}
//
//	public void setImages(List<String> images) {
//		this.images = images;
//	}
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + (int) (id ^ (id >>> 32));
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Project other = (Project) obj;
//		if (id != other.id)
//			return false;
//		return true;
//	}
//
//	@Override
//	public String toString() {
//		return "Project [id=" + id + ", title=" + title + ", content=" + content + ", latestDate=" + latestDate
//				+ ", images=" + images + "]";
//	}
//
//}
