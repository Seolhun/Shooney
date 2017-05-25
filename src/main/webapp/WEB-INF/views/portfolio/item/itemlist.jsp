<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<spring:url value="/resources" var="resources" />
<spring:url value="/resources/template" var="template"/>
<spring:url value="/music" var="music"/>
<tag:layout tab="${target}">
<div class="call-action-v1 bg-color-light">
	<div class="container">
		<div class="call-action-v1-box">
				<div class="call-action-v1-in">
					<p>These datas is Json parsed by Java FROM the MariaDB</p>
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
		  	<table class="table table-hover">
	    		<thead>
		      		<tr>
				        <th>Id</th>
				        <th>Name En</th>
				        <th>levelMax</th>
				        <th>Boss</th>
				        <th>Middle Boss</th>
				        <th>Maps</th>
				        <sec:authorize access="hasRole('SUPERADMIM')">
				        	<th width="100"></th>
				        </sec:authorize>
				        <sec:authorize access="hasRole('SUPERADMIM')">
				        	<th width="100"></th>
				        </sec:authorize>
					</tr>
		    	</thead>
	    		<tbody>
				<c:forEach items="${items}" var="item">
					<tr>
						<td>${item.id}</td>
						<td>${item.name_en}</td>
						<td>${item.levelMax}</td>
						<td>
							${item.itemData.boss.clearB} / ${item.itemData.boss.clearA} / ${item.itemData.boss.clearC}
							<c:forEach items="${item.itemData.boss.treasure}" var="i">
								<c:forEach items="${i.item}" var="j">
									${j} 
								</c:forEach>
							</c:forEach>								
						
						</td>
						<td>
							<c:forEach items="${item.itemData.middleBosses}" var="i">
								<c:forEach items="${i.subBNpcNames}" var="j">
										${j}
								</c:forEach>
							</c:forEach>
						</td>
						<td>
							<c:if test="${item.itemData.maps.size() != 0}">	/
								<c:forEach items="${item.itemData.maps}" var="i">
									<c:forEach items="${i.item}" var="j">
											${j} /
									</c:forEach>
								</c:forEach>
							</c:if>
						</td>
					    <sec:authorize access="hasRole('SUPERADMIM')">
							<td><a href="<c:url value='${item }/edit-${item.id}' />" class="btn btn-success custom-width">edit</a></td>
				        </sec:authorize>
				        <sec:authorize access="hasRole('SUPERADMIM')">
							<td><a href="<c:url value='${item }/delete-${item.id}' />" class="btn btn-danger custom-width">delete</a></td>
        				</sec:authorize>
					</tr>
				</c:forEach>
	    		</tbody>
	    	</table>
		</div>
   	</div>
</div>
</tag:layout>
