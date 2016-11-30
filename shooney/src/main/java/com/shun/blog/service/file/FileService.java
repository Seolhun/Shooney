package com.shun.blog.service.file;

import java.util.List;

import com.shun.blog.model.common.Paging;
import com.shun.blog.model.file.File;

public interface FileService {

	File findById(int id);
	
	int getCount(Paging paging);
	
	List<File> findAllFile(Paging paging);

	void setFile(File file);

	void getFile(File file);

	void deleteFileById(int id);
}