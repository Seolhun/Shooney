package com.shun.blog.service.file;

import java.util.List;

import com.shun.blog.model.common.Paging;
import com.shun.blog.model.file.FileData;

public interface FileService {

	FileData findById(int id);
	
	int getCount(Paging paging);
	
	List<FileData> findAllFile(Paging paging);

	void setFile(FileData file);

	void getFile(FileData file);

	void deleteFileById(int id);
}