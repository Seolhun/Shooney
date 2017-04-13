/* Write here your custom javascript codes */
var root="/shooney";
var csrfHeader=$("#csrfHeader").attr("content");
var	csrfToken=$("#csrfToken").attr("content");

//게시판 불러올 시 댓글 볼러오기
$(document).ready(function(){
	CommentService.getCommentList();
	
});

var CommentService = (function() {
	var commentSubmit=function(){
		var object = {}
		var blogId=$("#blogId").val();
		object["content"] = document.getElementById("commentTextarea").value;
		object["blogId"] = blogId;
		$.ajax({
			url : root +"/reply/blog/insert",
			type : 'POST',
			timeout : 60000,
			data: JSON.stringify(object),	
			dataType : "json",
			beforeSend: function(xhr) {
			    xhr.setRequestHeader("Accept", "application/json");
			    xhr.setRequestHeader("Content-Type", "application/json");
			    xhr.setRequestHeader(csrfHeader, csrfToken);
			},
			success: function(data) {
				if(data){
					console.log('Success');
					document.getElementById("commentTextarea").value='';
					getCommentList();
				} else {
					console.log('Fail');
				}
			},
			error : function(e){
				console.log('Error');
			}
		});	
	}

	var _getCommentList = function() {
		var blogId=$("#blogId").val();
		$.ajax({
			url : root +"/reply/blog/list",
			type : 'GET',
			timeout : 60000,
			data: {
				blogId : blogId
			},	
	    	dataType : "json",
	    	beforeSend: function(xhr) {
			    xhr.setRequestHeader("Accept", "application/json");
			    xhr.setRequestHeader("Content-Type", "application/json");
			    xhr.setRequestHeader(csrfHeader, csrfToken);
			},
	    	success : function(response) {
	    		$("#commentDiv").empty();
	    		console.log("response",response);
//	    		var commentList=response.comments;
//	    		commentList.forEach(function(data, status, index) {
//					row+= "<div class='col-sm-3 col-xs-3'><div class='input-group margin-bottom-20'><span class='input-group-addon rounded-left'><i class='icon-user color-green'></i></span><div class='form-control rounded-right' id='commentWriter'>"+data.writer+"</div></div></div>";
//	    			row+="<div class='col-sm-3 col-xs-3'><div class='input-group margin-bottom-20'><span class='input-group-addon rounded-left'><i class='icon-envelope color-green'></i></span><div class='form-control rounded-right'>"+data.likes+"</div></div></div>";
//	    			row+="<div class='col-sm-6 col-xs-6'><div class='input-group margin-bottom-20'><span class='input-group-addon rounded-left'><i class='icon-envelope color-green'></i></span><div class='form-control rounded-right'>"+data.latestDate+"</div></div></div>";
//					row+="<div class='col-sm-12 commentContent-"+data.id+"'><div class='input-group margin-bottom-20'><span class='input-group-addon rounded-left'><i class='icon-envelope color-green'></i></span><div class='form-control rounded-right' id='commentContent' style='height : auto;'>"+data.content+"</div></div></div>";	
//					if(accessUser==data.createdBy){
//						row+="<div class='col-sm-12 text-right commentContent-"+data.id+"'><button class='btn-u btn-u-dark-blue rounded margin-right-5' onclick='CommentService.commentModify("+data.id+");'>Modify</button><button class='btn-u btn-u-red rounded' onclick='CommentService.commentDelete("+data.id+");'>Delete</button></div>";
//					}
//					//This part for Modify				
//					row+="<div class='col-sm-12 text-center margin-bottom-20' id='commentModify-"+data.id+"' hidden='true'><textarea name='content' rows='5' cols='auto' style='resize:none;' id='commentModifyTextarea'>"+data.content.replace(/<br\s*\/?>/mg,"\n")+"</textarea><div class='text-right'><button class='btn-u btn-u-default rounded margin-right-5' onclick='CommentService.modifyCancel("+data.id+");'>Cancel</button><button class='btn-u btn-u-dark-blue rounded' onclick='CommentService.commentModifySubmit("+data.id+");'>Modify</button></div></div>"
//					row+="<div class='col-sm-12'><hr></div>";					
//					$("#commentDiv").append(row);
//					row = "";
//	    		});
	    	}, error : function(e){
				console.log('Error');
			}//end success
		});//end ajax
	}//end function()
	
	var getCommentList = function(){
		_getCommentList();
	}
	
	
	var commentDelete = function(commentId){
		var check=confirm("Really?");
		if(check){
			var id= commentId;
			var writer= document.getElementById("commentWriter").innerHTML;
			var content= document.getElementById("commentContent").innerHTML;
			$.ajax({
				url : root +"/reply/blog/delete/"+id,
				type : 'GET',
				timeout : 60000,
				data : {
					'writer' : writer,
					'content' : content,
				},
				dataType : "json",
				success: function(data) {
					if(data){
						console.log('Success');
						getCommentList();
					} else {
						console.log('Fail');
					}
				},
				error : function(e){
					console.log('Error');
				}
			});	
		}
	}

	var commentModify =function(commentId) {
		$(".commentContent-"+commentId).prop("hidden",true);
		$("#commentModify-"+commentId).prop("hidden",false);
	}

	var modifyCancel = function(commentId) {
		$(".commentContent-"+commentId).prop("hidden",false);
		$("#commentModify-"+commentId).prop("hidden",true);
	}

	var commentModifySubmit=function(commentId){
		var id= commentId;
		var writer= document.getElementById("commentWriter").innerHTML;
		var content= document.getElementById("commentModifyTextarea").value;
//		var content= inputContent.replace(/\n/g, '<br/>');
		$.ajax({
			url : root +"/reply/blog/modify/"+id,
			type : 'GET',
			timeout : 60000,
			data : {
				'writer' : writer,
				'content' : content,
			},
			dataType : "json",
			success: function(data) {
				if(data){
					console.log('Success');
					getCommentList();
				} else {
					console.log('Fail');
				}
			},
			error : function(e){
				console.log('Error');
			}
		});	
	}
	 var cancelHidden=function() {
			$("#commentBtn").prop("hidden", true);
	 }
	
	return {
		getCommentList : getCommentList,
		commentModify : commentModify,
		modifyCancel : modifyCancel,
		commentModifySubmit : commentModifySubmit,
		commentDelete : commentDelete,
		cancelHidden : cancelHidden,
		commentSubmit : commentSubmit
	}
})();