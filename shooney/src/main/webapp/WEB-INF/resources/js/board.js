/* Write here your custom javascript codes */
var project="/shooney"

//댓글달기 버튼 이벤트 
$('#commentTextarea').click(function(){
	$("#commentBtn").prop("hidden", false);
});

function cancelHidden() {
	$("#commentBtn").prop("hidden", true);
}

//게시판 불러올 시 댓글 볼러오기
window.onload = function() {
	$("#commentDiv").text('');
	getCommentsList();
}

$('#commentSubmit').click(function(){
	var data = {}
	data["content"] = document.getElementById("commentTextarea").value;
	data["board_id"] = document.getElementById("board_id").innerHTML;
	data["csrfParameter"] = csrfParameter;
	data["csrfToken"] = csrfToken;
	$.ajax({
		url : project +"/reply/board/add",
		type : 'POST',
		timeout : 60000,
		data: JSON.stringify(data),	
		dataType : "json",
		beforeSend: function(xhr) {
		    xhr.setRequestHeader("Accept", "application/json");
		    xhr.setRequestHeader("Content-Type", "application/json");
		    xhr.setRequestHeader(csrfHeader, csrfToken);
		},
		success: function(data) {
			if(data){
				console.log('Success');
				getCommentsList();
			} else {
				console.log('Fail');
			}
		},
		error : function(e){
			console.log('Error');
		}
	});	
});// end 

function getCommentsList() {
	var row = "";
	$.ajax({
		url : project +"/reply/board/list",
		timeout : 60000,
    	data : {
    		'board_id' : board_id
    	},
    	dataType : "json",
    	success : function(response) {
    		$("#commentDiv").empty();
    		var commentList=response.comments;
    		var pagingObject=response.paging;
    		commentList.forEach(function(data, Status, index) {
    			row+= "<div class='col-sm-4 col-xs-4'><div class='input-group margin-bottom-20'><span class='input-group-addon rounded-left'><i class='icon-user color-green'></i></span><div class='form-control rounded-right'>"+data.writer+"</div></div></div>";
    			row+="<div class='col-sm-4 col-xs-4'><div class='input-group margin-bottom-20'><span class='input-group-addon rounded-left'><i class='icon-envelope color-green'></i></span><div class='form-control rounded-right'>"+data.likes+"</div></div></div>";
    			row+="<div class='col-sm-4 col-xs-4'><div class='input-group margin-bottom-20'><span class='input-group-addon rounded-left'><i class='icon-envelope color-green'></i></span><div class='form-control rounded-right'>"+data.latestDate+"</div></div></div>";
				row+="<div class='col-sm-12'><div class='input-group margin-bottom-20'><span class='input-group-addon rounded-left'><i class='icon-envelope color-green'></i></span><div class='form-control rounded-right' id='comment-content'>"+data.content+"</div></div><hr></div>";	
				$("#commentDiv").append(row);
    		});
    	}//end success
	});//end ajax
}//end function()