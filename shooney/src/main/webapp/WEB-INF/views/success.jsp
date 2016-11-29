<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<spring:url value="/resources/" var="resources" />
<spring:url value="/resources/template" var="template"/>
<spring:url value="/" var="shooney"/>
<!-- CSS Page Style -->
<link rel="stylesheet" href="${template}/css/pages/page_404_error4.css">
<tag:layout>
	<div class="call-action-v1 bg-color-light" style="background-image: url('${template}/img/error/error-v5.jpg'); height : 80%">
		<div class="container">
			<div class="call-action-v1-box">
				<div class="error-v5">
					<h1>That's an error!</h1>
					<span class="sorry">The requested URL was not found on this server. 
						<br> That's all we know. 
						<br><span><a href="${shooney}">Back home</a></span>
					</span> 
					<img class="error-img" src="${template}/img/error/error-404-v5.png" alt="error">
					<form>
						<div class="input-group col-md-12">
							<input type="text" class="form-control" placeholder="Try to find something"> 
							<span class="input-group-btn">
								<button class="btn-u" type="button">Search</button>
							</span>
						</div>
						<!-- /input-group -->
					</form>
				</div>
			</div>
		</div>
	</div>
</tag:layout>
