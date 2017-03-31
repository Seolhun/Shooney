<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<spring:url value="/blog" var="blo"/><spring:url value="/file" var="file"/><spring:url value="/resources" var="resources" /><spring:url value="/resources/template" var="template"/>
<tag:layout>
<div class="call-action-v1 bg-color-light">
	<div class="container">
		<div class="call-action-v1-box">
			<c:choose>
				<c:when test="${edit }">
					<h2>Modify blog</h2>			
				</c:when>
				<c:otherwise>
					<h2>Create new blog</h2>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>
<div class="container margin-bottom-20">
	<div class="row content">
		<div class="col-sm-12">
			<form:form action="${blo }/insert?${_csrf.parameterName}=${_csrf.token}" class="form-horizontal" commandName="blog" modelAttribute="blog" enctype="multipart/form-data" acceptCharset="UTF-8">
				<div class="col-sm-4 col-xs-12">
					<div class="input-group margin-bottom-20">
						<span class="input-group-addon rounded-left"><i class="icon-user color-green"></i></span>
						<form:select path="entityName" class="form-control rounded-right" items="${enNames}"/>
						
						<span class="input-group-addon rounded-left"><i class="icon-user color-green"></i></span>
						<form:select path="portfolioType" class="form-control rounded-right" items="${pfNames }"/>
					</div>
					<div>
						<div class="error">
							<form:errors path="entityName" class="help-inline"/>
						</div>
						<div class="error">
							<form:errors path="portfolioType" class="help-inline"/>
						</div>
					</div>
				</div>
					
				<div class="col-sm-8 col-xs-12">
					<div class="input-group margin-bottom-20">
						<span class="input-group-addon rounded-left"><i class="icon-envelope color-green"></i></span>
						<form:input type="text" path="title" class="form-control rounded-right" placeholder="Board title"/>
					</div>
					<div>
						<div class="error">
							<form:errors path="title" class="help-inline"/>
						</div>
					</div>
				</div>
				
				<div class="col-sm-12 col-xs-12">
					<div>Content<br>
						<div class="error">
							<form:errors path="content" class="help-inline"/>
						</div>
					</div>
					<div class="input-group margin-bottom-20">
						<span class="input-group-addon rounded-left"><i class="icon-envelope color-green"></i></span>
					</div>
					<form:textarea type="text" path="content" class="form-control rounded-right" placeholder="Board content" id="summernote"/>
					
					<div>File Upload<br>
						<div class="error">
							<form:errors path="files" class="help-inline"/>
						</div>
					</div>
					<div class="input-group margin-bottom-20">
						<span class="input-group-addon rounded-left"><i class="icon-picture color-green"></i></span>
						<form:input type="file" path="files" id="files" class="form-control rounded-right" multiple="multiple" />
					</div>
				</div>
				
				<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
					<form:button type="submit" class="btn-u btn-u-dark-blue btn-block rounded">
					<c:choose>
						<c:when test="${edit }">
							Edit
						</c:when>
						<c:otherwise>
							Submit
						</c:otherwise>
					</c:choose>	
					</form:button>
				</div>
				<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6" >
					<button type="button" class="btn-u btn-u-orange btn-block rounded" onclick="javascript:history.back();">Cancel</button>
				</div>
			</form:form>
		</div>
	</div>
 </div>
</tag:layout>

<!-- Custom & Functional JS -->
<script type="text/javascript" src="${resources}/js/blog.js"></script>