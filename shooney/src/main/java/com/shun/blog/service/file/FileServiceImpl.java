package com.shun.blog.service.file;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shun.blog.common.model.Paging;
import com.shun.blog.model.file.FileData;

@Service("fileService")
@Transactional
public class FileServiceImpl implements FileService {

	@Override
	public FileData findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCount(Paging paging) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<FileData> findAllFile(Paging paging) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFile(FileData file) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getFile(FileData file) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFileById(int id) {
		// TODO Auto-generated method stub
		
	}

	
}