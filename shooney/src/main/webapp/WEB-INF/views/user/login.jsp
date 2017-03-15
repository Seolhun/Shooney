<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<spring:url value="/resources" var="resources" />
<spring:url value="/resources/template" var="template"/>
<spring:url value="/" var="shooney"/>
<spring:url value="/bo" var="bo"/>
<spring:url value="/po" var="po"/>
<spring:url value="/myinfo" var="myinfo"/>
<spring:url value="/admin" var="admin"/>
<spring:url value="/signup" var="signup"/>
<spring:url value="/login" var="login"/>
<spring:url value="/logout" var="logout"/>
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
	
	<!-- CSS Default -->
	<link rel="stylesheet" href="${resources}/css/app.css">
	<link rel="stylesheet" href="${resources}/css/header.css">
	
	<!-- CSS Theme -->
	<link rel="stylesheet" href="${template}/css/theme-colors/dark-blue.css" id="style_color">
</head>
<body>
	<!--=== Content Part ===-->
	<div class="container content-xs">
		<div class="row equal-height-columns">
			<div class="col-md-12 col-sm-12 form-block equal-height-column">
				<a href="${shooney}">
					<img src="${template}/img/themes/logo1-dark-blue.png" alt="">
				</a>
				<h2 class="margin-bottom-30">Login To Your Account</h2>
				<form:form action="${login }">
					<c:if test="${param.error != null}">
						<div class="alert alert-danger">as
							<!-- <p>Invalid username and password.</p> -->
							<p>${errorMsg }</p>
						</div>
					</c:if>
					<c:if test="${param.logout != null}">
						<div class="alert alert-success">
							<p>You have been logged out successfully.</p>
						</div>
					</c:if>
					<div class="reg-block">
						<div class="input-group margin-bottom-20">
							<span class="input-group-addon rounded-left"><i class="icon-user color-dark-blue"></i></span>
							<input type="text" class="form-control rounded-right" name="email" placeholder="Username">
						</div>
						<div class="input-group margin-bottom-20">
							<span class="input-group-addon rounded-left"><i class="icon-lock color-dark-blue"></i></span>
							<input type="password" class="form-control rounded-right" name="password" placeholder="Password">
						</div>

						<div class="checkbox">
							<ul class="list-inline">
								<li>
									<label>
										<input type="checkbox" name="remember-me"> Remember me
									</label>
								</li>

								<li class="pull-right">
									<a href="#">Forgot password?</a>
								</li>
							</ul>
						</div>

						<div class="row margin-bottom-70">
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
								<button type="submit" class="btn-u btn-block rounded">Login In</button>
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
								<a href="${signup }"><button type="button" class="btn-u btn-u-blue btn-block rounded">Sign Up</button></a>
							</div>
						</div>

						<div class="social-login text-center">
							<div class="or rounded-x">Or</div>
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
