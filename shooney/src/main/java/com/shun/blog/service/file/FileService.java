package com.shun.blog.service.file;

import java.util.List;

import com.shun.blog.model.file.FileData;

public interface FileService {
	void insert(FileData fileData);

	FileData selectById(Long id);
	
	List<FileData> selectList(FileData fileData);
	
	FileData update(FileData fileData);

	Boolean delete(Long id);
}