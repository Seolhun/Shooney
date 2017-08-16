<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<spring:url value="/resources" var="resources" /><spring:url value="/resources/template" var="template"/><spring:url value="/" var="shooney"/><spring:url value="/board" var="board"/><spring:url value="/project" var="project"/><spring:url value="/portfolio" var="portfolio"/><spring:url value="/myinfo" var="myinfo"/><spring:url value="/admin" var="admin"/><spring:url value="/signup" var="signup"/><spring:url value="/login" var="login"/><spring:url value="/logout" var="logout"/>
<tag:admin-layout tab="${target}">
	<div class="bg-color-ocean-g">
		<div class="container">
			<div class="row">
				<div class="ocean-title col-sm-8">
                    <p>Admin Menu Manager</p>
                </div>
                <sec:authorize access="hasRole('SUPERADMIN')">
                    <div class="col-sm-4 text-right">
                        <button class="btn-u btn-u-dark-blue" onclick="MenuService.menuInsertForm()">Add New Menu</button>
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
					        <th class="text-center">Name.</th>
					        <th class="text-center">Type.</th>
							<th class="text-center">URL.</th>
					        <th class="text-center">Order.</th>
					        <th class="text-center">Depth</th>
							<th class="text-center">Parent Id</th>
							<th class="text-center">State</th>
					        <sec:authorize access="hasRole('SUPERADMIN')">
					        	<th class="text-center">Edit.</th>
					        </sec:authorize>
					        <sec:authorize access="hasRole('SUPERADMIN')">
					        	<th class="text-center">Delete.</th>
					        </sec:authorize>

						</tr>
			    	</thead>
		    		<tbody>
					<c:forEach items="${menus }" var="menu">
						<tr class="text-center">
                            <td id="menuId${menu.menuId}">${menu.menuId}</td>
							<td id="menuName${menu.menuId}">${menu.menuName}</td>
							<td id="menuType${menu.menuId}">${menu.menuType}</td>
							<td id="menuUrl${menu.menuId}">${menu.menuUrl}</td>
							<td id="menuOrder${menu.menuId}">${menu.menuOrder}</td>
							<td id="menuDepth${menu.menuId}">${menu.menuDepth}</td>
							<td id="menuParentId${menu.menuId}">${menu.menuParentId}</td>
							<td>${menu.delFlag}</td>
							<td><button class="btn btn-default custom-width rounded" onclick="MenuService.menuUpdateForm(${menu.menuId})">Edit</button></td>
							<c:choose>
								<c:when test="${menu.delFlag.equals('N')}">
                                    <td><button class="btn-u btn-u-red rounded" onclick="MenuService.menuDelete(${menu.menuId})">Delete</button></td>
								</c:when>
								<c:otherwise>
									<td><button class="btn-u btn-u-dark-blue rounded" onclick="MenuService.menuDelete(${menu.menuId})">Active</button></td>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
		    		</tbody>
		    	</table>
			</div>
	   	</div>
	</div>
    <%@ include file="/WEB-INF/views/admin/menu/modal/admin-menu-modal.jsp"%>
</tag:admin-layout>
<!-- Custom & Functional JS -->
<script type="text/javascript" src="${resources}/js/menu/menu-module.js"></script>
