<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<spring:url value="/resources/" var="resources" /><spring:url value="/resources/template" var="template" /><spring:url value="/webjars" var="webjars" /><spring:url value="/news" var="news" /><spring:url value="/admin" var="admin" />
<tag:layout tab="${target}">
	<!--=== Call To Action ===-->
	<div class="call-action-v1 bg-color-light">
		<div class="container">
			<div class="call-action-v1-box">
				<div class="call-action-v1-in">
					<p>
						뉴스데이터를 MongoDB에 담아 AngularJS로 출력하였습니다.
					</p>
				</div>
				<sec:authorize access="hasRole('SUPERADMIN')">
					<div class="call-action-v1-in inner-btn page-scroll">
						<a href="${admin }/news/add/cio"><button class="btn-u btn-u-green margin-bottom-5">Move Save Page</button></a>
					</div>
				</sec:authorize>
			</div>
		</div>
	</div>
		
	<!--=== End Call To Action ===-->
	<div class="container content" data-ng-app="NewsAngularModule" data-ng-controller="NewsAngularController">
		<div class="row margin-bottom-30">
		<!-- 
			$index	number	iterator offset of the repeated element (0..length-1)
			$first	boolean	true if the repeated element is first in the iterator.
			$middle	boolean	true if the repeated element is between the first and last in the iterator.
			$last	boolean	true if the repeated element is last in the iterator.
			$even	boolean	true if the iterator position $index is even (otherwise false).
			$odd
			<div ng-repeat="n in [42, 42, 43, 43] track by $index">
		-->
			<div class="col-sm-4 max-height-400" data-ng-repeat="news in newsList">
				<div class="col-sm-12 margin-bottom-10">
					{{news.idx}}
				</div>
				<div class="col-sm-12 margin-bottom-10">
					{{news.title}}
				</div>
				<div class="col-sm-12 margin-bottom-10" data-ng-if="news.headerImage != ''">
					<img class="newsImage" data-ng-src="{{news.headerImage}}">
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-sm-12">
				<div data-ng-model="paging">
					<button data-ng-click="count = count + 1" data-ng-init="count=0">
	  					Increment : {{count}}
					</button>
					<p data-ng-bind-template="{{paging.startNum}}"></p>
					<p data-ng-bind-template="{{paging.lastNum}}"></p>
					<!-- 
			    	<ul>
				        <li data-ng-repeat="item in data | startFrom:paging.startNum*paging.limit | limitTo:pageSize">
				            {{item}}
				        </li>
				    </ul>
				    <button data-ng-disabled="currentPage == 0" data-ng-click="currentPage=currentPage-1">
				        Previous
				    </button>
				    	{{currentPage+1}}/{{numberOfPages()}}
				    <button data-ng-disabled="currentPage >= data.length/pageSize - 1" data-ng-click="currentPage=currentPage+1">
				        Next
				    </button>
				     -->
				</div>
			</div>
		</div>
	</div>
</tag:layout>
<script type="text/javascript" src="${resources}/js/news.js"></script>