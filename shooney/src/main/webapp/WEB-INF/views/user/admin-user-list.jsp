<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<spring:url value="/resources" var="resources" /><spring:url value="/resources/template" var="template"/><spring:url value="/" var="shooney"/><spring:url value="/board" var="board"/><spring:url value="/project" var="project"/><spring:url value="/portfolio" var="portfolio"/><spring:url value="/myinfo" var="myinfo"/><spring:url value="/admin" var="admin"/><spring:url value="/signup" var="signup"/><spring:url value="/login" var="login"/><spring:url value="/logout" var="logout"/><spring:url value="/it" var="it"/>
<tag:layout tab="${target}">
<div class="call-action-v1 bg-color-light">
	<div class="container">
		<div class="call-action-v1-box">
			<div class="call-action-v1-in">
				<p>Unify creative technology company providing key digital services and focused on helping our clients to build a successful business on web and mobile.</p>
			</div>
			<sec:authorize access="hasRole('SUPERADMIN')">
				<div class="call-action-v1-in inner-btn page-scroll">
			 		<a href="${signup}" class="btn-u btn-brd btn-brd-hover btn-u-dark btn-u-block margin-bottom-5">Add New User</a>
		 		</div>
		 	</sec:authorize>
		</div>
	</div>
</div>
<div class="container content-xs">
	<div class="row">
		<div class="col-sm-12">
			<div>
				<select class="select" name="roleType">
					<option label="-- Role --">
					<c:forEach items="${userProfile}" var="i">
						<option value="${i}">${i}</option>
					</c:forEach>
				</select>
				<select class="select" name="stateType">
					<option label="-- State --">
					<c:forEach items="${state}" var="i">
						<option value="${i}">${i}</option>
					</c:forEach>
				</select>
				<button type="button" class="btn-u btn-u-block rounded" id="allSubmit">Submit</button>
			</div>
			<table class="table table-hover">
	    		<thead>
		      		<tr>
		      			<th><input type="checkbox" id="allCheck"></th>
				        <th>No.</th>
				        <th>Email.</th>
				        <th>Nickname.</th>
				        <th>State.</th>
				        <th>Role.</th>
				        <th>Email Check</th>
				        <sec:authorize access="hasRole('SUPERADMIN')">
				        	<th>Edit.</th>
				        </sec:authorize>
				        <sec:authorize access="hasRole('SUPERADMIN')">
				        	<th>Delete.</th>
				        </sec:authorize>
				        
					</tr>
		    	</thead>
	    		<tbody>
				<c:forEach items="${users}" var="user">
					<tr>
						<td><input type="checkbox" name="check" value="${user.id}"></td>
						<td>${user.id}</td>
						<td>${user.email}</td>
						<td>${user.nickname}</td>
						<td>${user.state}</td>
						<td><c:forEach items="${user.userProfiles}" var="i">${i.type }<br></c:forEach></td>
						<td>${user.receiveEmail}</td>
						<td><a href="${admin }/user/modify/${user.email}" class="btn btn-default custom-width rounded">Edit</a></td>
				        <c:choose>
				        	<c:when test="${user.state.equals('ACTIVE')}">
				        		<td><a href="${admin }/user/update/state/${user.email}?type=LOCKED" class="btn-u btn-u-red custom-width rounded confirm">Locked</a></td>
				        	</c:when>
				        	<c:otherwise>
				        		<td><a href="${admin }/user/update/state/${user.email}?type=ACTIVE" class="btn-u btn-u-dark-blue custom-width rounded" id="confirm">Active</a></td>
				        	</c:otherwise>
				        </c:choose>
					</tr>
				</c:forEach>
	    		</tbody>
	    	</table>
	    	<form action="${admin}/user/list" class="form-horizontal">
		   		<div class="text-center">
		   			<a href="list?cPage=${paging.currentPage -10 < 1 ? 1 : paging.currentPage -10}" class="btn-u btn-brd btn-brd-hover btn-u-dark btn-u-block margin-bottom-5">&larr;&larr;</a>
		   			<a href="list?cPage=${paging.currentPage -1 < 1 ? 1 : paging.currentPage -1}" class="btn-u btn-brd btn-brd-hover btn-u-dark btn-u-block margin-bottom-5">&larr;</a>
		   			<c:forEach begin="${paging.blockStartNum }" end="${paging.blockEndNum}" varStatus="status">
						<a href="list?cPage=${status.index }" class="btn-u btn-brd btn-brd-hover btn-u-dark btn-u-block margin-bottom-5" 
						<c:if test="${status.index==paging.currentPage }">style="color: Green"</c:if>>${status.index}</a>
		   			</c:forEach>
		   			<a href="list?cPage=${paging.currentPage +1 > paging.totalPage ? paging.totalPage : paging.currentPage +1}" class="btn-u btn-brd btn-brd-hover btn-u-dark btn-u-block margin-bottom-5">&rarr;</a>
		   			<a href="list?cPage=${paging.currentPage +10 > paging.totalPage ? paging.totalPage : paging.currentPage +10 }" class="btn-u btn-brd btn-brd-hover btn-u-dark btn-u-block margin-bottom-5">&rarr;&rarr;</a>
		   		</div>
	   		</form>
		</div>
   	</div>
</div>
</tag:layout>
<!-- Custom & Functional JS -->
<script type="text/javascript" src="${resources}/js/user.js"></script>
