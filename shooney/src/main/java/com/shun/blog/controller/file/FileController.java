package com.shun.blog.controller.file;

import java.io.File;
import java.io.OutputStream;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shun.blog.model.file.FileData;
import com.shun.blog.service.file.FileService;

/**
 * Handles requests for the application file upload requests
 */
@Controller
public class FileController {
	private static final Logger LOG = LoggerFactory.getLogger(FileController.class);
	
	private FileService fileService;
	
	@Autowired
	public FileController(FileService fileService) {
		this.fileService=fileService;
	}
	
	/**
	 * 파일 다운로드 받기.
	 * 
	 * @param String portfolioType
	 * @return String  -view
	 * @throws Exception
	 */
	@GetMapping(value="/file/download/{fileId}")
	public void downloadFile(FileData fileData, HttpServletResponse response, @PathVariable Long fileId) throws Exception{
		LOG.info("param : downloadFile : {}", fileData.toString());
		
		fileData = fileService.selectById(fileId);
		String path=fileData.getFileDataSavedPath();
		String savedFileName=fileData.getFileDataSavedName();
		String originalFileName=fileData.getFileDataOriginName();
		LOG.info("param : getFile : {}", path+savedFileName);
		byte fileByte[] = FileUtils.readFileToByteArray(new File(path+savedFileName));
		
        if(fileByte.length<1){
            String errorMessage = "Sorry. The file you are looking for does not exist";
            System.out.println(errorMessage);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            outputStream.close();
            LOG.info("error : fileByte Length Problem");
            return;
        }
		
		response.setContentType("application/octet-stream");
		response.setContentLength(fileByte.length);
//		response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(originalFileName,"UTF-8"));
		response.setHeader("Content-Disposition", String.format("inline; filename=\"" + originalFileName+"\""));
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.getOutputStream().write(fileByte);
		
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
}