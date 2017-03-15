package com.shun.blog.dao.file;

import java.util.List;

import com.shun.blog.model.file.FileData;

public interface FileRepository {
	void insert(FileData fileData);
	
	FileData selectById(Long id);
	
	List<FileData> selectList(FileData fileData);
	
	boolean delete(Long id);
}
