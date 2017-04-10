/* Write here your custom javascript codes */
var root="/shooney";
var csrfHeader=$("#csrfHeader").attr("content");
var	csrfToken=$("#csrfToken").attr("content");

var CommonService = (function() {
	//File Info & Validation Check 
	var checkFileBeforeUpload=function(htmlTagId){
		var files = document.getElementById(htmlTagId), fileName, fileType;
		if(files!=null){
			files.addEventListener('change', function() {
				var fileTotal=0;
				$("#fileInfoDiv").empty();
				for(var i=0;i<this.files.length;i++){
					fileTotal+=this.files[i].size;
					if(this.files[i].size>50000000){
						alert("파일용량 초과입니다.");
						document.getElementById(htmlTagId).value="";
						$("#fileInfoDiv").empty();
						return;
					} else if(fileTotal>200000000){
						alert("파일용량 초과입니다.");
						document.getElementById(htmlTagId).value="";
						$("#fileInfoDiv").empty();
						return;
					}
					fileName=this.files[i].name;
					fileType=this.files[i].type;
					var html=
						"<div class='margin-bottom-20'>" +
							"<span class='margin-right-20'>파일이름 : "+fileName+"</span>" +
							"<span class='margin-right-20'>파일형식 : "+fileType+"</span>" +
							"<button class='btn-u btn-u-red' onclick='CommonService.checkFileBeforeUpload.fileCancel();' type='button'>&times;</button>"+
						"</div>";
					$("#fileInfoDiv").append(html);
				}
			});	
		}
	}
	
	var alertConfirm=function(message){
	    return (!confirm(message));
	}
	
	var validAllCheckedParam=function(){
		var params=[];
		$("input[name=oneCheck]:checked").each(function(index) {
			params.push($(this).val());
		});
		
		if(params.length<1){
			swal("","아무것도 체크가 되어있지 않습니다.");
		}			
		return params;
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
		alertConfirm : alertConfirm,
		validAllCheckedParam : validAllCheckedParam,
		selectDomCopy : selectDomCopy,
		checkFileBeforeUpload : checkFileBeforeUpload
	}
})();
