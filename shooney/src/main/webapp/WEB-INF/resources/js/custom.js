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