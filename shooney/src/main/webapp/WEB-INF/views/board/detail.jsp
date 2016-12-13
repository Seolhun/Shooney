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
<div class="call-action-v1 bg-color-light">
	<div class="container">
		<div class="call-action-v1-box">
			<div class="call-action-v1-in">
				<p></p>
			</div>
			<sec:authorize access="hasRole('SUPERADMIN')">
				<div class="call-action-v1-in inner-btn page-scroll">
			 		<a href="${signup}" class="btn-u btn-brd btn-brd-hover btn-u-dark btn-u-block margin-bottom-5">Add New User</a>
		 		</div>
		 	</sec:authorize>
		</div>
	</div>
</div>
<div class="margin-bottom-20"></div>
<div class="container">
	<div class="panel panel-default row">
		<div class="col-sm-12">
			<div class="margin-bottom-20"></div>
			<div class="col-sm-6">
				<div class="input-group margin-bottom-20">
					<span class="input-group-addon rounded-left"><i class="icon-user color-green"></i></span>
					<div class="form-control rounded-right">${board.title }</div>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="input-group margin-bottom-20">
					<span class="input-group-addon rounded-left"><i class="icon-user color-green"></i></span>
					<div class="form-control rounded-right">${board.title }</div>
				</div>
			</div>
			<div class="col-sm-12">
				<div class="input-group margin-bottom-20">
					<span class="input-group-addon rounded-left"><i class="icon-envelope color-green"></i></span>
					<div class="form-control rounded-right">${board.title }</div>
				</div>
			</div>
			<div class="col-sm-12">
				<div>Content<br>
					<div class="input-group margin-bottom-20">
						<span class="input-group-addon rounded-left"><i class="icon-envelope color-green"></i></span>
						<div class="form-control rounded-right" style="height : 600px;">${board.content }</div>
					</div>
				</div>					
				<div class="row margin-bottom-30">
					<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
						<a href="${bo}/${kind}/list"><button type="button" value="list" class="btn-u btn-u-blue btn-block rounded">List</button></a>
					</div>
					<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
						<a href="${bo}/${kind}/m${board.id}"><button type="button" value="modify" class="btn-u btn-u-dark-blue btn-block rounded">Modify</button></a>
					</div>
					<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
						<a href="${bo}/${kind}/d${board.id}"><button type="button" class="btn-u btn-u-default btn-block rounded">Delete</button></a>
					</div>
				</div>
			</div>
		</div>
		<!-- 댓글 구현 -->
	</div>
 </div>
</tag:layout>