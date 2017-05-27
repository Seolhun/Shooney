/* Write here your custom javascript codes */
var csrfHeader=$("#csrfHeader").attr("content");
var	csrfToken=$("#csrfToken").attr("content");
console.log(csrfHeader);
console.log(csrfToken);
var root="/shooney";

//게시판 불러올 시 댓글 볼러오기
$(document).ready(function(){
	//댓글달기 버튼 이벤트 
	$('#commentTextarea').click(function(){
		$("#commentBtnDiv").prop("hidden", false);
	});
});

var CommentService = (function() {
	var _commentHtml = function(data, dbTime, nickname){
		var commentHtml="";
		commentHtml=
			"<div id='commentChagedDiv"+data.commentId+"'>"+
				"<div class='col-sm-12 margin-bottom-5'>" +
					"<div class='col-sm-5 col-xs-5'>" +
						"<div class='input-group'>" +
							"<span class='input-group-addon rounded-left'><i class='icon-envelope color-green'></i></span>" +
							"<div class='form-control rounded-right'>"+data.createdBy+"</div>" +
						"</div>" +
					"</div>" +
					"<div class='col-sm-5 col-xs-5'>" +
						"<div class='input-group'>" +
							"<span class='input-group-addon rounded-left'><i class='icon-envelope color-green'></i></span>" +
							"<div class='form-control rounded-right'>"+dbTime+"</div>" +
						"</div>" +
					"</div>" +
					"<div class='col-sm-2 col-xs-2'>" +
						"<div class='input-group'>" +
							"<span class='input-group-addon rounded-left'><i class='icon-envelope color-green'></i></span>" +
							"<div class='form-control rounded-right'>"+data.likes+"</div>" +
						"</div>" +
					"</div>" +
				"</div>" +
				"<div class='col-sm-12 col-xs-12 margin-bottom-5'>" +
					"<div class='col-sm-9'>" +
						"<div class='form-control2 rounded' id='comment-content"+data.commentId+"'>"+data.content+"</div>" +
					"</div>";
			if(data.createdBy===nickname){
				commentHtml+=
					"<div class='col-sm-3 text-right'>" +
						"<button class='btn-u btn-u-dark-blue rounded' onclick='CommentService.commentModifyDiv("+data.commentId+");'>수정</button>" +
						"<button class='btn-u btn-u-red rounded' onclick='CommentService.commentDeleteSubmit("+data.commentId+");'>삭제</button>" +
					"</div>";
			}
			commentHtml+=
				"</div>" +
				"<div id='commentModifiedDiv"+data.commentId+"'>"+
				"</div>" +
				"<div class='col-sm-12'><hr></div>"+
			"</div>";
		return commentHtml;
	}

	//댓글 수정시 댓글 밑에 입력 칸 뜨는 이벤트.(하나만 뜨게하였다.)
	var _modifyHtml=function(commentId){
		$(".commentModifyOne").remove();
		return modifyHtml=
			"<div class='col-sm-12 margin-bottom-5 commentModifyOne'>" +
				"<div class='col-sm-9 col-xs-9'>" +
					"<textarea name='content' rows='3' cols='auto' id='commentModifyTextarea'></textarea>" +
				"</div>" +
				"<div class='col-sm-3 col-xs-3 text-right'>" +
					"<button class='btn-u btn-u-dark-blue rounded margin-right-5' onclick='CommentService.commentModifySubmit("+commentId+")'>완료</button>" +
					"<button class='btn-u btn-u-default rounded' onclick='CommentService.commentModifyCancel("+commentId+")'>취소</button>" +
				"</div>" +
			"</div>";
	}

	var inesrtComment=function(){
		var comment = {}, blog={}, blogId=$("#blogId").val(), content=$("#commentTextarea").val();
		if(content.length>300){
			alert("댓글의 길이는 300자 이하입니다.");
		}
		blog["blogId"] = blogId;
		comment["content"] = content;
		comment["blogInComment"] = blog;
		$.ajax({
			url : root +"/reply/blog/insert",
			type : 'POST',
			timeout : 60000,
			data: JSON.stringify(comment),
			dataType : "json",
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
			    xhr.setRequestHeader("Content-Type", "application/json");
			    xhr.setRequestHeader(csrfHeader, csrfToken);
			}, success: function(data) {
				if(data.createdBy==null){
					alert("로그인이 필요합니다.");
					window.open("/shooney/login", "_blank");
					return;
				}

				$("#commentTextarea").val("");
				var dbTime = new Date(data.createdDate);
				dbTime=CommonService.customDateformat(dbTime, "yyyy-MM-dd, hh:mm");

				$("#commentDiv").prepend(_commentHtml(data, dbTime, data.createdBy));
			},
			error : function(e){
				console.log('Error');
			}
		});
	}

	var _currentPage = 0;

	var getCommentsMore = function() {
		var comment = {}, blog={}, paging={}, blogId=$("#blogId").val();
		_currentPage+=1;
		paging["currentPage"]=_currentPage;
		blog["blogId"] = blogId;
		comment["blogInComment"] = blog;
		comment["paging"] = paging;
		$.ajax({
			url : root +"/reply/blog/list/more",
			type : 'POST',
			timeout : 60000,
			data: JSON.stringify(comment),
	    	dataType : "json",
	    	beforeSend: function(xhr) {
			    xhr.setRequestHeader("Accept", "application/json");
			    xhr.setRequestHeader("Content-Type", "application/json");
			    xhr.setRequestHeader(csrfHeader, csrfToken);
			}, success: function(data, xhr) {
	    		if(data.comments!==null){
	    			var commentList=data.comments;
	    			var nickname=data.nickname;
		    		commentList.forEach(function(data, status, index) {
		    			var dbTime = new Date(data.createdDate);
						dbTime=CommonService.customDateformat(dbTime, "yyyy-MM-dd, hh:mm");
	    				$("#commentDiv").append(_commentHtml(data, dbTime, nickname));
		    		});
	    		}else {
	    			_currentPage-=1;
	    		}
	    	}, error : function(e){
				console.log('Error');
			}//end success
		});//end ajax
	}//end function()


	//댓글 수정 버튼 누를시, 입력공간 넣어주는 것.
	var commentModifyDiv =function(commentId) {
		$("#commentModifiedDiv"+commentId).append(_modifyHtml(commentId));

		//댓글 수정 성공시 비동기 Html 수정
		var content=$("#commentChagedDiv"+commentId).find("#comment-content").val();;
		console.log("content",content);

		$(".commentModifyOne").find("#commentModifyTextarea").val(content);
//		$("#commentChagedDiv"+commentId).find("#comment-content").val(content);

	}

	//댓글 수정 후 입력공간에서 취소버튼 누를 떄의 이벤트
	var commentModifyCancel = function() {
		$(".commentModifyOne").remove();
	}

	//댓글 입력 취소버튼 클릭시 이벤트.
	var commentInsertCancel = function() {
		$("#commentBtnDiv").prop("hidden", true);
	}

	//댓글 수정한것 입력하기.
	var commentModifySubmit=function(commentId){
		if(CommonService.alertConfirm("댓글을 수정하시겠습니까?")){
			return;
		}
		var comment = {}, content=$("#commentModifyTextarea").val();
		comment["commentId"]=commentId;
		comment["content"]=content;

		$.ajax({
			url : root +"/reply/blog/modify/"+commentId,
			type : 'POST',
			timeout : 60000,
			data: JSON.stringify(comment),
			dataType : "json",
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
			    xhr.setRequestHeader("Content-Type", "application/json");
			    xhr.setRequestHeader(csrfHeader, csrfToken);
			}, success: function(data) {
				console.log(data);
				$("#comment-content"+commentId).html(content);
				$(".commentModifyOne").remove();
			},
			error : function(e){
				console.log('Error');
			}
		});
	}

	//댓글 삭제한것 입력하기.
	var commentDeleteSubmit = function(commentId){
		if(CommonService.alertConfirm("댓글을 삭제하시겠습니까?")){
			return;
		}
		//삭제시 수정하던 칸 삭제하기.
		commentModifyCancel();
		var comment = {}, content=$("#commentTextarea").val();
		comment["commentId"]=commentId;
		$.ajax({
			url : root +"/reply/blog/delete/"+commentId,
			type : 'POST',
			timeout : 60000,
			data: JSON.stringify(comment),
			dataType : "json",
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
			    xhr.setRequestHeader("Content-Type", "application/json");
			    xhr.setRequestHeader(csrfHeader, csrfToken);
			}, success: function(data) {
				if(data.result=="success"){
					//댓글 삭제 성공시 Html remove
					$("#commentChagedDiv"+commentId).remove();
				} else {
				}
			},
			error : function(e){
				console.log('Error');
			}
		});
	}

	return {
		commentInsertCancel : commentInsertCancel,
		inesrtComment : inesrtComment,
		getCommentsMore : getCommentsMore,
		commentModifyDiv : commentModifyDiv,
		commentModifyCancel : commentModifyCancel,
		commentDeleteSubmit : commentDeleteSubmit,
		commentModifySubmit : commentModifySubmit,
	}
})();