<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<spring:url value="/resources" var="resources" /><spring:url value="/resources/template" var="template" /><spring:url value="/webjars" var="webjars" /><spring:url value="/news" var="news" /><spring:url value="/admin" var="admin" />
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
					<div class="call-action-v1-in inner-btn page-scroll" data-ng-model="newsCtrl.newsData">
						<a href="${news }/modify/{{newsData.id}}"><button class="btn-u btn-u-green margin-bottom-5">Move Save Page</button></a>
					</div>
				</sec:authorize>
			</div>
		</div>
	</div>

	<!--=== End Call To Action ===-->
	<div class="container" data-ng-app="NewsAngularApp">
		<div class="content"  data-ng-controller="NewsAngularController as newsCtrl">
			<div class="row margin-bottom-30">
				<div class="col-sm-4" data-ng-model="newsCtrl.newsData">
					<div class="col-sm-12 margin-bottom-10">
						id : {{news.id}}
					</div>
					<div class="col-sm-12 margin-bottom-10">
						No : {{news.idx}}
					</div>
					<div class="col-sm-12 margin-bottom-10">
						Title : {{news.title}}
					</div>
					<div class="col-sm-12 margin-bottom-10">
						Writer : {{news.createdBy}}
					</div>
					<div class="col-sm-12 margin-bottom-10" data-ng-if="news.headerImage != ''">
						<img class="newsImage" data-ng-src="{{news.headerImage}}">
					</div>
				</div>
			</div>
			</div>
	</div>
</tag:layout>
<script type="text/javascript" src="${resources}/js/news.js"></script>