/* Write here your custom javascript codes */
var csrfHeader=$("#csrfHeader").attr("content");
var	csrfToken=$("#csrfToken").attr("content");
var root="/shooney";
/* AJAX 통신 처리 */
var GithubService = (function() {
    var addSearchDiv = function(){
        var html = "";
        return html;
    };

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
    };

    var _devideCommaToList= function(param){
        var return_list = [];
        if(param.includes(',')){
            var temp_list = param.split(',');
            $.each(temp_list, function (index, value) {
                if($.inArray(value.trim(), return_list) === -1) {
                    if(value.trim().length > 0){
                        return_list.push(value.trim());
                    }
                }else{
                    console.log(value+" is a duplicate value");
                }
            });
            return return_list;
        } else {
            if(param.trim().length > 0) {
                return_list.push(param.trim());
            }
            return return_list;
        }
    };

    var _divideSearchStr = function(gitSearch){
        var topics, languages, names, topic, language, name, searchType, order;
        var minSize, maxSize, minStars, maxStars, minForks, maxForks, clientInfo={};

        searchType = $("select[name='searchType']").val().trim();
        order = $("select[name='searchOrder']").val().trim();

        minSize = $("input[name='minSize']").val();
        maxSize = $("input[name='maxSize']").val();
        minStars = $("input[name='minStars']").val();
        maxStars = $("input[name='maxStars']").val();
        minForks = $("input[name='minForks']").val();
        maxForks = $("input[name='maxForks']").val();

        topic = $("input[name='topic']").val();
        language = $("input[name='language']").val();
        name = $("input[name='repoName']").val();

        topics=_devideCommaToList(topic);
        languages=_devideCommaToList(language);
        names=_devideCommaToList(name);

        gitSearch = {
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
        };
        return gitSearch;
    };

    var githubSearch = function(currentPage){
        var gitSearch = {}, clientInfo={};
        gitSearch = _divideSearchStr(gitSearch);
        _setNavigator(clientInfo);
        gitSearch["searchUser"] = clientInfo;

        if(currentPage === null || currentPage === undefined){
            currentPage = 1;
        }
        gitSearch["currentPage"] = currentPage;

        $.ajax({
            url : root +"/github/search",
            type : 'POST',
            timeout : 60000,
            data: JSON.stringify(gitSearch),
            dataType : "json",
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
                xhr.setRequestHeader(csrfHeader, csrfToken);
            }, success: function(data) {
                if(data === null){
                    alert("Insert search paramerter exactly")
                } else {
                    console.log(data);
                    $("#searchResult").empty();
                    $("#searchResult").append(data);
                }
            }, error : function(error){
                console.log('Error', error);
            }
        });
    };


    return {
        addSearchDiv : addSearchDiv,
        githubSearch : githubSearch
    };
})();
