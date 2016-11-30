package com.shun.blog.model.file;

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
@Table(name = "FILE")
public class File {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "FILENAME", nullable = false)
	@NotEmpty
	private String fileName;

	@Column(name = "FILEPATH", nullable = false)
	@NotEmpty
	private String filePath;

	@Column(name = "FILESIZE", nullable = false)
	@NotEmpty
	private String fileSize;

	@Column(name = "UPLOADER", nullable = false)
	@NotEmpty
	private String uploader;

	@Column(name = "LATESTDATE", nullable = false)
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date latestDate;

	@Column(name = "ENTITYNAME")
	private String entityName;

	@Column(name = "PFNAME")
	private String pfName;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getUploader() {
		return uploader;
	}

	public void setUploader(String uploader) {
		this.uploader = uploader;
	}

	public Date getLatestDate() {
		return latestDate;
	}

	public void setLatestDate(Date latestDate) {
		this.latestDate = latestDate;
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
}
