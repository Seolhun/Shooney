/* Write here your custom javascript codes */
var csrfHeader=$("#csrfHeader").attr("content");
var	csrfToken=$("#csrfToken").attr("content");
var root="/shooney";


$(document).ready(function(){
   $("body").keypress(function(event){
       //13 == enter key
       if(event.keyCode == 13){
           GithubService.githubSearch()
       }
   }) ;
});