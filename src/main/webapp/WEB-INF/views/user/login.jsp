<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<spring:url value="/resources" var="resources" /><spring:url value="/resources/template" var="template"/><spring:url value="/" var="shooney"/><spring:url value="/bo" var="bo"/><spring:url value="/po" var="po"/><spring:url value="/myinfo" var="myinfo"/><spring:url value="/admin" var="admin"/><spring:url value="/signup" var="signup"/><spring:url value="/login" var="login"/><spring:url value="/logout" var="logout"/>
<html lang="ko"> 
<head>
	<title>SomeThing New | Shooney Blog</title>
	<meta charset="utf-8">
	<meta http-equiv="Cache-Control" content="no-cache"/>
	<meta http-equiv="Expires" content="0"/>
	<meta http-equiv="Pragma" content="no-cache"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="keywords" content="Shooney, Blog, News, Music">
	<meta name="description" content="Shooney's Blog">
	<meta name="author" content="Shooney">
	<meta id="csrfToken" name="csrfToken" content="${_csrf.token}"/>
	<meta id="csrfHeader" name="csrfHeader" content="${_csrf.headerName}"/>

	<title>SomeThing New | Shooney Blog</title>

	<link rel="shortcut icon" href="${resources}/img/logo.jpeg" />

	<!-- Web Fonts -->
	<link rel='stylesheet' type='text/css' href='//fonts.googleapis.com/css?family=Open+Sans:400,300,600&amp;subset=cyrillic,latin'>

	<!-- CSS Global Compulsory -->
	<script type="text/javascript" src="${resources }/vendor/vuejs/vuejs.js"></script>
	<script type="text/javascript" src="${webjars}/jquery/2.2.4/jquery.min.js" ></script>
	<link rel="stylesheet" href="${webjars }/bootstrap/3.3.6/css/bootstrap.min.css">

	<!-- CSS Default -->
	<link rel="stylesheet" href="${resources}/css/app.css">
	<link rel="stylesheet" href="${resources}/css/header.css">

	<!-- Summernote CSS -->
	<link href="${resources }/vendor/summer/summernote.css" rel="stylesheet">
</head>
<body>
	<!--=== Content Part ===-->
	<div class="container content-xs">
		<div class="row equal-height-columns">
			<div class="col-md-12 col-sm-12 form-block equal-height-column">
				<a href="${shooney}">Home</a>
				<h2 class="margin-bottom-30">Login To Your Account</h2>
				<form:form action="${login }">
					<c:if test="${param.error != null}">
						<div class="alert alert-danger">
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
	<script type="text/javascript" src="${webjars}/angular/1.6.3/angular.min.js"></script>
	<script type="text/javascript" src="${webjars}/angular-sanitize/1.6.3/angular-sanitize.min.js"></script>

	<!-- Boostrap Library JS  -->
	<script type="text/javascript" src="${webjars}/bootstrap/3.3.6/js/bootstrap.min.js" ></script>
	<script type="text/javascript" src="${webjars}/bootstrap/3.3.6/js/tooltip.js"></script>

	<!-- Smart Editor JS -->
	<script type="text/javascript" src="${resources }/vendor/summer/summernote.js"></script>

	<!-- Common Function JS -->
	<!-- Language JS -->
	<script type="text/javascript" src="${resources}/vendor/i18next/i18next.min.js" ></script>
	<script type="text/javascript" src="${resources }/js/common/common-lang.js"></script>
	<script type="text/javascript" src="${resources }/js/common/common-function.js"></script>
</body>
</html>
