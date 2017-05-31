/* Write here your custom javascript codes */
var csrfHeader=$("#csrfHeader").attr("content");
var	csrfToken=$("#csrfToken").attr("content");
var root="/shooney";

/* AJAX 통신 처리 */
var GithubService = (function() {
	var githubSearch = function(){
        var topics = [], topic, searchType, order;
        searchType = $("select[name='searchType']").val();
        order = $("select[name='searchOrder']").val();
        topic = $("input[name='topic']").val();
        topics.push(topic);

        $.ajax({
            url : root +"/github/search",
            type : 'POST',
            timeout : 60000,
            data: JSON.stringify({
                "topic" : topic,
                "order" : order,
                "searchType" : searchType,
                "topics" : topics
            }),
            dataType : "json",
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
                xhr.setRequestHeader(csrfHeader, csrfToken);
            }, success: function(data) {
                console.log(data);
                $("#searchResult").append(data.result);
            }, error : function(error){
                console.log('Error', error);
            }
        });
	}


	return {
        githubSearch : githubSearch
	};
})();