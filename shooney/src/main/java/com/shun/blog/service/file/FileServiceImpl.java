package com.shun.blog.service.file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.shun.blog.model.file.FileData;
import com.shun.blog.model.file.FileUploadOverException;
import com.shun.blog.repository.file.FileRepository;

@Service
@Transactional(transactionManager="txManager")
@PropertySource(value = { "classpath:datasource.properties" })
public class FileServiceImpl implements FileService {
	private static final Logger LOG = LoggerFactory.getLogger(FileServiceImpl.class);
	
	private FileRepository fileRepository;
	
	@Autowired
	public FileServiceImpl(FileRepository fileRepository) {
		this.fileRepository=fileRepository;
	}
	
	private static final String FILE_PATH="/Users/hunseol/Desktop/project/shooney/file/";
//	private static final String FILE_PATH="/opt/tomcat/files/";
	private static final int MAX_UPLOAD_SIZE=(1024 * 1024 * 200);
	private static final int MAX_UPLOAD_SIZE_PER_FILE=(1024 * 1024 * 50);
	
	@Override
	@Transactional(transactionManager="txManager", rollbackFor={Exception.class, IOException.class})
	public void insert(FileData fileData) throws IOException, Exception {
		LOG.info("param : insert {}", fileData.toString());
		
		//유효성 검사.
		int fileTotalSize=0;
		List<FileData> fileDataList=new ArrayList<>();
		
		//MultipartFile에 index 0은 빈값이 온다.(알아보고 처리해야함. 그래서 1로 시작)		
		MultipartFile[] files=fileData.getFiles();
		for (int i = 0; i < files.length; i++) {
			if (!files[i].isEmpty()) {
				FileData setfileInfo=new FileData();
				if(files[i].getSize()>MAX_UPLOAD_SIZE_PER_FILE){
					throw new FileUploadOverException();
				}
				
				//파일이름으로 확장자명과 파일이름 나누기.
				String originName=files[i].getOriginalFilename();
                String onlyFileExtension = originName.substring(originName.lastIndexOf("."));
                String savedName=getRandomString()+onlyFileExtension;
                
                //파일 속성을 담는다.
                Long fileSize= files[i].getSize();
                //총 파일 크기를 계산하기 위한 것.
				fileTotalSize+=files[i].getSize();
				
				byte[] bytes = files[i].getBytes();
				setfileInfo.setFileByte(bytes);
				String fileDataType=files[i].getContentType();
				
				setfileInfo.setFileDataOriginName(originName);
				setfileInfo.setFileDataSavedName(savedName);
				setfileInfo.setFileDataSavedPath(FILE_PATH);
				setfileInfo.setFileDataType(fileDataType);
				setfileInfo.setFileDataSize(fileSize);
				setfileInfo.setFileDataCreatedBy(fileData.getBlogInFile().getCreatedBy());
				//파일정보에 게시판 정보를 담는다.
				setfileInfo.setBlogInFile(fileData.getBlogInFile());
				
				//리스트에 담는다.
				fileDataList.add(setfileInfo);
			}
		}
		
		//파일 최대 요청용량 초과시 에러처리.
		if(fileTotalSize>MAX_UPLOAD_SIZE){
			throw new FileUploadOverException();
		}
		
		for (int j = 0; j < fileDataList.size(); j++) {
			//폴더 없을시 폴더 만들기.
			File directory = new File(FILE_PATH);
			if (!directory.exists()){
				directory.mkdirs();
			}
			
			File serverFile = new File(directory.getAbsolutePath() +File.separator+ fileDataList.get(j).getFileDataSavedName());
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			byte[] bytes = fileDataList.get(j).getFileByte();
			stream.write(bytes);
			stream.close();
			
			//게시판을 인서트한 후 파일을 인서트한다.
			fileRepository.insert(fileDataList.get(j));
		}
	}
	
	@Override
	public FileData selectById(Long id) {
		LOG.info("param : "+id.toString());
		
		FileData fileData=fileRepository.selectById(id);
		return fileData;
	}

	@Override
	public List<FileData> selectList(FileData fileData) {
		LOG.info("param : "+fileData.toString());
		
		List<FileData> fileDataList = fileRepository.selectList(fileData);
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
		if (dbFileData != null) {
			dbFileData=fileData;
		}
		return dbFileData;
	}
	
    private String getRandomString(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}