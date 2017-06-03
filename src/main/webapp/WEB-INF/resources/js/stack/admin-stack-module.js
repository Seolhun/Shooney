/* Write here your custom javascript codes */
var csrfHeader=$("#csrfHeader").attr("content");
var	csrfToken=$("#csrfToken").attr("content");
var root="/shooney";

/* AJAX 통신 처리 */
var AdminStackService = (function() {
	var stackInsert = function(){
        var stack = {}, stackName;
        stackName = $("input[name='stackName']").val();
        if(CommonService.alertConfirm(stackName+"로 하시겠습니까")){
            return;
        }
        stack["name"] = stackName;
        $.ajax({
            url : root +"/admin/stack/insert",
            type : 'POST',
            timeout : 60000,
            dataType : "json",
            data: JSON.stringify(stack),
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

    var stackListInsert = function(){
        var stack = {};
        if(CommonService.alertConfirm("전체 리스트로 하시겠습니까")){
            return;
        }
        $.ajax({
            url : root +"/admin/stack/insert/list",
            type : 'POST',
            timeout : 60000,
            dataType : "json",
            data: JSON.stringify(stack),
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
        stackInsert : stackInsert,
        stackListInsert : stackListInsert
	};
})();