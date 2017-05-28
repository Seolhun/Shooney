/**
 * Created by hunseol on 2017. 5. 25..
 */
/* Write here your custom javascript codes */
var csrfHeader=$("#csrfHeader").attr("content"), csrfToken=$("#csrfToken").attr("content"), root="/shooney";
var protocol = window.location.protocol, host = window.location.host, pathname = window.location.pathname;
var thisPage = protocol+host+pathname;
console.log(thisPage);

var MenuService = (function () {
    var menuUpdateForm = function (menuId) {
        $("#menuIdInput").val(menuId);
        $("#menuNameInput").val($("#menuName" + menuId).html());
        $("#menuTypeInput").val($("#menuType" + menuId).html());
        $("#menuUrlInput").val($("#menuUrl" + menuId).html());
        $("#menuOrderInput").val($("#menuOrder" + menuId).html());
        $("#menuDepthInput").val($("#menuDepth" + menuId).html());
        $("#menuParentIdInput").val($("#menuParentId" + menuId).html());

        $("#updateMenuModal").modal("show");
    };

    var menuInsertForm = function () {
        $("#menuNameInsert").val("");
        $("#menuTypeInsert").val("");
        $("#menuUrlInsert").val("");
        $("#menuOrderInsert").val("");
        $("#menuDepthInsert").val("");
        $("#menuParentIdInsert").val("");

        $("#insertMenuModal").modal("show");
    };

    var menuDelete = function (menuId) {
        var menu = {}, menuType;
        menuType = $("#menuType" + menuId).html();
        menu["menuId"] = menuId;
        menu["menuType"] = menuType;

        $.ajax({
            url : root +"/admin/menu/delete",
            type : 'POST',
            timeout : 60000,
            data: JSON.stringify(menu),
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

    var menuUpdate = function () {
        var menu = {}, menuId, menuName, menuType, menuUrl, menuOrder, menuDepth, menuParentId;
        menuId = $("#menuIdInput").val();
        menuName = $("#menuNameInput").val();
        menuType = $("#menuTypeInput").val();
        menuUrl = $("#menuUrlInput").val();
        menuOrder = $("#menuOrderInput").val();
        menuDepth = $("#menuDepthInput").val();
        menuParentId = $("#menuParentIdInput").val();

        menu["menuId"] = menuId;
        menu["menuName"] = menuName;
        menu["menuType"] = menuType;
        menu["menuUrl"] = menuUrl;
        menu["menuOrder"] = menuOrder;
        menu["menuDepth"] = menuDepth;
        menu["menuParentId"] = menuParentId;

        $.ajax({
            url : root +"/admin/menu/update",
            type : 'POST',
            timeout : 60000,
            data: JSON.stringify(menu),
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

    var menuInsert = function () {
        var menu = {}, menuName, menuType, menuUrl, menuOrder, menuDepth, menuParentId;
        menuName = $("#menuNameInsert").val();
        menuType = $("#menuTypeInsert").val();
        menuUrl = $("#menuUrlInsert").val();
        menuOrder = $("#menuOrderInsert").val();
        menuDepth = $("#menuDepthInsert").val();
        menuParentId = $("#menuParentIdInsert").val();

        menu["menuName"] = menuName;
        menu["menuType"] = menuType;
        menu["menuUrl"] = menuUrl;
        menu["menuOrder"] = menuOrder;
        menu["menuDepth"] = menuDepth;
        menu["menuParentId"] = menuParentId;

        $.ajax({
            url : root +"/admin/menu/insert",
            type : 'POST',
            timeout : 60000,
            data: JSON.stringify(menu),
            dataType : "json",
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
                xhr.setRequestHeader(csrfHeader, csrfToken);
            }, success: function() {
                alert("Success : Insert Munu");
                location.reload();
            }, error : function(error){
                console.log('Error', error);
            }
        });
    };

    return {
        menuUpdateForm : menuUpdateForm,
        menuInsertForm : menuInsertForm,
        menuDelete : menuDelete,
        menuUpdate : menuUpdate,
        menuInsert : menuInsert
    }
})();