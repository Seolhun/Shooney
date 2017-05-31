/* Write here your custom javascript codes */
var csrfHeader=$("#csrfHeader").attr("content");
var	csrfToken=$("#csrfToken").attr("content");
var root="/shooney";
/* AJAX 통신 처리 */
var GithubService = (function() {
    var addSearchDiv = function(){
        var html = "";

        return html;
    }

    var _setNavigator= function(object){
        object["appCodeName"] = window.navigator.appCodeName;
        object["appName"] = window.navigator.appName;
        object["appVersion"] = window.navigator.appVersion;
        object["cookieEnabled"] = window.navigator.cookieEnabled;
        object["platform"] = window.navigator.platform;
        object["productSub"] = window.navigator.productSub;
        object["vendor"] = window.navigator.vendor;
        object["language"] = window.navigator.language;
        object["languages"] = window.navigator.languages;
    }

    var githubSearch = function(){
        var topics = [], languages = [], names = [], topic, language, name, searchType, order;
        var minSize, maxSize, minStars, maxStars, minForks, maxForks, clientInfo={}, ip="";
        searchType = $("select[name='searchType']").val();
        order = $("select[name='searchOrder']").val();

        minSize = $("input[name='minSize']").val();
        maxSize = $("input[name='maxSize']").val();
        minStars = $("input[name='minStars']").val();
        maxStars = $("input[name='maxStars']").val();
        minForks = $("input[name='minForks']").val();
        maxForks = $("input[name='maxForks']").val();

        topic = $("input[name='topic']").val().trim();
        language = $("input[name='language']").val().trim();
        name = $("input[name='repoName']").val().trim();

        _setNavigator(clientInfo);
        if(topic.length > 0){
            topics.push(topic);
        }
        if(language.length > 0){
            languages.push(language);
        }
        if(name.length > 0){
            names.push(name);
        }
        $.ajax({
            url : root +"/github/search",
            type : 'POST',
            timeout : 60000,
            data: JSON.stringify({
                "order" : order,
                "searchType" : searchType,
                "minSize" : minSize,
                "maxSize" : maxSize,
                "minStars" : minStars,
                "maxStars" : maxStars,
                "minForks" : minForks,
                "maxForks" : maxForks,
                "topics" : topics,
                "languages" : languages,
                "names" : names,
                "searchUser" : clientInfo
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
        addSearchDiv : addSearchDiv,
        githubSearch : githubSearch
    };
})();
