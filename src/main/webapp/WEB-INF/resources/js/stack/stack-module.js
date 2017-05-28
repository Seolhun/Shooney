/* Write here your custom javascript codes */
var csrfHeader=$("#csrfHeader").attr("content");
var	csrfToken=$("#csrfToken").attr("content");
var root="/shooney";

/* AJAX 통신 처리 */
var StackService = (function() {
	var stackInsert = function(){
        var stack = {}, stackName;
        stackName = $("input[name='stackName']").val();
        stack["name"] = stackName;
        $.ajax({
            url : root +"/admin/stack/insert",
            type : 'POST',
            timeout : 60000,
            data: JSON.stringify(stack),
            dataType : "json",
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
                xhr.setRequestHeader(csrfHeader, csrfToken);
            }, success: function(data) {
                alert("Success : Insert Stack is succeed")
            }, error : function(error){
                console.log('Error', error);
            }
        });
	}
	
	return {
        stackInsert : stackInsert
	};
})();