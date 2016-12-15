/* Write here your custom javascript codes */
$('#confirm').on('click', function() {
	return confirm("정말 삭제하시겠습니까?");
});

$('#summernoteReply').summernote({
	lang : 'ko-KR', // default: 'en-US'		
	height : 100, // set editor height
	minHeight : 50, // set minimum height of editor
	placeholder : 'Write reply content here...' ,
	focus : true, // set focus to editable area after initializing summernote
	dialogsFade: true
});

$('#summernote').summernote({
	lang : 'ko-KR', // default: 'en-US'		
	height : 600, // set editor height
	minHeight : 400, // set minimum height of editor
	maxHeight : 1200, // set maximum height of editor
	placeholder : 'Write board content here...' ,
	focus : true, // set focus to editable area after initializing summernote
	dialogsFade: true
});


$(function() {
	// 최상단 체크박스 클릭
	$("#allCheck").click(function() {
		if ($("#allCheck").prop("checked")) {
			// input태그의 name이 chk인 태그들을 찾아서 checked옵션을 true로 정의
			$("input[name=check]").prop("checked", true);
		} else {
			// input태그의 name이 chk인 태그들을 찾아서 checked옵션을 false로 정의
			$("input[name=check]").prop("checked", false);
		}
	})
});

//회원관리 전체작업용
function allWork(allWorkValue) {
	var key = "";
	var msg = "";
	$("input[name=check]:checked").each(function(index) {
		var val = $(this).val();
		key+= val + ",";
	});
	
	if (key == "") {
		alert("아무도 선택되지 않았습니다.");
		return;
	}
	
	//유저 일괄 작업 
	if (allWorkValue.equals("a")) {
		msg = "is Actived"
	} else if (allWorkValue.equals("l")) {
		msg = "is Locked"
		if (confirm("정말이신가요?")) {
			
		} else {
			return;
		}
	} else if (allWorkValue.equals("d")) {
		msg = "is Deleted"
		if (confirm("정말이신가요?")) {
			
		} else {
			return;
		}
	} else {
		alert("올바른 작업이 아닙니다.");
		return;
	}
	var defaultMsg = "User is" + msg;
	var defaultNotMsg = "User is " + msg;
	$.ajax({
		url : admin + "/allup/"+allWorkValue,
		data : {
			key : key
		},
		dataType : "json",
		success : function(responseData) {
			console.log("responseData.result:" + responseData.result);
			console.log("responseData :" + responseData);
			var result = responseData.result;
			if (result == "ok") {
				alert(defaultMsg);
				location.reload();
			} else {
				alert(defaultNotMsg);
			}
		},
		error : function(error) {
			alert("Request Error : " + error);
		}
	});
};