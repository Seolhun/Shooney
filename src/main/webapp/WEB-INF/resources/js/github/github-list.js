/* Write here your custom javascript codes */
var csrfHeader=$("#csrfHeader").attr("content");
var	csrfToken=$("#csrfToken").attr("content");
var root="/shooney";

$(document).ready(function () {
    $("input[name='needOptions']").change(function(event){
        // console.log(event.target.id);
        // console.log(event.target.checked);
        if(event.target.checked){
            if(event.target.id == "needSize"){
                $("#sizeDiv").show();
            } else if(event.target.id == "needStars"){
                $("#startsDiv").show();
            } else if(event.target.id == "needForks"){
                $("#forksDiv").show();
            }
        } else {
            if(event.target.id == "needSize"){
                $("#sizeDiv").hide();
            } else if(event.target.id == "needStars"){
                $("#startsDiv").hide();
            } else if(event.target.id == "needForks"){
                $("#forksDiv").hide();
            }
        }
    });
});