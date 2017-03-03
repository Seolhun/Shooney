package com.shun.blog.model.file;

import java.io.Serializable;
import java.util.Date;

public class Files implements Serializable {

	private static final long serialVersionUID = -1727698536271322844L;

	private int fileId;
	private String fileOriginName;
	private String fileSavedName;
	private String fileSavedDir;
	private String fileType;
	private int fileUploader_id;
	private Date fileCreationDate;
	private Date fileDeletedDate;
	private int fileDelFlag;
	private int itemId;

	private int count;

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public String getFileOriginName() {
		return fileOriginName;
	}

	public void setFileOriginName(String fileOriginName) {
		this.fileOriginName = fileOriginName;
	}

	public String getFileSavedName() {
		return fileSavedName;
	}

	public void setFileSavedName(String fileSavedName) {
		this.fileSavedName = fileSavedName;
	}

	public String getFileSavedDir() {
		return fileSavedDir;
	}

	public void setFileSavedDir(String fileSavedDir) {
		this.fileSavedDir = fileSavedDir;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public int getFileUploader_id() {
		return fileUploader_id;
	}

	public void setFileUploader_id(int fileUploader_id) {
		this.fileUploader_id = fileUploader_id;
	}

	public Date getFileCreationDate() {
		return fileCreationDate;
	}

	public void setFileCreationDate(Date fileCreationDate) {
		this.fileCreationDate = fileCreationDate;
	}

	public Date getFileDeletedDate() {
		return fileDeletedDate;
	}

	public void setFileDeletedDate(Date fileDeletedDate) {
		this.fileDeletedDate = fileDeletedDate;
	}

	public int getFileDelFlag() {
		return fileDelFlag;
	}

	public void setFileDelFlag(int fileDelFlag) {
		this.fileDelFlag = fileDelFlag;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
}
