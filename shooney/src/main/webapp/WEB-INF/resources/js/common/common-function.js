/* Write here your custom javascript codes */
var root="/shooney";
var csrfHeader=$("#csrfHeader").attr("content");
var	csrfToken=$("#csrfToken").attr("content");

var CommonService = (function() {
	var _alertConfirm=function(message){
	    return (!confirm(message));
	}
	
	var _validAllCheckedParam=function(){
		var params=[];
		$("input[name=oneCheck]:checked").each(function(index) {
			params.push($(this).val());
		});
		
		if(params.length<1){
			swal("","아무것도 체크가 되어있지 않습니다.");
		}			
		return params;
	}
	
	//File Info & Validation Check 
	var checkFileBeforeUpload=function(htmlTagId){
		var files = document.getElementById(htmlTagId);
		// binds to onchange event of the input field
		files.addEventListener('change', function() {
			//this.files[0].size gets the size of your file.
			console.log("files", this.files.length);
			for(var i=0;i<this.files.length;i++){
				console.log("size", this.files[i].size);
				console.log("size", this.files[i].name);
				console.log("size", this.files[i].type);
				console.log("size", this.files[i].lastModifiedDate);
			}
		});		
	}
	
	var allCheck=function(){
		// 최상단 체크박스 클릭
		if ($("input[name=allCheck]").prop("checked")) {
			// input태그의 name이 chk인 태그들을 찾아서 checked옵션을 true로 정의
			$("input[name=oneCheck]").prop("checked", true);
		} else {
			// input태그의 name이 chk인 태그들을 찾아서 checked옵션을 false로 정의
			$("input[name=oneCheck]").prop("checked", false);
		}
	}

	
	//Auto Copy when click btn
	var selectDomCopy=function(btnSelector, selectorGettingData){
		var copyTextareaBtn = document.querySelector('#copyBtn'); //btnSelector
		copyTextareaBtn.addEventListener('click', function(event) {
			var copyTextarea = document.querySelector('#newsNumber');//selectorGettingData
			copyTextarea.select();
			try {
				var successful = document.execCommand('copy');
				var msg = successful ? 'successful' : 'unsuccessful';
				console.log('Copying text command was ' + msg);
			} catch (err) {
				console.log('Oops, unable to copy');
			}
		});	
	}
	
	return {
		allCheck : allCheck,
		selectDomCopy : selectDomCopy,
		checkFileBeforeUpload : checkFileBeforeUpload
	}
})();
