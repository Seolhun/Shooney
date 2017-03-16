package com.shun.blog.service.file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.shun.blog.dao.file.FileRepository;
import com.shun.blog.model.board.Board;
import com.shun.blog.model.file.FileData;
import com.shun.blog.model.file.FileUploadOverException;
import com.shun.blog.service.board.BoardService;

@Service
@Transactional(transactionManager="txManager")
public class FileServiceImpl implements FileService {
	private static final Logger LOG = LoggerFactory.getLogger(FileServiceImpl.class);
	
	private FileRepository fileRepository;
	private BoardService boardService;
	
	@Autowired
	public FileServiceImpl(FileRepository fileRepository, BoardService boardService) {
		this.fileRepository=fileRepository;
		this.boardService=boardService;
	}
	
	private static final String FILE_PATH="/Users/hunseol/Desktop/project/shooney/file/";
//	private static final String FILE_PATH="/Users/HunSeol/Desktop/project/shooney/file/";
	private static final int MAX_UPLOAD_SIZE=(1024 * 1024 * 200);
	private static final int MAX_UPLOAD_SIZE_PER_FILE=(1024 * 1024 * 50);
	
	@Override
	@Transactional(transactionManager="txManager", rollbackFor={Exception.class, IOException.class})
	public void insert(Board board, FileData fileData, MultipartFile[] files) throws IOException, Exception {
		//유효성 검사.
		int fileTotalSize=0;
		Set<FileData> fileDataList=new HashSet<>();
		
		String savedName="";
		for (int i = 0; i < files.length; i++) {
			MultipartFile multipartFile = files[i];
			if (!multipartFile.isEmpty()) {
				LOG.info("param : multipartFile index : {}", i);
				LOG.info("param : MultipartFile getContentType : {}", multipartFile.getContentType());
				LOG.info("param : MultipartFile getOriginalFilename : {}", multipartFile.getOriginalFilename());
				LOG.info("param : MultipartFile getName : {}", multipartFile.getName());
				LOG.info("param : MultipartFile getSize : {}", multipartFile.getSize());

				if(multipartFile.getSize()>MAX_UPLOAD_SIZE_PER_FILE){
					throw new FileUploadOverException();
				}
				
				//파일이름으로 확장자명과 파일이름 나누기.
				String originName=multipartFile.getOriginalFilename();
                String onlyFileExtension = originName.substring(originName.lastIndexOf("."));
                savedName=getRandomString()+onlyFileExtension;
                
				String fileDataType=multipartFile.getContentType();
				Long fileSize= multipartFile.getSize();
				fileTotalSize+=multipartFile.getSize();
				fileData.setFileDataOriginName(originName);
				fileData.setFileDataSavedName(savedName);
				fileData.setFileDataSavedPath(FILE_PATH);
				fileData.setFileDataType(fileDataType);
				fileData.setFileDataSize(fileSize);
				fileData.setFileDataCreatedBy(board.getCreatedBy());
				
				//리스트에 담는다.
				fileDataList.add(fileData);
			}
		}
		
		//파일 최대 요청용량 초과시 에러처리.
		if(fileTotalSize>MAX_UPLOAD_SIZE){
			throw new FileUploadOverException();
		}
		
		//문제 없을 시 진행.		
		boardService.insert(board);
		//파일을 나누어 인서트.
		for (int i = 0; i < files.length; i++) {
			MultipartFile multipartFile = files[i];
			if (!multipartFile.isEmpty()) {
				try {
					byte[] bytes = multipartFile.getBytes();
					//폴더 없을시 폴더 만들기.
					File directory = new File(FILE_PATH);
					if (!directory.exists()){
						directory.mkdirs();
					}
					
					File serverFile = new File(directory.getAbsolutePath() +File.separator+ savedName);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
					LOG.info("return : Server File Location : {}", serverFile.getAbsolutePath());				
					
					//게시판을 인서트한 후 파일을 인서트한다.
					fileData.setBoardInFile(board);
					fileRepository.insert(fileData);
				} catch (Exception e) {
					e.printStackTrace();
				}
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
	
    private String getRandomString(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}