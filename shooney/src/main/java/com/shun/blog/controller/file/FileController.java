package com.shun.blog.controller.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Map;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPageEvent;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
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
        	LOG.info("error : fileByte Length Problem");
            String errorMessage = "Sorry. The file you are looking for does not exist";
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            outputStream.close();
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
	
	 /**
     * Image Stream
     * 
     */
    @RequestMapping(value="/file/image")
    public void getImageStream(Map<String, Object> imageMap, HttpServletResponse response) {
    	
        ServletOutputStream fout = null;
        try {
            String path = "";
            //path = webPath+"/";
            
            if(imageMap.containsKey("path")){
            	path = imageMap.get("path").toString();
            }
            
            String filename = imageMap.get("name").toString();
            String filePath = path + filename;
            
            File sz = new File(filePath);
            
            response.setHeader("Content-Disposition", "attachment;filename="+ filename +";");
            response.setContentType("image/jpeg");
            response.setContentLength((int)sz.length());
            
            byte b[] = new byte[(int)sz.length()];
            fout = response.getOutputStream();
            
            if (sz.isFile()) {
                if( !(new MimetypesFileTypeMap().getContentType(sz)).toLowerCase().startsWith("image") ) {
                    throw new FileNotFoundException("Fail mime type.");
                }
                BufferedInputStream fin = new BufferedInputStream(new FileInputStream(sz));
                BufferedOutputStream outs = new BufferedOutputStream(fout);
                int read = 0;

                while ((read = fin.read(b)) != -1) {
                    outs.write(b, 0, read);
                }
                
                outs.flush();
                outs.close();
                fin.close();
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                if(fout != null) fout.close();
            } catch(Exception e){}
        }
    }
    
    
    /*
     * BrainWaveReport PDF Down
     */
	@RequestMapping(value = "/file/brainWavePdf")
	public void pdfCreate(Map<String, Object> pdfMap, HttpServletResponse response) throws Exception {
		
		// Document 생성
		Document document = new Document(PageSize.A4, 50, 50, 50, 50); // 용지 및 여백 설정
		     
		// PdfWriter 생성
		//PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("d:/test.pdf")); 
		PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream()); // 바로 다운로드.
		writer.setInitialLeading(12.5f);
		
		// PDF 워터마크, 쪽 번호, 우측 상단 머릿말 넣기.
		PdfPageEvent event = new PdfPageEventHelper(); // Pdf 속성 설정.
		writer.setBoxSize("boxName", new Rectangle(36, 54, 559, 788));
		writer.setPageEvent(event);
		
		// 파일 다운로드 설정
		response.setContentType("application/pdf");
		String fileName = URLEncoder.encode("한글파일명", "UTF-8"); // 파일명이 한글일 땐 인코딩 필요
		response.setHeader("Content-Transper-Encoding", "binary");
		response.setHeader("Content-Disposition", "inline; filename=" + fileName + ".pdf");
		 
		// Document 오픈
		document.open();
		XMLWorkerHelper helper = XMLWorkerHelper.getInstance();
		     
		// CSS
		CSSResolver cssResolver = new StyleAttrCSSResolver();
	
		CssFile cssFile = helper.getCSS(new FileInputStream("/Users/hunseol/Desktop/pdf.css"));
		cssResolver.addCss(cssFile);
		     
		// HTML, 폰트 설정
		XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
		fontProvider.register("/Users/hunseol/Desktop/210 맨발의청춘L.ttf", "MalgunGothic"); // MalgunGothic은 alias,
		CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);
		 
		HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
		htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
		 
		// Pipelines
		PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
		HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
		CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);
		 
		XMLWorker worker = new XMLWorker(css, true);
		XMLParser xmlParser = new XMLParser(worker, Charset.forName("UTF-8"));
		
	    
		// 폰트 설정에서 별칭으로 줬던 "MalgunGothic"을 html 안에 폰트로 지정한다.
		String htmlStr = "<html><head><body style='font-family: MalgunGothic;'>"
				       + "<table border='1'>"
				       + "<tr><td>테이블안에 생성.</td></tr>"
				       + "<tr><td>테이블안에 생성2</td></tr>"
				       + "</table>"
				       + "<img src='http://localhost:8080/tunner/file/imageServlet?path=/Users/hyunyoul/Documents/test/report/&name=contents10.jpg'></img>"
		               + "<p>PDF 안에 들어갈 내용입니다.</p>"
		               + "<h3>한글, English, 漢字.</h3>"
		               + "</body></head></html>";
		
		StringReader strReader = new StringReader(htmlStr);
		xmlParser.parse(strReader);
		 
		document.close();
		writer.close();
		
	  }
}