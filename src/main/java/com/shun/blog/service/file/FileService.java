package com.shun.blog.service.file;

import java.io.IOException;
import java.util.List;

import com.shun.blog.model.blog.Blog;
import com.shun.blog.model.file.FileData;

public interface FileService {
	void insert(FileData fileData) throws IOException, Exception;

	FileData selectById(Long id);
	
	List<FileData> selectList(FileData fileData) throws Exception;
	
	List<FileData> selectListByBlog(Blog blog) throws Exception;
	
	FileData update(FileData fileData);

	Boolean delete(Long id);
}