package com.shun.blog.controller.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.shun.blog.controller.common.util.FileValidator;
import com.shun.blog.controller.common.util.MultiFileValidator;
import com.shun.blog.model.file.FileBucket;
import com.shun.blog.model.file.MultiFileBucket;
import com.shun.blog.service.board.BoardService;

@Controller
public class FileController {
	@Autowired
	BoardService boardService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	FileValidator fileValidator;

	@Autowired
	MultiFileValidator multiFileValidator;
	
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	// Temporary location
	private static final String UPLOAD_LOCATION = "/Users/HunSeol/Desktop/shooney/file/"; 

	@InitBinder("fileBucket")
	protected void initBinderFileBucket(WebDataBinder binder) {
		binder.setValidator(fileValidator);
	}

	@InitBinder("fileBucket")
	protected void initBinderMultiFileBucket(WebDataBinder binder) {
		binder.setValidator(multiFileValidator);
	}
	
	// ------------------------파일다운로드 부분------------------------

		@RequestMapping(value = "/singleUpload", method = RequestMethod.GET)
		public String getSingleUploadPage(ModelMap model) {
			FileBucket fileModel = new FileBucket();
			model.addAttribute("fileBucket", fileModel);
			return "singleFileUploader";
		}

		@RequestMapping(value = "/singleUpload", method = RequestMethod.POST)
		public String singleFileUpload(@Valid FileBucket fileBucket, BindingResult result, ModelMap model) throws IOException {
			if (result.hasErrors()) {
				logger.info("validation errors");
				return "singleFileUploader";
			} else {
				System.out.println("Fetching file");
				MultipartFile multipartFile = fileBucket.getFile();

				// Now do something with file...
				FileCopyUtils.copy(fileBucket.getFile().getBytes(),
						new File(UPLOAD_LOCATION + fileBucket.getFile().getOriginalFilename()));
				String fileName = multipartFile.getOriginalFilename();
				model.addAttribute("fileName", fileName);
				return "success";
			}
		}

		@RequestMapping(value = "/multiUpload", method = RequestMethod.GET)
		public String getMultiUploadPage(ModelMap model) {
			MultiFileBucket filesModel = new MultiFileBucket();
			model.addAttribute("multiFileBucket", filesModel);
			return "multiFileUploader";
		}

		@RequestMapping(value = "/multiUpload", method = RequestMethod.POST)
		public String multiFileUpload(@Valid MultiFileBucket multiFileBucket, BindingResult result, ModelMap model) throws IOException {
			if (result.hasErrors()) {
				logger.info("validation errors in multi upload");
				return "multiFileUploader";
			} else {
				logger.info("Fetching files");
				List<String> fileNames = new ArrayList<String>();
				// Now do something with file...
				for (FileBucket bucket : multiFileBucket.getFiles()) {
					FileCopyUtils.copy(bucket.getFile().getBytes(),
							new File(UPLOAD_LOCATION + bucket.getFile().getOriginalFilename()));
					fileNames.add(bucket.getFile().getOriginalFilename());
				}

				model.addAttribute("fileNames", fileNames);
				return "multiSuccess";
			}
		}
}
