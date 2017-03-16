<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<spring:url value="/board" var="bo"/>
<tag:layout tab="${target}">
<div class="call-action-v1 bg-color-light">
	<div class="container">
		<div class="call-action-v1-box">
			<div class="call-action-v1-in">
				<p>Unify creative technology company providing key digital services and focused on helping our clients to build a successful business on web and mobile.</p>
			</div>
			<sec:authorize access="hasRole('SUPERADMIN')">
				<div class="call-action-v1-in inner-btn page-scroll">
			 		<a href="insert" class="btn-u btn-brd btn-brd-hover btn-u-dark-blue btn-u-block margin-bottom-5">Write</a>
		 		</div>
		 	</sec:authorize>
		</div>
	</div>
</div>
<div class="container content-xs">
	<div class="row">
		<div class="col-sm-12">
			<a href="${bo }/list" class="btn-u btn-brd btn-brd-hover btn-u-dark-blue rounded">All</a>
			<c:forEach items="${pfNames }" var="i">
				<a href="list?pf=${i.type}" class="btn-u btn-brd btn-brd-hover btn-u-dark-blue rounded">${i}</a>
			</c:forEach>
		</div>
		<hr>
		<div class="col-sm-12">
   			<c:choose>
  				<c:when test="${fn:length(boards)<1}">
  					<div class="font-24 text-center">
  						${param.pf } 게시물이 없습니다.
  					</div>
  				</c:when>
  				<c:otherwise>
					<table class="table table-hover">
				   		<thead>
				      		<tr>
						        <th class="width-10 text-center">Id</th>
						        <th class="width-40 text-center">Title</th>
						        <th class="width-15 text-center">Writer</th>
						        <th class="width-15 text-center">LatestDate</th>
						        <th class="width-5 text-center">Hits</th>
						        <th class="width-5 text-center">Likes</th>
							</tr>
				    	</thead>
				   		<tbody>
							<c:forEach items="${boards}" var="i">
								<tr>
									<td class="width-10 text-center">${i.boardId}</td>
									<td class="width-60"><a href="${bo}/detail/${i.boardId}">[ ${i.portfolioType} ] ${i.title}<c:if test="${i.depth>0}">&nbsp;&nbsp;<i class="fa fa-comments">&nbsp;${i.depth}</i></c:if></a></td>
									<td class="width-10 text-center">${i.createdBy}</td>
									<td class="width-10 text-center"><fmt:formatDate value="${i.createdDate}" pattern="yy-MM-dd, HH:mm"/></td>
									<td class="width-5 text-center">${i.hits}</td>
									<td class="width-5 text-center">${i.likes}</td>
								</tr>
							</c:forEach>
				   		</tbody>
			   		</table>
		   			<div class="text-center">
		   				<div class="text-center row">
		   					<div class="col-sm-12">
								<form:form method="GET" acceptCharset="UTF-8">
									<select class="select" name="sDate">
										<option value="0" <c:if test="${paging.searchDate==0}">selected='selected'</c:if>>All Day</option>
										<option value="1" <c:if test="${paging.searchDate==1}">selected='selected'</c:if>>One Day</option>
										<option value="7" <c:if test="${paging.searchDate==7}">selected='selected'</c:if>>One Week</option>
										<option value="30" <c:if test="${paging.searchDate==30}">selected='selected'</c:if>>One Month</option>
										<option value="180" <c:if test="${paging.searchDate==180}">selected='selected'</c:if>>Six Months</option>
										<option value="365" <c:if test="${paging.searchDate==365}">selected='selected'</c:if>>One Year</option>
									</select>
									<select class="select" name="sType">
										<option value="0" <c:if test="${paging.searchType==0}">selected='selected'</c:if>>Subject</option>
										<option value="1" <c:if test="${paging.searchType==1}">selected='selected'</c:if>>Writer</option>
										<option value="2" <c:if test="${paging.searchType==2}">selected='selected'</c:if>>Subject &amp; Reply</option>
									</select>
									<input type="text" name="sText" class="input" placeholder="Searching" value="${paging.searchText}">
					       			<button type="submit" class="btn-u btn-u-block rounded">Search</button>
								</form:form>
							</div>
						</div>
		   			</div>
		   			<hr>
			   		<div class="text-center">
			   			<a href="list?cPage=${paging.currentPage -10 < 1 ? 1 : paging.currentPage -10}" class="btn-u btn-brd btn-brd-hover btn-u-dark-blue btn-u-block margin-bottom-5">&larr;&larr;</a>
			   			<a href="list?cPage=${paging.currentPage -1 < 1 ? 1 : paging.currentPage -1}" class="btn-u btn-brd btn-brd-hover btn-u-dark-blue btn-u-block margin-bottom-5">&larr;</a>
			   			<c:forEach begin="${paging.blockStartNo }" end="${paging.blockEndNo}" varStatus="status">
							<a href="${bo}/detail/list?cPage=${status.index }" class="btn-u btn-brd btn-brd-hover btn-u-dark-blue btn-u-block margin-bottom-5" 
							<c:if test="${status.index==paging.currentPage }">style="color : #4765a0"</c:if>>${status.index}</a>
			   			</c:forEach>
			   			<a href="list?cPage=${paging.currentPage +1 > paging.totalPage ? paging.totalPage : paging.currentPage +1}" class="btn-u btn-brd btn-brd-hover btn-u-dark-blue btn-u-block margin-bottom-5">&rarr;</a>
			   			<a href="list?cPage=${paging.currentPage +10 > paging.totalPage ? paging.totalPage : paging.currentPage +10 }" class="btn-u btn-brd btn-brd-hover btn-u-dark-blue btn-u-block margin-bottom-5">&rarr;&rarr;</a>
			   		</div>
			   		<div class="text-center">
			   			${paging.currentPage } / ${paging.totalPage}
			   		</div>
  				</c:otherwise>
  			</c:choose>
		</div>
	</div> 
</div>
</tag:layout>
