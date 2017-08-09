<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<spring:url value="/resources" var="resources" /><spring:url value="/resources/template" var="template"/><spring:url value="/" var="shooney"/>
<html lang="en"> 
<head>
	<title>SomeThing New | Shooney Blog</title>
	<!-- Meta -->
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
										<button type="submit" value="Update" class="btn-u btn-block rounded">Edit</button>
									</c:when>
									<c:otherwise>
										<button type="submit" value="Registration" class="btn-u btn-block rounded">Sign Up</button>
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
			2017 &copy; Shooney opened. <a href="https://github.com/Seolhun/">https://github.com/Seolhun/</a>
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

	<!-- Language JS -->
	<script type="text/javascript" src="${resources}/vendor/i18next/i18next.min.js" ></script>
	<script type="text/javascript" src="${resources }/js/common/common-lang.js"></script>
	<script type="text/javascript" src="${resources }/js/common/common-function.js"></script>
</body>
</html>
