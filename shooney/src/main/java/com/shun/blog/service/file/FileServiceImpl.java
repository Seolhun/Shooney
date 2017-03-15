package com.shun.blog.service.file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shun.blog.dao.file.FileRepository;
import com.shun.blog.model.board.Board;
import com.shun.blog.model.file.FileData;
import com.shun.blog.model.file.FileNameInvalidException;
import com.shun.blog.model.file.FileUploadOverException;
import com.shun.blog.service.board.BoardService;

@Service("fileService")
public class FileServiceImpl implements FileService {
	private static final Logger LOG = LoggerFactory.getLogger(FileServiceImpl.class);
	
	private FileRepository fileRepository;
	private PasswordEncoder passwordEncoder;
	private BoardService boardService;
	
	@Autowired
	public FileServiceImpl(PasswordEncoder passwordEncoder, FileRepository fileRepository, BoardService boardService) {
		this.passwordEncoder = passwordEncoder;
		this.fileRepository=fileRepository;
		this.boardService=boardService;
	}
	
	private static final String FILE_PATH="/Users/hunseol/Desktop/project/shooney/file/";
	private static final int MAX_UPLOAD_SIZE=(1024 * 1024 * 200);
	private static final int MAX_UPLOAD_SIZE_PER_FILE=(1024 * 1024 * 50);
	
	@Override
	public void insert(Board board, FileData fileData, MultipartFile[] files) throws IOException, Exception {
		LOG.info("param : insert : {}",fileData.toString());
		LOG.info("param : insert : {}",files.toString());
		//유효성 검사.
		int fileTotalSize=0;
		List<FileData> fileList=new ArrayList<>();
		for (int i = 0; i < files.length; i++) {
			LOG.info("return : MultipartFile getBytes : {}", files[i].getBytes());
			LOG.info("return : MultipartFile getContentType : {}", files[i].getContentType());
			LOG.info("return : MultipartFile getOriginalFilename : {}", files[i].getOriginalFilename());
			LOG.info("return : MultipartFile getName : {}", files[i].getName());
			LOG.info("return : MultipartFile getSize : {}", files[i].getSize());
			
			String originName=files[i].getOriginalFilename();
			String savedName=passwordEncoder.encode(originName);
			String fileDataType=files[i].getContentType();
			Long fileSize= files[i].getSize();
			
			String validName[]=originName.split(".");
			if(files[i].getSize()>MAX_UPLOAD_SIZE_PER_FILE){
				throw new FileUploadOverException();
			} else if(validName.length>1){
				throw new FileNameInvalidException();
			}
			
			fileTotalSize+=files[i].getSize();
			fileData.setFileDataOriginName(originName);
			fileData.setFileDataSavedName(savedName);
			fileData.setFileDataSavedPath(FILE_PATH);
			fileData.setFileDataType(fileDataType);
			fileData.setFileDataSize(fileSize);
			fileData.setFileDataCreatedBy(board.getCreatedBy());
			
			fileList.add(fileData);
		}
		
		//파일 최대 요청용량 초과시 에러처리.
		if(fileTotalSize>MAX_UPLOAD_SIZE){
			throw new FileUploadOverException();
		}
		
		//문제 없을 시 진행.
		board.setFileDataList(fileList);
		boardService.insert(board);
		for (int i = 0; i < files.length; i++) {	
			try {
				byte[] bytes = files[i].getBytes();
				String rootPath = FILE_PATH;
				//폴더 없을시 폴더 만들기.
				File directory = new File(rootPath + File.separator);
				if (!directory.exists()){
					directory.mkdirs();
				}
				
				//파일 지정된 폴더에 전송.
				File serverFile = new File(directory.getAbsolutePath()+ File.separator + fileList.get(i).getFileDataSavedName());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				LOG.info("return : Server File Location : {}", serverFile.getAbsolutePath());
				fileData.setBoardInFile(board);
				fileRepository.insert(fileData);
			} catch (Exception e) {
				throw new FileUploadException();
			}
		}
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