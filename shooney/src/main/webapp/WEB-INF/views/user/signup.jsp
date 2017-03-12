<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<spring:url value="/resources" var="resources" />
<spring:url value="/resources/template" var="template"/>
<spring:url value="/" var="shooney"/>
<html lang="en"> 
<head>
	<title>SomeThing New | Hooney Blog</title>
	<!-- Meta -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="Shooney Blog Login">
	<!-- Favicon -->
	<link rel="shortcut icon" href="${resources}/img/logo.jpeg" />
	<!-- Web Fonts -->
	<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Open+Sans:400,300,700&amp;subset=cyrillic,latin">
	<!-- CSS Global Compulsory -->
	<link rel="stylesheet" href="${template}/plugins/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${template}/css/style.css">
	<!-- CSS Implementing Plugins -->
	<link rel="stylesheet" href="${template}/plugins/animate.css">
	<link rel="stylesheet" href="${template}/plugins/line-icons/line-icons.css">
	<link rel="stylesheet" href="${template}/plugins/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="${template}/plugins/brand-buttons/brand-buttons.css">
	<!-- CSS Page Style -->
	<link rel="stylesheet" href="${template}/css/pages/page_log_reg_v4.css">
	<!-- CSS Theme -->
	<link rel="stylesheet" href="${template}/css/theme-colors/dark-blue.css" id="style_color">
</head>
<body>
	<!--=== Content Part ===-->
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
							<div style="color: blue;">
								<form:errors path="email" cssClass="error" class="help-inline"/>
							</div>
						</div>
						<div class="input-group margin-bottom-20">
							<span class="input-group-addon rounded-left"><i class="icon-envelope color-green"></i></span>
							<c:choose>
								<c:when test="${edit}">
									<form:input path="email" type="email" class="form-control rounded-right" placeholder="Your email" disabled="true"/>
								</c:when>
								<c:otherwise>
									<form:input path="email" type="email" class="form-control rounded-right" placeholder="Your email"/>
								</c:otherwise>
							</c:choose>
						</div>
						
						<div>Nickname<br>
							<div style="color: blue;">
								<form:errors path="nickname" cssClass="error" class="help-inline"/>
							</div>
						</div>
						
						<div class="input-group margin-bottom-20">
							<span class="input-group-addon rounded-left"><i class="icon-user color-green"></i></span>
							<c:choose>
								<c:when test="${edit}">
									<form:input path="nickname" name="nickname" type="text" class="form-control rounded-right" placeholder="NickName" readonly="true"/>
								</c:when>
								<c:otherwise>
									<form:input path="nickname" name="nickname" type="text" class="form-control rounded-right" placeholder="NickName"/>
								</c:otherwise>
							</c:choose>
						</div>
	
						<c:if test="${edit }">
							<div>User Roles<br>
								<div style="color: blue;">
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
							<div style="color: blue;">
								<form:errors path="password" cssClass="error" class="help-inline"/>
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
										<button type="submit" value="Update" class="btn-u btn-block rounded">Edit</button>
									</c:when>
									<c:otherwise>
										<button type="submit" value="Registration" class="btn-u btn-block rounded">Sign Up</button>
									</c:otherwise>
								</c:choose>	
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6" >
								<button type="button" class="btn-u btn-u-default btn-block rounded" onclick="javascript:history.back()">Cancel</button>
							</div>
						</div>
						
						<div class="social-login text-center">
							<ul class="list-inline margin-bottom-20">
								<li>
									<button class="btn rounded btn-lg btn-facebook">
										<i class="fa fa-facebook"></i> Facebook Sign in
									</button>
								</li>
								<li>
									<button class="btn rounded btn-lg btn-twitter">
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
	<!--=== End Content Part ===-->

	<!--=== Sticky Footer ===-->
	<div class="container sticky-footer">
		<ul class="list-unstyled list-inline social-links margin-bottom-20">
			<li><a href="#"><i class="icon-custom icon rounded-x icon-bg-dark-blue fa fa-facebook"></i></a></li>
			<li><a href="#"><i class="icon-custom icon rounded-x icon-bg-dark-blue fa fa-twitter"></i></a></li>
			<li><a href="#"><i class="icon-custom icon rounded-x icon-bg-dark-blue fa fa-google-plus"></i></a></li>
		</ul>
		<p class="copyright-space">
			2016 &copy; All Rights Reserved. <a href="https://github.com/Seolhun/">https://github.com/Seolhun/</a>
		</p>
	</div>
	<!--=== End Sticky Footer ===-->

	<!-- JS Global Compulsory -->
	<script src="${template}/plugins/jquery/jquery.min.js"></script>
	<script src="${template}/plugins/jquery/jquery-migrate.min.js"></script>
	<script src="${template}/plugins/bootstrap/js/bootstrap.min.js"></script>

	<!-- JS Implementing Plugins -->
	<script src="${template}/plugins/back-to-top.js"></script>
	<script src="${template}/plugins/backstretch/jquery.backstretch.min.js"></script>
	
	<!-- JS Page Level -->
	<script src="${template}/js/app.js"></script>
	
	<!-- JS Customization -->
	<script src="${resources}/js/user.js"></script>
</body>
</html>
