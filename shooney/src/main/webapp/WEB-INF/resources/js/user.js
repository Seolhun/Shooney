/* Write here your custom javascript codes */
var root="/shooney";
var csrfHeader=$("#csrfHeader").attr("content");
var	csrfToken=$("#csrfToken").attr("content");

//회원관리 전체작업용
$(function() {
	$("#allSubmit").click(function(){
		var roleType=$("select[name=roleType]").val();
		var stateType=$("select[name=stateType]").val();
		//선택된 번호를 받기 위한 변수명과 메세지를 전달하기 위한 변수명
		var key = "", stateMsg = "", roleMsg = "";
		
		$("input[name=check]:checked").each(function(index) {
			var val = $(this).val();
			key+= val + ",";
		});
		
		if (key == "") {
			alert("Nobody is selected");
			return;
		}
		
		if (roleType=="GUEST") {
			roleMsg = "GUEST"
		} else if (roleType=="USER") {
			roleMsg = "USER"
		} else if (roleType=="START") {
			roleMsg = "START"
		} else if (roleType=="SUPERADMIN") {
			roleMsg = "SUPERADMIN"
			if (confirm("Really?")) {
			} else {
				return;
			}
		}
		
		//유저 일괄 작업 
		if (stateType=="ACTIVE") {
			stateMsg = "Actived"
		} else if (stateType=="INACTIVE") {
			stateMsg = "Inactived"
			if (confirm("Really?")) {
			} else {
				return;
			}
		} else if (stateType=="LOCKED") {
			stateMsg = "Locked"
			if (confirm("Really?")) {
			} else {
				return;
			}
		} else if (stateType=="DELETED") {
			stateMsg = "Deleted"
			if (confirm("Really?")) {
			} else {
				return;
			}
		} 
		
		if(roleType=="" && stateType=="") {
			alert("This is not right work");
			return;
		}
		
		var defaultStateMsg = "Success : User is " + stateMsg + "\n";
		var defaultRoleMsg = "Success : User is " + roleMsg + "\n";
		
		var defaultFailStateMsg = "Fail : User is " + stateMsg + "\n";
		var defaultFailRoleMsg = "Fail : User is " + roleMsg + "\n";
		$.ajax({
			url : root+"/admin/user/all/update",
			type : 'GET',
			timeout: 60000,
			data : {
				'key' : key,
				'roleType' : roleType,
				'stateType' : stateType
			},
			dataType : "json",
			beforeSend: function(xhr) {
                 xhr.setRequestHeader("Accept", "application/json");
                 xhr.setRequestHeader("Content-Type", "application/json");
                 xhr.setRequestHeader(csrfHeader, csrfToken);
			}, success : function(data) {
				if (data.result=="success") {
					console.log("Success");
					alert(defaultStateMsg+defaultRoleMsg);
					location.reload();
				} else {
					console.log("Fail");
					alert(defaultFailStateMsg+defaultFailRoleMsg);
				}
			}, error : function(error) {
				alert("Request Error : " + error);
			}
		});
	})
});

//$('#insertBtn').click(function(ev) {
//	 var data = {}
//		data["commentCreatedBy"] = $("#commentCreatedBy").val();
//		data["commentContent"] = $("#commentContent").val();
//		data["csrfParameter"] = csrfParameter;
//		data["csrfToken"] = csrfToken;
//		var target_url2 = board_path+"/"+itemId+"/commentAdd";
//		$.ajax({
//	             type: "POST",
//	             url: target_url2,
//	             timeout: 600000,
//	             cache: false,             
//	             contentType: 'application/json',
//	             data: JSON.stringify(data),
//	             dataType: 'json',         
//	             beforeSend: function(xhr) {
//	                 xhr.setRequestHeader("Accept", "application/json");
//	                 xhr.setRequestHeader("Content-Type", "application/json");
//	                 xhr.setRequestHeader(csrfHeader, csrfToken);
//	             },             
//	             error: function (e) {
//	                 console.log('ok');
//					commentListAJAX();
//					$('#commentContent').val('');
//	             }
//			});	
//});//end #insertBtn.click