/**
 * Created by hunseol on 2017. 5. 25..
 */
/* Write here your custom javascript codes */
var csrfHeader=$("#csrfHeader").attr("content"), csrfToken=$("#csrfToken").attr("content"), root="/shooney";
var protocol = window.location.protocol, host = window.location.host, pathname = window.location.pathname;
var thisPage = protocol+host+pathname;

$(document).ready(function(){
    $('.noticeContent').summernote({
        lang : 'ko-KR', // default: 'en-US'
        height : 200, // set editor height
        minHeight : 100, // set minimum height of editor
        placeholder : 'Write reply content here...' ,
        focus : true, // set focus to editable area after initializing summernote
        dialogsFade: true
    });
});

var NoticeService = (function () {
    var noticeUpdateForm = function (noticeId) {
        $("#noticeIdUpdate").val(noticeId);
        $("#noticeUriUpdate").val($("#noticeUri" + noticeId).html());
        $("#noticeContentUpdate").val($("#noticeContent" + noticeId).html());
        $("#noticeStateUpdate").val($("#noticeState" + noticeId).html());

        $("#updateNoticeModal").modal("show");
    };

    var noticeInsertForm = function () {
        $("#noticeUri").val("");
        $("#noticeContent").val("");
        $("#noticeState").val("");

        $("#insertNoticeModal").modal("show");
    };

    var noticeDelete = function (noticeId) {
        var notice = {}, noticeType;
        noticeType = $("#noticeType" + noticeId).html();
        notice["noticeId"] = noticeId;
        notice["noticeType"] = noticeType;

        $.ajax({
            url : root +"/admin/notice/delete",
            type : 'POST',
            timeout : 60000,
            data: JSON.stringify(notice),
            dataType : "json",
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
                xhr.setRequestHeader(csrfHeader, csrfToken);
            }, success: function(data) {
                alert("Success : Request is Succeed");
                location.reload();
            }, error : function(error){
                console.log('Error', error);
            }
        });
    };

    var noticeUpdate = function () {
        var notice = {}, noticeId, noticeUri, noticeContent, noticeState;
        noticeUri = $("#noticeUri").val();
        noticeContent = $("#noticeContent").val();
        noticeState = $("#noticeState").val();

        notice["uri"] = noticeUri;
        notice["content"] = noticeContent;
        notice["delFlag"] = noticeState;

        $.ajax({
            url : root +"/admin/notice/update",
            type : 'POST',
            timeout : 60000,
            data: JSON.stringify(notice),
            dataType : "json",
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
                xhr.setRequestHeader(csrfHeader, csrfToken);
            }, success: function() {
                alert("Success : Update request is succeed");
                location.reload();
            }, error : function(error){
                console.log('Error', error);
            }
        });
    };

    var noticeInsert = function () {
        var notice = {}, noticeId, noticeUri, noticeContent, noticeState;
        noticeId = $("#noticeId").val();
        noticeUri = $("#noticeUri").val();
        noticeContent = $("#noticeContent").val();
        noticeState = $("#noticeState").val();

        notice["id"] = noticeId;
        notice["uri"] = noticeUri;
        notice["content"] = noticeContent;
        notice["delFlag"] = noticeState;

        $.ajax({
            url : root +"/admin/notice/insert",
            type : 'POST',
            timeout : 60000,
            data: JSON.stringify(notice),
            dataType : "json",
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
                xhr.setRequestHeader(csrfHeader, csrfToken);
            }, success: function() {
                alert("Success : Insert Notice");
                location.reload();
            }, error : function(error){
                console.log('Error', error);
            }
        });
    };

    return {
        noticeUpdateForm : noticeUpdateForm,
        noticeInsertForm : noticeInsertForm,
        noticeDelete : noticeDelete,
        noticeUpdate : noticeUpdate,
        noticeInsert : noticeInsert
    }
})();