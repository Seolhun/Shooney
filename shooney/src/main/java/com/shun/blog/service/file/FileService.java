package com.shun.blog.service.file;

import java.io.IOException;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.shun.blog.model.board.Board;
import com.shun.blog.model.file.FileData;

@Transactional(transactionManager="txManager", noRollbackFor={NullPointerException.class})
public interface FileService {
	@Transactional(transactionManager="txManager", rollbackFor={Exception.class, IOException.class})
	void insert(Board board, FileData fileData, MultipartFile[] files) throws IOException, Exception;

	FileData selectById(Long id);
	
	List<FileData> selectList(FileData fileData);
	
	FileData update(FileData fileData);

	Boolean delete(Long id);
}