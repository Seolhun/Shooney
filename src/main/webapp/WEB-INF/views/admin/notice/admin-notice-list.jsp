<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<spring:url value="/resources" var="resources" /><spring:url value="/resources/template" var="template"/><spring:url value="/" var="shooney"/><spring:url value="/board" var="board"/><spring:url value="/project" var="project"/><spring:url value="/portfolio" var="portfolio"/><spring:url value="/myinfo" var="myinfo"/><spring:url value="/admin" var="admin"/><spring:url value="/signup" var="signup"/><spring:url value="/login" var="login"/><spring:url value="/logout" var="logout"/>
<tag:admin-layout tab="${target}">
	<div class="call-action-v1 bg-color-light">
		<div class="container">
			<div class="call-action-v1-box">
				<div class="col-sm-12">
					<p>Admin Notice Control</p>
				</div>
				<sec:authorize access="hasRole('SUPERADMIN')">
					<div class="col-sm-12 text-right">
				 		<button class="btn-u btn-u-dark-blue" onclick="MenuService.menuInsertForm()">Add New Notice</button>
					</div>
			 	</sec:authorize>
			</div>
		</div>
	</div>
	<div class="container content-xs">
		<div class="row">
			<div class="col-sm-12">
				<table class="table table-hover">
		    		<thead>
			      		<tr>
                            <th class="text-center">No.</th>
							<th class="text-center">URI.</th>
							<th class="text-center">Content</th>
							<th class="text-center">State</th>
					        <sec:authorize access="hasRole('SUPERADMIN')">
					        	<th class="text-center">Edit.</th>
					        	<th class="text-center">Delete.</th>
					        </sec:authorize>
						</tr>
			    	</thead>
		    		<tbody>
					<c:forEach items="${notices }" var="notice">
						<tr class="text-center">
                            <td id="noticeId">${notice.id}</td>
							<td id="noticeUri">${notice.uri}</td>
							<td id="noticeContent">${notice.content}</td>
                            <td id="noticeState">${notice.delFlag}</td>
                            <td>
                                <button class="btn btn-default custom-width rounded" onclick="MenuService.menuUpdateForm(${notice.id})">Edit
                                </button>
                            </td>
                            <c:choose>
                                <c:when test="${notice.delFlag.equals('N')}">
                                    <td>
                                        <button class="btn-u btn-u-red rounded" onclick="MenuService.menuDelete(${notice.id})">Delete
                                        </button>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td>
                                        <button class="btn-u btn-u-dark-blue rounded" onclick="MenuService.menuDelete(${notice.id})">Active
                                        </button>
                                    </td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
					</c:forEach>
		    		</tbody>
		    	</table>
			</div>
	   	</div>
	</div>
    <%@ include file="/WEB-INF/views/admin/notice/modal/admin-notice-modal.jsp"%>
</tag:admin-layout>
<!-- Custom & Functional JS -->
<script type="text/javascript" src="${resources}/js/menu/menu-module.js"></script>
