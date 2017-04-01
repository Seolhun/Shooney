/* Write here your custom javascript codes */
var root="/shooney";
var csrfHeader=$("#csrfHeader").attr("content");
var	csrfToken=$("#csrfToken").attr("content");

var CommonService = (function() {
	//File Info & Validation Check 
	var checkFileBeforeUpload=function(htmlTagId){
		var files = document.getElementById(htmlTagId);
		if(files!=null){
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
		selectDomCopy : selectDomCopy,
		checkFileBeforeUpload : checkFileBeforeUpload
	}
})();
