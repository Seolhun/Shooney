/* Write here your custom javascript codes */
var root="/shooney";
var csrfHeader=$("#csrfHeader").attr("content");
var	csrfToken=$("#csrfToken").attr("content");


/* AJAX 통신 처리 */
var NewsAngularModule = angular.module('NewsAngularModule', []);
NewsAngularModule.controller('NewsAngularController', function ($scope, $http) {
	getNewsList();
	
	function getNewsList(){
		$http({
			method : 'GET', // 방식
			url : root +"/news/list-json", /* 통신할 URL */
			timeout : 60000,
			headers : {
				"Content-Type" : "application/json; charset=utf-8",
				csrfHeader : csrfToken
			}
	    }).then(function (response){
	    	console.log("Success");
	    	var responseData=response.data;
	    	
	    	var newsList=responseData.newsDatas.content;
	    	var paging=responseData.paging;
	    	
	    	$scope.newsList=newsList;
	    	$scope.paging=paging;
	    	
		    $scope.numberOfPages=function(){
		        return Math.ceil(paging.totalCount/paging.limit);                
		    }
		    
	    	//Ajax결과 출력    	
	    	newsList.forEach(function(data, index, status){
//	    		console.log(data);
	    	})
	    },function (error){
	    	console.log("Error"+error);
	    });
	}
	
	function PagerService() {
	    // service definition
	    var service = {};
	 
	    service.GetPager = GetPager;
	 
	    return service;
	 
	    // service implementation
	    function GetPager(totalItems, currentPage, pageSize) {
	        // default to first page
	        currentPage = currentPage || 1;
	 
	        // default page size is 10
	        pageSize = pageSize || 10;
	 
	        // calculate total pages
	        var totalPages = Math.ceil(totalItems / pageSize);
	 
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
	        var startIndex = (currentPage - 1) * pageSize;
	        var endIndex = Math.min(startIndex + pageSize - 1, totalItems - 1);
	 
	        // create an array of pages to ng-repeat in the pager control
	        var pages = _.range(startPage, endPage + 1);
	 
	        // return object with all pager properties required by the view
	        return {
	            totalItems: totalItems,
	            currentPage: currentPage,
	            pageSize: pageSize,
	            totalPages: totalPages,
	            startPage: startPage,
	            endPage: endPage,
	            startIndex: startIndex,
	            endIndex: endIndex,
	            pages: pages
	        };
	    }
	}
	    
	//We already have a limitTo filter built-in to angular,
	//let's make a startFrom filter
	NewsAngularModule.filter('startFrom', function() {
	    return function(input, start) {
	        start = +start; //parse to int
	        return input.slice(start);
	    }
	});
	
});

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
			}, success: function(data) {
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
			}, success: function(data) {
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
			}, success: function(data) {
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