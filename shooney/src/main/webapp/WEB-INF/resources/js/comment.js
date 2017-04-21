/* Write here your custom javascript codes */
var root="/shooney";
var csrfHeader=$("#csrfHeader").attr("content");
var	csrfToken=$("#csrfToken").attr("content");

//게시판 불러올 시 댓글 볼러오기
$(document).ready(function(){
		
});

var CommentService = (function() {
	
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
				$("#commentTextarea").val("");
				if(data.createdBy==null){
					return;
				}
				var dbTime = new Date(data.createdDate);
				dbTime=CommonService.customDateformat(dbTime, "yyyy-MM-dd, hh:mm");
				
				var commentHtml=
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
						"<div class='input-group'>" +
							"<span class='input-group-addon rounded-left'><i class='icon-envelope color-green'></i></span>" +
							"<div class='form-control rounded-right' id='comment-content'>"+data.content+"</div>" +
						"</div>" +
					"</div>" +
					"<c:if test='${accessUser.nickname.equals(i.createdBy)}'>" +
						"<div class='col-sm-3 text-right'>" +
							"<button class='btn-u btn-u-dark-blue rounded' onclick='commentModify();'>Modify</button>" +
							"<button class='btn-u btn-u-red rounded' onclick='commentDelete();'>Delete</button>" +
						"</div>" +
					"</c:if>" +
				"</div>" +
				"<div class='col-sm-12'><hr></div>";
				$("#commentDiv").prepend(commentHtml);
			},
			error : function(e){
				console.log('Error');
			}
		});	
	}

	var _getCommentsMoreCount = 1;
	
	var getCommentsMore = function() {
		var comment = {}, blog={}, blogId=$("#blogId").val();
		blog["blogId"] = blogId;
		console.log("blogId", blogId);
		comment["blogInComment"] = blog;
		$.ajax({
			url : root +"/reply/blog/list/more",
			type : 'POST',
			timeout : 60000,
			data: JSON.stringify(comment),	
	    	dataType : "json",
	    	beforeSend: function(xhr) {
	    		xhr.setRequestHeader("ajax", true);
			    xhr.setRequestHeader("Accept", "application/json");
			    xhr.setRequestHeader("Content-Type", "application/json");
			    xhr.setRequestHeader(csrfHeader, csrfToken);
			}, success: function(data, xhr) {
				_getCommentsMoreCount+=1;
	    		console.log("data", data);
	    		var commentList=data.comments;
	    		commentList.forEach(function(data, status, index) {
	    			var dbTime = new Date(data.createdDate);
					dbTime=CommonService.customDateformat(dbTime, "yyyy-MM-dd, hh:mm");
					
	    			var commentHtml=
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
	    						"<div class='input-group'>" +
	    							"<span class='input-group-addon rounded-left'><i class='icon-envelope color-green'></i></span>" +
	    							"<div class='form-control rounded-right' id='comment-content'>"+data.content+"</div>" +
	    						"</div>" +
	    					"</div>" +
	    					"<c:if test='${accessUser.nickname.equals(i.createdBy)}'>" +
	    						"<div class='col-sm-3 text-right'>" +
	    							"<button class='btn-u btn-u-dark-blue rounded' onclick='commentModify();'>Modify</button>" +
	    							"<button class='btn-u btn-u-red rounded' onclick='commentDelete();'>Delete</button>" +
	    						"</div>" +
	    					"</c:if>" +
	    				"</div>" +
	    				"<div class='col-sm-12'><hr></div>";
	    				$("#commentDiv").prepend(commentHtml);
	    		});
	    		
	    	}, error : function(e){
				console.log('Error');
			}//end success
		});//end ajax
	}//end function()
	
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
						getCommentsMore();
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
					getCommentsMore();
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
		inesrtComment : inesrtComment,
		getCommentsMore : getCommentsMore,
		cancelHidden : cancelHidden,
		commentModify : commentModify,
		modifyCancel : modifyCancel,
		commentDelete : commentDelete,
		commentModifySubmit : commentModifySubmit,
	}
})();