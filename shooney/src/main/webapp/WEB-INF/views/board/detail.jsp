<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<spring:url value="/bo" var="bo"/>
<c:url value="/resources/" var="resources" />
<c:url value="/resources/template" var="template"/>
<tag:layout>
<div class="generic-container">
	<div class="panel panel-default row">
		<form:form method="POST" modelAttribute="board" class="form-horizontal" style="margin: 5%;">
			<form:input type="hidden" path="writer"/>
			<form:input type="hidden" path="hits"/>
			<form:input type="hidden" path="likes"/>
			<form:input type="hidden" path="delCheck"/>
			<form:input type="hidden" path="depth"/>
			<a href="${shooney}">
					<img src="${template}/img/themes/logo1-dark-blue.png" alt="">
				</a>
			<h2>Create new board</h2>
			<div class="reg-block">
				<div class="margin-bottom-20"></div>
				<div class="col-sm-4">
					<div class="input-group margin-bottom-20">
						<span class="input-group-addon rounded-left"><i class="icon-user color-green"></i></span>
						<form:select class="form-control rounded-right" path="entityName">
							<form:option value="${enNames}"/>
						</form:select>
						<span class="input-group-addon rounded-left"><i class="icon-user color-green"></i></span>
						<form:select class="form-control rounded-right" path="pfName">
							<form:option value="${pfNames}"/>
						</form:select>
					</div>
					<div>
						<div style="color: blue;">
							<form:errors path="entityName" class="help-inline"/>
						</div>
						<div style="color: blue;">
							<form:errors path="pfName" class="help-inline"/>
						</div>
					</div>
				</div>
				<div class="col-sm-8">
					<div class="input-group margin-bottom-20">
						<span class="input-group-addon rounded-left"><i class="icon-envelope color-green"></i></span>
						<div class="form-control rounded-right">${board.title }</div>
					</div>
					<div>
						<div style="color: blue;">
							<form:errors path="title" class="help-inline"/>
						</div>
					</div>
				</div>
				<div class="col-sm-12">
					<div>Content<br>
						<div style="color: blue;">
							<form:errors path="content" class="help-inline"/>
						</div>
					</div>
					<div class="input-group margin-bottom-20">
						<span class="input-group-addon rounded-left"><i class="icon-envelope color-green"></i></span>
						<div class="form-control rounded-right" style="height : 600px;">${board.content }</div>
					</div>
					
					<div class="row margin-bottom-30">
						<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
							<button type="submit" value="Update" class="btn-u btn-u-blue btn-block rounded">List</button>
						</div>
						<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
							<button type="submit" value="Registration" class="btn-u btn-u-dark-blue btn-block rounded">Edit</button>
						</div>
						<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
							<a href="${bo}/${kind}/list"><button type="button" class="btn-u btn-u-default btn-block rounded">Delete</button></a>
						</div>
					</div>
				</div>
			</div>
		<!-- 댓글 구현 -->
		</form:form>
	</div>
 </div>
</tag:layout>