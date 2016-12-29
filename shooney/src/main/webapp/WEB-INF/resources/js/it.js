/* Write here your custom javascript codes */
var project="/shooney";
var csrfHeader=$("meta[name='_csrf_header']").attr("content");
var	csrfToken=$("meta[name='_csrf']").attr("content");

$("button[name='itWorldBtn']").click(function(){
	$.ajax({
		url : project +"/it/itworld/add",
		timeout : 60000,
		success: function(data) {
			if(data){
				console.log('Success');
			} else {
				console.log('Fail');
			}
		},
		error : function(e){
			console.log('Error');
		}
	});	
});// end 
