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
		<form:form method="POST" modelAttribute="board" class="form-horizontal" style="margin: 5%;" action="${bo}/${kind}/add">
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
						<form:select class="form-control rounded-right" path="entityName" items="${enNames}"/>
						<span class="input-group-addon rounded-left"><i class="icon-user color-green"></i></span>
						<form:select class="form-control rounded-right" path="pfName" items="${pfNames}"/>
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
						<form:input type="text" class="form-control rounded-right" path="title" placeholder="Board title"/>
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
					</div>
					<form:textarea type="text" class="form-control rounded-right" path="content" placeholder="Board content" id="summernote"/>
					
					<div>File Upload<br>
						<div style="color: blue;">
							<form:errors path="files" class="help-inline"/>
						</div>
					</div>
					<div class="input-group margin-bottom-20">
						<span class="input-group-addon rounded-left"><i class="icon-picture color-green"></i></span>
						<input name="files" type="file" class="form-control rounded-right" multiple="multiple" />
					</div>
					
					<div class="row margin-bottom-30">
						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
							<c:choose>
								<c:when test="${edit }">
									<button type="submit" value="Update" class="btn-u btn-u-dark-blue btn-block rounded">Edit</button>
								</c:when>
								<c:otherwise>
									<button type="submit" value="Registration" class="btn-u btn-u-dark-blue btn-block rounded">Submit</button>
								</c:otherwise>
							</c:choose>	
						</div>
						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6" >
							<a href="${bo}/${kind}/list"><button type="button" class="btn-u btn-u-dark-orange btn-block rounded">Cancel</button></a>
						</div>
					</div>
				</div>
			</div>
		</form:form>
	</div>
 </div>
</tag:layout>