/* Write here your custom javascript codes */
var root="/shooney";
var csrfHeader=$("#csrfHeader").attr("content");
var	csrfToken=$("#csrfToken").attr("content");

// 게시판 불러올 시 댓글 볼러오기
$(document).ready(function(){
	//HTML에 FileUpload시 정보띄우
	CommonService.checkFileBeforeUpload("files");
	
	
});