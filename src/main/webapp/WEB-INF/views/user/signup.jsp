<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<spring:url value="/resources" var="resources" /><spring:url value="/resources/template" var="template"/><spring:url value="/" var="shooney"/><spring:url value="/bo" var="bo"/><spring:url value="/po" var="po"/><spring:url value="/myinfo" var="myinfo"/><spring:url value="/admin" var="admin"/><spring:url value="/signup" var="signup"/><spring:url value="/login" var="login"/><spring:url value="/logout" var="logout"/>
<tag:layout>
	<div class="container content-xs">
		<div class="row equal-height-columns">
			<div class="col-md-12 col-sm-12 form-block equal-height-column">
				<a href="${shooney}">
					<img src="${template}/img/themes/logo1-dark-blue.png">
				</a>
				<c:choose>
					<c:when test="${edit}">
						<h2 class="margin-bottom-30">Modify account</h2>
					</c:when>
					<c:otherwise>
						<h2 class="margin-bottom-30">Create new account</h2>
					</c:otherwise>
				</c:choose>
				
				<form:form method="POST" modelAttribute="user" class="form-horizontal" >
					<div class="reg-block">
						<div>Email<br>
							<div class="error-blue">
								<form:errors path="email" class="help-inline"/>
							</div>
						</div>
						<div class="input-group margin-bottom-20">
							<span class="input-group-addon rounded-left"><i class="icon-envelope color-green"></i></span>
							<c:choose>
								<c:when test="${edit}">
									<form:input path="id" value="${user.id }" type="hidden"/>
									<form:input path="email" value="${user.email }" type="email" class="form-control rounded-right" placeholder="Your email" disabled="true"/>
								</c:when>
								<c:otherwise>
									<form:input path="email" type="email" class="form-control rounded-right" placeholder="Your email"/>
								</c:otherwise>
							</c:choose>
						</div>
						
						<div>Nickname<br>
							<div class="error-blue">
								<form:errors path="nickname" class="help-inline"/>
							</div>
						</div>
						
						<div class="input-group margin-bottom-20">
							<span class="input-group-addon rounded-left"><i class="icon-user color-green"></i></span>
							<c:choose>
								<c:when test="${edit}">
									<form:input class="form-control rounded-right" path="nickname" value="${user.nickname }"  name="nickname" type="text" placeholder="NickName" readonly="true"/>
								</c:when>
								<c:otherwise>
									<form:input path="nickname" name="nickname" type="text" class="form-control rounded-right" placeholder="NickName"/>
								</c:otherwise>
							</c:choose>
						</div>
	
						<c:if test="${edit }">
							<div>User Roles<br>
								<div class="error-blue">
									<form:errors path="userProfiles" class="help-inline"/>
								</div>
							</div>
							<div class="input-group margin-bottom-30">
								<span class="input-group-addon rounded-left"><i class="icon-lock color-green"></i></span>
								<form:select path="userProfiles" items="${roles}" multiple="true" itemValue="id" itemLabel="type" class="form-control input-sm" />
							</div>
						</c:if>
						<c:if test="${edit }">
							<hr>
							<h2>Option</h2>
						</c:if>
						<div>Password<br>
							<div class="error-blue">
								<form:errors path="password" class="help-inline"/>
							</div>
						</div>
						
						<div class="input-group margin-bottom-30">
							<span class="input-group-addon rounded-left"><i class="icon-lock color-green"></i></span>
							<input type="password" name="password" class="form-control rounded-right" placeholder="Password"/>	
						</div>
						
						<hr>
						
						<div class="checkbox">
							<ul class="list-inline">
								<li class="pull-right">
									<span class="glyphicon glyphicon-search"></span><a href="#"> Forgot password?</a>
								</li>
							</ul>
						</div>
						<div class="row margin-bottom-70">
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
								<c:choose>
									<c:when test="${edit }">
										<button type="submit" value="Update" class="btn-u btn-u-ocean btn-block rounded">Edit</button>
									</c:when>
									<c:otherwise>
										<button type="submit" value="Registration" class="btn-u btn-u-ocean btn-block rounded">Sign Up</button>
									</c:otherwise>
								</c:choose>	
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6" >
								<a href="${shooney }"><button type="button" class="btn-u btn-u-default btn-block rounded">Cancel</button></a>
							</div>
						</div>
						
						<div class="social-login text-center">
							<ul class="list-inline margin-bottom-20">
								<li>
									<button class="rounded btn-u-lg btn-u btn-u-dark-blue">
										<i class="fa fa-facebook"></i> Facebook Sign in
									</button>
								</li>
								<li>
									<button class="rounded btn-u-lg btn-u btn-u-aqua">
										<i class="fa fa-twitter"></i> Twitter Sign in
									</button>
								</li>
							</ul>
							<p>Don't have an account? <a href="page_registration2.html">Create New</a></p>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div><!--/container-->
</tag:layout>
