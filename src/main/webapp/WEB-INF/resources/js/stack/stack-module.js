/* Write here your custom javascript codes */
var csrfHeader=$("#csrfHeader").attr("content");
var	csrfToken=$("#csrfToken").attr("content");
var root="/shooney";

/* AJAX 통신 처리 */
var StackService = (function() {
	var getStackList = function(){
        $.ajax({
            url : root +"/api/stack",
            type : 'GET',
            timeout : 60000,
            dataType : "application/json",
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
                xhr.setRequestHeader(csrfHeader, csrfToken);
            }, success: function(data) {
                console.log(data);
            }, error : function(error){
                console.log('Error', error);
            }
        });
    };
	
	return {
        getStackList : getStackList
	};
})();