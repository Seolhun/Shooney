<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:url value="/bo" var="bo"/>
<tag:layout tab="${target}">
<div class="call-action-v1 bg-color-light">
	<div class="container">
		<div class="call-action-v1-box">
			<div class="call-action-v1-in">
				<p>Unify creative technology company providing key digital services and focused on helping our clients to build a successful business on web and mobile.</p>
			</div>
			<sec:authorize access="hasRole('SUPERADMIN')">
				<div class="call-action-v1-in inner-btn page-scroll">
			 		<a href="add" class="btn-u btn-brd btn-brd-hover btn-u-dark btn-u-block margin-bottom-5">Write</a>
		 		</div>
		 	</sec:authorize>
		</div>
	</div>
</div>
<div class="container content-xs">
	<div class="row">
		<div class="col-sm-12">
			<a href="${bo }/${kind}/list" class="btn-u btn-brd btn-brd-hover btn-u-dark btn-u-block margin-bottom-5">All</a>
			<c:forEach items="${pfNames }" var="i">
				<a href="list?pf=${i.type}" class="btn-u btn-brd btn-brd-hover btn-u-dark btn-u-block margin-bottom-5">${i}</a>
			</c:forEach>
		</div>
		<hr>
		<div class="col-sm-12">
			<table class="table table-hover">
	   		<thead>
	      		<tr>
			        <th style="width: 10%; text-align: center;">Id</th>
			        <th style="width: 40%; text-align: center;">Title</th>
			        <th style="width: 15%; text-align: center;">Writer</th>
			        <th style="width: 15%; text-align: center;">LatestDate</th>
			        <th style="width: 5%; text-align: center;">Hits</th>
			        <th style="width: 5%; text-align: center;">Likes</th>
				</tr>
	    	</thead>
	   		<tbody>
				<c:forEach items="${boards}" var="i">
					<tr>
						<td style="width: 10%; text-align: center;">${i.id}</td>
						<td style="width: 40%"><a href="${bo}/${kind}/r${i.id}">[ ${i.pfName} ] ${i.title}<c:if test="${i.depth>0}">&nbsp;&nbsp;<i class="fa fa-comments">&nbsp;${i.depth}</i></c:if></a></td>
						<td style="width: 15%; text-align: center;">${i.writer}</td>
						<td style="width: 15%; text-align: center;"><fmt:formatDate value="${i.latestDate}" pattern="yy-MM-dd, HH:mm"/></td>
						<td style="width: 5%; text-align: center;">${i.hits}</td>
						<td style="width: 5%; text-align: center;">${i.likes}</td>
					</tr>
				</c:forEach>
	   		</tbody>
	   		</table>
   			<div style="text-align: center">
   				<div class="text-center row">
   					<div class="col-sm-12">
						<form:form method="GET" acceptCharset="UTF-8">
							<select name="sDate">
								<option value="0" <c:if test="${paging.sDate==0}">selected='selected'</c:if>>All Day</option>
								<option value="1" <c:if test="${paging.sDate==1}">selected='selected'</c:if>>One Day</option>
								<option value="7" <c:if test="${paging.sDate==7}">selected='selected'</c:if>>One Week</option>
								<option value="30" <c:if test="${paging.sDate==30}">selected='selected'</c:if>>One Month</option>
								<option value="180" <c:if test="${paging.sDate==180}">selected='selected'</c:if>>Six Months</option>
								<option value="365" <c:if test="${paging.sDate==365}">selected='selected'</c:if>>One Year</option>
							</select>
							<select name="sType">
								<option value="0" <c:if test="${paging.sType==0}">selected='selected'</c:if>>Subject</option>
								<option value="1" <c:if test="${paging.sType==1}">selected='selected'</c:if>>Writer</option>
								<option value="2" <c:if test="${paging.sType==2}">selected='selected'</c:if>>Subject &amp; Reply</option>
							</select>
							<input type="text" name="sText" class="form" placeholder="Searching" style="width: 30%;" value="${paging.sText}">
			       			<button type="submit" class="btn btn-default">Search</button>
						</form:form>
					</div>
				</div>
   			</div>
   			<hr>
	   		<div style="text-align: center">
	   			<a href="list?cp=${paging.cPage -10 < 1 ? 1 : paging.cPage -10}" class="btn-u btn-brd btn-brd-hover btn-u-dark btn-u-block margin-bottom-5">&larr;&larr;</a>
	   			<a href="list?cp=${paging.cPage -1 < 1 ? 1 : paging.cPage -1}" class="btn-u btn-brd btn-brd-hover btn-u-dark btn-u-block margin-bottom-5">&larr;</a>
	   			&nbsp;
	   			<c:forEach begin="${paging.blockStartNo }" end="${paging.blockEndNo}" varStatus="status">
					<a href="list?cp=${status.index }" class="btn-u btn-brd btn-brd-hover btn-u-dark btn-u-block margin-bottom-5" <c:if test="${status.index==paging.cPage }">style="color : #4765a0"</c:if>>${status.index}</a>
	   			</c:forEach>
	   			&nbsp;
	   			<a href="list?cp=${paging.cPage +1 > paging.totalPage ? paging.totalPage : paging.cPage +1}" class="btn-u btn-brd btn-brd-hover btn-u-dark btn-u-block margin-bottom-5">&rarr;</a>
	   			<a href="list?cp=${paging.cPage +10 > paging.totalPage ? paging.totalPage : paging.cPage +10 }" class="btn-u btn-brd btn-brd-hover btn-u-dark btn-u-block margin-bottom-5">&rarr;&rarr;</a>
	   		</div>
	   		<div style="text-align: center">
	   			${paging.cPage } / ${paging.totalPage}
	   		</div>
		</div>
	</div> 
</div>
</tag:layout>
