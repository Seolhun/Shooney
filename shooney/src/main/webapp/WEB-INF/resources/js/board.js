/* Write here your custom javascript codes */
var project="/shooney"
	
//댓글달기 버튼 이벤트 
$(function() {
	$('#commentTextarea').click(function(){
		$("#commentBtn").prop("hidden", false);
	});
});

function cancelHidden() {
	$("#commentBtn").prop("hidden", true);
}

$('#commentSubmit').click(function() {
	var content=document.getElementById("commentTextarea").value;
	$.ajax({
		type : "POST",
		url : project+"/",
		timeout : 60000,
		cache : false,
		contentType : 'application/json',
		data : JSON.stringify(data),
		dataType : 'json',
		beforeSend : function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
			xhr.setRequestHeader(csrfHeader, csrfToken);
		},
		error : function(e) {
			console.log('ok');
			commentListAJAX();
			$('#commentContent').val('');
		}
	});	
});// end #insertBtn.click

// Insert Comment Ajax Event
// $('#insertBtn').click(function(ev) {
// var data = {}
// data["commentCreatedBy"] = $("#commentCreatedBy").val();
// data["commentContent"] = $("#commentContent").val();
//		data["csrfParameter"] = csrfParameter;
//		data["csrfToken"] = csrfToken;
//		var target_url2 = board_path+"/"+itemId+"/commentAdd";
//		$.ajax({
//	             type: "POST",
//	             url: target_url2,
//	             timeout: 60000,
//	             cache: false,             
//	             contentType: 'application/json',
//	             data: JSON.stringify(data),
//	             dataType: 'json',         
//	             beforeSend: function(xhr) {
//	                 xhr.setRequestHeader("Accept", "application/json");
//	                 xhr.setRequestHeader("Content-Type", "application/json");
//	                 xhr.setRequestHeader(csrfHeader, csrfToken);
//	             },             
//	             error: function (e) {
//	                 console.log('ok');
//					commentListAJAX();
//					$('#commentContent').val('');
//	             }
//			});	
//});//end #insertBtn.click