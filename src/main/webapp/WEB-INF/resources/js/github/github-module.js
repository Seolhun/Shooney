/* Write here your custom javascript codes */
var csrfHeader=$("#csrfHeader").attr("content");
var	csrfToken=$("#csrfToken").attr("content");
var root="/shooney";
/* AJAX 통신 처리 */
var GithubService = (function() {
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

    var _printResultSearchDiv = function(data){
        var html = "";
        console.log(data);
        $("#searchResult").empty();
        //CommonService.customDateformat(new Date(data.createdDate), "yyyy-MM-dd mm:hh");
        var total_count = data.total_count, result_items = data.items, owner;

        html +="<div class='row'>";
            html +="<div class='col-sm-12 margin-bottom-10'>total_count : "+total_count+"</div>";
            result_items.forEach(function (item, index, status) {
                owner = item.owner;
                html +="<div class='col-sm-6'>";
                    html +="<div class='col-sm-12'><b>Item</b>";
                        html +="<div class='col-sm-12'>item id : "+item.id+"</div>";
                        html +="<div class='col-sm-12'>description : "+item.description+"</div>";
                        html +="<details class='margin-bottom-10'>";
                            html +="<summary>Owner of Item</summary>";
                            html +="<div class='col-sm-12'><b>Owner</b>";
                                html +="<div class='col-sm-12'>owner id : "+owner.id+"</div>";
                                html +="<div class='col-sm-12'>owner type : "+owner.type+"</div>";
                                html +="<div class='col-sm-12'>owner html_url : "+owner.html_url+"</div>";
                                html +="<div class='col-sm-12'>owner site_admin : "+owner.site_admin+"</div>";
                                html +="<div class='col-sm-12'>owner login : "+owner.login+"</div>";
                            html +="</div>";
                        html +="</details>";
                        html +="<div class='col-sm-12'>score : "+item.score+"</div>";
                        html +="<div class='col-sm-12'>size : "+item.size+"</div>";
                        html +="<div class='col-sm-12'>forks : "+item.forks+"</div>";
                        html +="<div class='col-sm-12'>open_issues : "+item.open_issues+"</div>";
                        html +="<div class='col-sm-12'>watchers : "+item.watchers+"</div>";
                        html +="<div class='col-sm-12'>clone_url : "+item.clone_url+"<button class='margin-left-10 btn-u btn-u-dark-blue rounded'>Copy</button></div>";
                        html +="<div class='col-sm-12'>html_url : "+item.html_url+"</div>";

                        html +="<div class='col-sm-12'>pushed_at : "+CommonService.customDateformat(new Date(item.pushed_at), "yyyy-MM-dd mm:hh")+"</div>";
                        html +="<div class='col-sm-12'>created_at : "+CommonService.customDateformat(new Date(item.created_at), "yyyy-MM-dd mm:hh")+"</div>";
                        html +="<div class='col-sm-12 margin-bottom-30'>updated_at : "+CommonService.customDateformat(new Date(item.updated_at), "yyyy-MM-dd mm:hh")+"</div>";
                    html +="</div>";
                html +="</div>";
            });
        html +="</div>";

        html +="<div></div>";
        html +="<div></div>";
        html +="<div></div>";
        html +="<div></div>";
        html +="<div></div>";
        html +="<div></div>";
        $("#searchResult").append(html);
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
                    _printResultSearchDiv(data);
                }
            }, error : function(error){
                console.log('Error', error);
            }
        });
    };


    return {
        githubSearch : githubSearch
    };
})();
