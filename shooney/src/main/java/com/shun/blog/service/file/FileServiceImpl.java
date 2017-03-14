package com.shun.blog.service.file;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shun.blog.dao.file.FileRepository;
import com.shun.blog.model.file.FileData;

@Service("fileService")
public class FileServiceImpl implements FileService {

	private static final Logger LOG = LoggerFactory.getLogger(FileServiceImpl.class);
	
	@Autowired
	FileRepository fileRepository;
	
	@Override
	public void insert(FileData fileData) {
		LOG.info("param : "+fileData.toString());
		fileRepository.insert(fileData);
	}
	
	@Override
	public FileData selectById(Long id) {
		LOG.info("param : "+id.toString());
		
		FileData fileData=fileRepository.selectById(id);
		LOG.info("return : "+fileData.toString());
		return fileData;
	}

	@Override
	public List<FileData> selectList(FileData fileData) {
		LOG.info("param : "+fileData.toString());
		
		List<FileData> fileDataList = fileRepository.selectList(fileData);
		LOG.info("return : "+fileDataList.toString());
		return fileDataList;
	}

	@Override
	public Boolean delete(Long id) {
		LOG.info("param : "+id.toString());
		try {
			fileRepository.delete(id);	
		} catch (NullPointerException e) {
			return false;
		}
		return true;
	}

	@Override
	public FileData update(FileData fileData) {
		LOG.info("param : "+fileData.toString());
		FileData dbFileData = fileRepository.selectById(fileData.getFileDataId());
		LOG.info("return : "+dbFileData);
		if (dbFileData != null) {
			dbFileData.setFileDataOriginName(fileData.getFileDataOriginName());
		}
		return dbFileData;
	}
}