<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<spring:url value="/resources" var="resources" />
<spring:url value="/resources/template" var="template"/>
<spring:url value="/myinfo" var="myinfo"/>
<spring:url value="/po" var="po"/>
<tag:layout tab="${target}">
<!--=== Call To Action ===-->
<div class="call-action-v1 bg-color-light">
	<div class="container">
		<div class="call-action-v1-box">
			<div class="call-action-v1-in">
				<p>GitHub API와 D3를 통해 데이터시각화를 사용합니다</p>
			</div>
			<sec:authorize access="hasRole('SUPERADMIN')">
				<div class="call-action-v1-in inner-btn page-scroll">
					<a href="insert" class="btn-u btn-u-dark-blue rounded margin-bottom-5 ">Write</a>
				</div>
			</sec:authorize>
		</div>
	</div>
</div>
<!--=== End Call To Action ===-->
<div class="container content-xs">
	<div class="row">
		<div class="col-sm-12">
			<div class="row service-box-v1">
				<div class="col-sm-12 no-margin-bottom">
					<div class="service-block service-block-default">

					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</tag:layout>