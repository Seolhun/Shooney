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