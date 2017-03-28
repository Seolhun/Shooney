/* Write here your custom javascript codes */
var root="/shooney";
var csrfHeader=$("#csrfHeader").attr("content");
var	csrfToken=$("#csrfToken").attr("content");

/* AJAX 통신 처리 */
(function() {
	'use strict';
	angular
		.module('NewsAngularApp', [])
		.factory('PagerService', PagerService)
		.controller('NewsAngularController', NewsAngularController);
	
	//News Angular Controller
	function NewsAngularController(PagerService, $scope, $http) {
		var newsCtrl = this;
		newsCtrl.pager = {};
		newsCtrl.setPage = setPage;
		newsCtrl.getNewsList=getNewsList;
		
		// initialize to page 1
		initController();
		function initController() {
			newsCtrl.setPage(0);
		}
		
		function setPage(page) {
			if (page < 0 || page > newsCtrl.pager.totalPages) {
				newsCtrl.getNewsList(page);
				return;
			}
			newsCtrl.getNewsList(page);
			
			//function GetPager(totalCount, currentPage, limit)
			newsCtrl.pager = PagerService.GetPager(28227, page);
		}
        
		function getNewsList(currentPage) {
			$http({
				method : 'GET', // 방식
				url : root + "/news/list-json", /* 통신할 URL */
				timeout : 600000,
				contentType : 'application/json',
				params : {
					"currentPage" : currentPage
				},
				responseType : 'json',
				headers : {
					"Content-Type" : "application/json; charset=utf-8",
		            "Accept" : "application/json",
					csrfHeader : csrfToken
				}
			}).then(function(response) {
				console.log("Success");
				var responseData = response.data;
				
				var paging = response.data.paging;
				var newsList = responseData.newsDatas.content;
				
				newsCtrl.paging=paging;
				newsCtrl.newsList=newsList;
				
//				// Ajax결과 출력
//				newsList.forEach(function(data, index, status) {
//					 console.log(index);
//				})
			}, function(error) {
				console.log("Error" + error);
			});
		}
	}

	function PagerService() {
		// service definition
		var service = {};
		service.GetPager = GetPager;
		return service;

		// service implementation
		function GetPager(totalCount, currentPage, limit) {
			// default to first page
			currentPage = currentPage || 1;

			// default page size is 10
			limit = limit || 15;

			// calculate total pages
			var totalPages = Math.ceil(totalCount / limit);

			var startPage, endPage;
			if (totalPages <= 10) {
				// less than 10 total pages so show all
				startPage = 1;
				endPage = totalPages;
			} else {
				// more than 10 total pages so calculate start and end pages
				if (currentPage <= 6) {
					startPage = 1;
					endPage = 10;
				} else if (currentPage + 4 >= totalPages) {
					startPage = totalPages - 9;
					endPage = totalPages;
				} else {
					startPage = currentPage - 5;
					endPage = currentPage + 4;
				}
			}
			
			// calculate start and end item indexes
			var startIndex = (currentPage - 1) * limit;
			var endIndex = Math.min(startIndex + limit - 1, totalCount - 1);
			
			// create an array of pages to ng-repeat in the pager control
			var pages = _setArraysPage(startPage, endPage);
			
			function _setArraysPage(startPage, lastPage){
				var pages=[];
				for(var i=startPage;i<=lastPage;i++){
					pages.push(i);
				}
				return pages;
			}
			
			// return object with all pager properties required by the view
			return {
				currentPage : currentPage,
				totalCount : totalCount,
				limit : limit,
				totalPages : totalPages,
				startPage : startPage,
				endPage : endPage,
				startIndex : startIndex,
				endIndex : endIndex,
				pages : pages
			};
		}
	}
})();


// JavaScript Module Pattern
var NewsModule=(function(){
	var _saveNews = function() {
		var newsNumber=$("#newsNumber").val();
		$.ajax({
			url : root +"/news/save/"+newsNumber,
			timeout : 60000,
			beforeSend: function(xhr) {
                 xhr.setRequestHeader("Accept", "application/json");
                 xhr.setRequestHeader("Content-Type", "application/json");
                 xhr.setRequestHeader(csrfHeader, csrfToken);
			}, success: function(data) {
				if(data.result=="success"){
					console.log('Success');
				} else {
					console.log('Fail');
				}
			},
			error : function(e){
				console.log('Error');
			}
		});	
	};
	
	var saveNews = function() {
		_saveNews();
	};
	
	var _stopNews = function() {
		$.ajax({
			url : root +"/news/stop",
			timeout : 60000,
			beforeSend: function(xhr) {
                 xhr.setRequestHeader("Accept", "application/json");
                 xhr.setRequestHeader("Content-Type", "application/json");
                 xhr.setRequestHeader(csrfHeader, csrfToken);
			}, success: function(data) {
				if(data.result=="success"){
					console.log('Success');
				} else {
					console.log('Fail');
				}
			},
			error : function(e){
				console.log('Error');
			}
		});
	};
	
	var stopNews = function() {
		_stopNews();
	};
	
	var _getNewsDetail = function(){
		var newsNumber=$("#newsNumber").val();
		console.log("newsNumber : "+newsNumber);
		$.ajax({
			url : root +"/news/detail/"+newsNumber,
			timeout : 60000,
			beforeSend: function(xhr) {
                 xhr.setRequestHeader("Accept", "application/json");
                 xhr.setRequestHeader("Content-Type", "application/json");
                 xhr.setRequestHeader(csrfHeader, csrfToken);
			}, success: function(data) {
				if(data){
					console.log(data);
				} else {
					console.log('Fail');
				}
			},
			error : function(e){
				console.log('Error');
			}
		});
	} 
	
	var getNewsDetail = function(){
		_getNewsDetail();
	} 
	
	return {
		stopNews : stopNews,
		saveNews : saveNews,
		getNewsDetail : getNewsDetail
	};
})();