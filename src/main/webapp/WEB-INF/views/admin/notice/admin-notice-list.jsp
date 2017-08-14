<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<spring:url value="/resources" var="resources" /><spring:url value="/resources/template" var="template"/><spring:url value="/" var="shooney"/><spring:url value="/board" var="board"/><spring:url value="/project" var="project"/><spring:url value="/portfolio" var="portfolio"/><spring:url value="/myinfo" var="myinfo"/><spring:url value="/admin" var="admin"/><spring:url value="/signup" var="signup"/><spring:url value="/login" var="login"/><spring:url value="/logout" var="logout"/>
<tag:admin-layout tab="${target}">
	<div class="bg-color-ocean-g">
		<div class="container">
			<div class="row">
				<div class="ocean-title col-sm-8">
					<p>Admin Notice Manager</p>
				</div>
				<sec:authorize access="hasRole('SUPERADMIN')">
					<div class="col-sm-12 text-right">
						<button class="btn-u btn-u-dark-blue" onclick="NoticeService.noticeInsertForm()">Add New Notice</button>
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
                            <td id="noticeId${notice.id}">${notice.id}</td>
							<td id="noticeUri${notice.id}">${notice.uri}</td>
							<td class="text-left" id="noticeContent${notice.id}">${notice.content}</td>
                            <td id="noticeState${notice.id}">${notice.delFlag}</td>
                            <td>
                                <button class="btn btn-default custom-width rounded" onclick="NoticeService.noticeUpdateForm(${notice.id})">Edit</button>
                            </td>
                            <c:choose>
                                <c:when test="${!notice.delFlag}">
                                    <td>
                                        <button class="btn-u btn-u-red rounded" onclick="NoticeService.noticeDelete(${notice.id})">Delete
                                        </button>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td>
                                        <button class="btn-u btn-u-dark-blue rounded" onclick="NoticeService.noticeDelete(${notice.id})">Active
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
<script type="text/javascript" src="${resources}/js/notice/notice-module.js"></script>
