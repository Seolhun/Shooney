package com.shun.blog.repository.file;

import java.util.List;

import com.shun.blog.model.blog.Blog;
import com.shun.blog.model.file.FileData;

public interface FileRepository {
	void insert(FileData fileData);
	
	FileData selectById(Long id);
	
	List<FileData> selectListByBlog(Blog blog);
	
	List<FileData> selectList(FileData fileData);
	
	boolean delete(Long id);
}
