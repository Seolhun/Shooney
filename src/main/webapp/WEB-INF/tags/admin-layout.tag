<%@ tag description="Layout Template" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ attribute name="tab" required="false" type="java.lang.String"%>
<spring:url value="/" var="shooney"/>
<spring:url value="/resources" var="resources" />
<spring:url value="/resources/template" var="template"/>
<spring:url value="/webjars" var="webjars" />
<spring:url value="/blog" var="blog"/>
<spring:url value="/myinfo" var="myinfo"/>
<spring:url value="/admin" var="admin"/>
<spring:url value="/signup" var="signup"/>
<spring:url value="/login" var="login"/>
<spring:url value="/logout" var="logout"/>
<spring:url value="/news" var="news"/>
<spring:url value="/admin" var="admin" />
<spring:url value="/menu" var="menu"/>
<!doctype html>
<html lang="ko">
<head>
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

	<%-- Vue JS--%>
	<script src="https://cdn.jsdelivr.net/vue.resource/0.9.3/vue-resource.min.js"></script>
	<script type="text/javascript" src="${resources }/vendor/vuejs/vuejs.js"></script>
	<%-- jQuery --%>
	<script type="text/javascript" src="${webjars}/jquery/2.2.4/jquery.min.js" ></script>
	<%-- BootStrap --%>
	<link rel="stylesheet" href="${webjars }/bootstrap/3.3.6/css/bootstrap.min.css">

	<!-- CSS Default -->
	<link rel="stylesheet" href="${resources}/css/app.css">
	<link rel="stylesheet" href="${resources}/css/header.css">

	<!-- Summernote CSS -->
	<link href="${resources }/vendor/summer/summernote.css" rel="stylesheet">
</head>
<body class="header-fixed header-fixed-space translation">
	<div class="wrapper">
		<div class="header-v6 header-classic-white header-sticky">
			<div id="abovenav">
				<div class="container">
					<div class="row" id="aboverow">
						<div class="col-sm-4">
							<span class="margin-right-10">
								<a class="link-txt head-a" href="javascript:void(0);" onclick="changeLang('ko_KR');">
									<img src="${resources}/img/lang_ko.png" class="language">
									<span>Korean</span>
								</a>
							</span> 
							<span>&nbsp;|&nbsp;</span>
							<span class="margin-right-10">
								<a class="link-txt head-a" href="javascript:void(0);" onclick="changeLang('en_US');">
									<img src="${resources}/img/lang_en.png" class="language">
									<span>English</span>
								</a>
							</span>
						</div>
						<div class="col-sm-8">
							<div class="text-right">
								<sec:authorize access="isAuthenticated()">
									<span class="margin-right-10"><b>ID : </b><sec:authentication property="principal.username"/></span>
									<span class="margin-right-20"><b>Role : </b><sec:authentication property="principal.authorities"/></span>
									<span class="margin-right-20"><b><a href="${logout }" class="head-a">LOGOUT</a></b></span>
									<sec:authorize access="hasRole('SUPERADMIN')">
										<span><b><a href="${admin}/user/list" class="head-a">ADMIN</a></b></span>
									</sec:authorize>
								</sec:authorize>
								<sec:authorize access="isAnonymous()">
									<b><a href="${login }" class="margin-right-10" class="head-a">LOGIN</a></b>
									<b><a href="${signup }" class="head-a">SIGN UP</a></b>
								</sec:authorize>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- Navbar -->
			<div class="navbar navbar-color" role="navigation">
				<div class="container">
					<!-- Brand and toggle get grouped for better mobile display -->
					<div class="menu-container">
						<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-responsive-collapse">
							<span class="sr-only">Toggle navigation</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>

						<!-- Navbar Brand -->
						<div class="navbar-brand">
							<a class="font-16" href="${shooney}">Home</a>
						</div>
						<!-- ENd Navbar Brand -->
					</div>

					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse navbar-responsive-collapse">
						<div class="menu-container">
							<ul class="nav navbar-nav">
								<sec:authorize access="hasRole('SUPERADMIN')">
									<c:forEach items="${menuList }" var="menu">
										<c:choose>
											<c:when test="${menu.menuUrl==null}">
												<li class="dropdown">
													<a href="javascript:void(0);" class="dropdown-toggle">${menu.menuName}</a>
													<ul class="dropdown-menu">
												 		<c:forEach items="${menu.submenuList}" var="submenu">
											 				<li><a href="${shooney}${submenu.menuUrl}" class="dropdown-toggle">${submenu.menuName}</a></li>
											 			</c:forEach>
						 							</ul>
						 						</li>
											</c:when>
											<c:otherwise>
												<li><a href="${shooney}${menu.menuUrl}" class="dropdown-toggle">${menu.menuName}</a></li>
											</c:otherwise>
										</c:choose>
							 		</c:forEach>
						 		</sec:authorize>	
							</ul>
						</div>
					</div><!--/navbar-collapse-->
				</div>
			</div>
			<!-- End Navbar -->
		</div>
		<!--=== End Header v6 ===-->
<!-- ---------------------------------------------------------------------------------------------------------------------------------  -->
		<!-- real Body input place  -->
		<div>
		
			<jsp:doBody/>
			
		</div>
<!-- ---------------------------------------------------------------------------------------------------------------------------------  -->
		<!--=== Footer v6 ===-->
		<div id="footer-v6" class="footer-v6">
			<div class="footer">
				<div class="container">
					<div class="row">
						<!-- About Us -->
						<div class="col-md-4">
							<div class="heading-footer">
								<h2>About Hi-Cord</h2>
							</div>
							<p data-lang="commons:footer.s1">
								
							</p>
						</div>
						<!-- End About Us -->

						<!-- Contacts -->
						<div class="col-md-8">
							<div class="heading-footer"><h2>Contacts</h2></div>
							<div class="col-sm-4">
								<ul class="list-unstyled contacts">
									<li>
										<i class="radius-3x fa fa-map-marker"></i>
										Korea<br>
										Yeonsugoo, Incheon 
									</li>
								</ul>
							</div>
							<div class="col-sm-4">
								<ul class="list-unstyled contacts">
									<li>
										<i class="radius-3x fa fa-phone"></i>
										(+82)11 2902 4829<br>
										(+82)11 2902 4829
									</li>
								</ul>
							</div>
							<div class="col-sm-4">
								<ul class="list-unstyled contacts">
									<li>
										<i class="radius-3x fa fa-globe"></i>
										<a href="mailto:shun10114@gmail.com">shun10114@gmail.com</a><br>
										<a href="https://www.hi-cord.com">www.hi-cord.com</a>
									</li>
								</ul>
							</div>
						</div>
						<!-- End Contacts -->
					</div>
				</div><!--/container -->
			</div>

			<div class="copyright">
				<div class="container">
					<div class="row">
						<div class="col-md-8 sm-margon-bottom-10">
							<ul class="list-inline terms-menu">
								<li class="silver">Copyright © 2016 - All Rights Reserved</li>
								<li><a href="#">Terms of Use</a></li>
								<li><a href="#">Privacy and Policy</a></li>
								<li><a href="#">License</a></li>
								<li><a href="#">Support</a></li>
							</ul>
						</div>
						<div class="col-md-4 ">
							<ul class="list-inline dark-social pull-right space-bottom-0">
								<li>
									<a data-placement="top" data-toggle="tooltip" class="tooltips" data-original-title="Facebook" href="https://facebook.com" target="_blank">
										<i class="fa fa-facebook"></i>
									</a>
								</li>
								<li>
									<a data-placement="top" data-toggle="tooltip" class="tooltips" data-original-title="Twitter" href="https://twitter.com" target="_blank">
										<i class="fa fa-twitter"></i>
									</a>
								</li>
								<li>
									<a data-placement="top" data-toggle="tooltip" class="tooltips" data-original-title="Vine" href="https://vine.co/" target="_blank">
										<i class="fa fa-vine"></i>
									</a>
								</li>
								<li>
									<a data-placement="top" data-toggle="tooltip" class="tooltips" data-original-title="Google plus" href="https://plus.google.com/" target="_blank">
										<i class="fa fa-google-plus"></i>
									</a>
								</li>
								<li>
									<a data-placement="top" data-toggle="tooltip" class="tooltips" data-original-title="Pinterest" href="https://www.pinterest.com/" target="_blank">
										<i class="fa fa-pinterest"></i>
									</a>
								</li>
								<li>
									<a data-placement="top" data-toggle="tooltip" class="tooltips" data-original-title="Instagram" href="https://www.instagram.com/" target="_blank">
										<i class="fa fa-instagram"></i>
									</a>
								</li>
								<li>
									<a data-placement="top" data-toggle="tooltip" class="tooltips" data-original-title="Tumblr" href="https://www.tumblr.com/" target="_blank">
										<i class="fa fa-tumblr"></i>
									</a>
								</li>
								<li>
									<a data-placement="top" data-toggle="tooltip" class="tooltips" data-original-title="Youtube" href="https://www.youtube.com/" target="_blank">
										<i class="fa fa-youtube"></i>
									</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
			<!--=== End Footer v6 ===-->
	</div><!--/wrapper-->

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