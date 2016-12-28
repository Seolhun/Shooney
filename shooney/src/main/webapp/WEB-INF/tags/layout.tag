<%@ tag description="Layout Template" pageEncoding="UTF-8"%>
<%@ attribute name="tab" required="false" type="java.lang.String"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%-- <c:choose><c:when test="${language_code eq 'en'}"><spring:eval expression="@text_en" var="text" scope="request" /></c:when><c:otherwise><spring:eval expression="@text_ko" var="text" scope="request" /></c:otherwise></c:choose> --%>
<spring:url value="/resources" var="resources" /><spring:url value="/resources/template" var="template"/><spring:url value="/" var="shooney"/><spring:url value="/board" var="board"/><spring:url value="/project" var="project"/><spring:url value="/portfolio" var="portfolio"/><spring:url value="/myinfo" var="myinfo"/><spring:url value="/admin" var="admin"/><spring:url value="/signup" var="signup"/><spring:url value="/login" var="login"/><spring:url value="/logout" var="logout"/>
<!doctype html>
<html class="no-js" lang="${language_code}">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="keywords" content="Shooney">
<meta name="description" content="Shooney's Blog">
<meta id="_csrf" name="_csrf" content="${_csrf.token}" />
<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}" />

<title>SomeThing New | Hooney Blog</title>

<link rel="shortcut icon" href="${resources}/img/logo.jpeg" />

<!-- Web Fonts -->
<link rel='stylesheet' type='text/css' href='//fonts.googleapis.com/css?family=Open+Sans:400,300,600&amp;subset=cyrillic,latin'>

<!-- CSS Global Compulsory -->
<link rel="stylesheet" href="${template}/plugins/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${template}/css/style.css">

<!-- CSS Header and Footer -->
<link rel="stylesheet" href="${template}/css/headers/header-v6.css">
<link rel="stylesheet" href="${template}/css/footers/footer-v6.css">

<!-- CSS Implementing Plugins -->
<link rel="stylesheet" href="${template}/plugins/animate.css">
<link rel="stylesheet" href="${template}/plugins/line-icons/line-icons.css">
<link rel="stylesheet" href="${template}/plugins/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="${template}/plugins/fancybox/source/jquery.fancybox.css">
<link rel="stylesheet" href="${template}/plugins/owl-carousel/owl-carousel/owl.carousel.css">
<link rel="stylesheet" href="${template}/plugins/master-slider/masterslider/style/masterslider.css">
<link rel='stylesheet' href="${template}/plugins/master-slider/masterslider/skins/black-2/style.css">

<!-- CSS Pages Style -->
<link rel="stylesheet" href="${template}/css/pages/page_one.css">

<!-- CSS Theme -->
<link rel="stylesheet" href="${template}/css/theme-colors/dark-blue.css" id="style_color">
<link rel="stylesheet" href="${template}/css/theme-skins/dark.css">

<!-- CSS Customization -->
<link href="${resources }/summer/summernote.css" rel="stylesheet">
<link rel="stylesheet" href="${resources}/css/hooney.css">
</head>
<body class="header-fixed header-fixed-space">
	<div class="wrapper">
		<div class="header-v6 header-classic-white header-sticky">
			<div id="abovenav">
				<div class="container">
					<div class="row" id="aboverow">
						<div class="col-sm-6 col-xs-6" style="text-align: left">
							<a href="#" class="link-txt" id="head-a"><img src="${resources}/img/lang_ko.png" class="language"> Korean&nbsp;</a>| 
							<a href="#" class="link-txt" id="head-a"><img src="${resources}/img/lang_en.png" class="language"> English</a>
						</div>
						<div class="col-sm-3 col-xs-3">	
							<sec:authorize access="isAuthenticated()">
								<sec:authentication property="principal.username"/>
							</sec:authorize>
						</div>
						<div class="col-sm-3 col-xs-3" style="text-align : right">	
							<sec:authorize access="isAuthenticated()">
								<a href="${logout }" id="head-a">LOGOUT</a>
							</sec:authorize>
							<sec:authorize access="isAnonymous()">
								<a href="${login }" id="head-a">LOGIN</a>
								&nbsp;&nbsp;|&nbsp;&nbsp;
							</sec:authorize>
							<!-- Demo Pages -->
							<sec:authorize access="isAnonymous()">
								<a href="${signup }" id="head-a">SIGN UP</a>
							</sec:authorize>
						</div>
					</div>
				</div>
			</div>
			<!-- Navbar -->
			<div class="navbar mega-menu" role="navigation">
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
							<a href="${shooney}" style="font-size: 16px;">
								<%-- <img class="shrink-logo" src="${resources}/img/logo.jpeg" alt="Logo"> --%>
							</a>
						</div>
						<!-- ENd Navbar Brand -->
						<!-- Header Inner Right -->
						<div class="header-inner-right">
							<ul class="menu-icons-list">
								<li class="menu-icons">
									<i class="menu-icons-style search search-close search-btn fa fa-search"></i>
									<div class="search-open">
										<input type="text" class="animated fadeIn form-control" placeholder="Start searching ...">
									</div>
								</li>
								<!-- 
								<li class="menu-icons shopping-cart">
									<i class="menu-icons-style radius-x fa fa-shopping-cart"></i>
									<span class="badge">0</span>
									<div class="shopping-cart-open">
										<span class="shc-title">No products in the Cart</span>
										<button type="button" class="btn-u"><i class="fa fa-shopping-cart"></i> Cart</button>
										<span class="shc-total">Total: <strong>$0.00</strong></span>
									</div>
								</li>
								 -->
							</ul>
							
						</div>
						<!-- End Header Inner Right -->
					</div>

					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse navbar-responsive-collapse">
						<div class="menu-container">
							<ul class="nav navbar-nav">
								<!-- Home -->
								<li><a href="${shooney}" class="dropdown-toggle">Home</a></li>
								<!-- End Home -->

								<!-- Pages -->
								<li class="dropdown">
									<a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">Board</a>
									<ul class="dropdown-menu">
										<li><a href="${board }/notice/list">Notice</a></li>
										<li><a href="${board }/freeboard/list">Free Board</a></li>
										<li><a href="${board }/qna/list">QNA</a></li>
									</ul>
								</li>
								<!-- End Pages -->
								
								<!-- Shortcodes -->
								<!-- <li class="dropdown mega-menu-fullwidth"> -->
								<li class="dropdown">
									<a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">Portfolio</a>
									<ul class="dropdown-menu">
										<li><a href="${project}"><i class="fa fa-columns"></i> Projects</a></li>
										<li><a href="${portfolio}/item/list"><i class="fa fa-magic"></i> Items</a></li>
										<li><a href="${portfolio}/music/list"><i class="fa fa-volume-down"></i> Musics(MongoDB, Json, Crawl, Youtube API)</a></li>
										
										<%--										
										<li><a href="${portfolio}/stomp/list"><i class="fa fa-comments"></i> Stomp(Spring AMQP)</a></li>
										<li><a href="shortcode_typo_grid.html"><i class="fa fa-align-justify"></i> Big Data(Spark)</a></li>
										<li><a href="shortcode_typo_tagline_boxes.html"><i class="fa fa-tasks"></i> Thread Game(Java)</a></li>
										<li><a href="${portfolio}/toon/list"><i class="fa fa-tasks"></i> Toon(Phantom JS)</a></li>
										<li>
											<div class="mega-menu-content disable-icons">
												<div class="container">
													<div class="row equal-height">
														<div class="col-md-3 equal-height-in">
															<ul class="list-unstyled equal-height-list">
																<li><h3>Function Portfolio</h3></li>
																<!-- Typography -->
																<li><a href="${admin}/list"><i class="fa fa-asterisk"></i>  Admin</a></li>
																<li><a href="${portfolio}/music/list"><i class="fa fa-volume-down"></i>  Musics</a></li>
																<li><a href="${portfolio}/item/list"><i class="fa fa-magic"></i>  Items(Json)</a></li>
																<li><a href="${portfolio}/stomp/list"><i class="fa fa-comments"></i>  Stomp</a></li>
																<li><a href="${portfolio}/toon/list"><i class="fa fa-columns"></i>  Toon(Phantom)</a></li>
																<li><a href="${portfolio}/it/list"><i class="fa fa-tasks"></i> Web Crawling</a></li>
																
																<li><a href="shortcode_typo_grid.html"><i class="fa fa-align-justify"></i> Grid Layouts</a></li>
																<li><a href="shortcode_compo_messages.html"><i class="fa fa-comment"></i> Alerts &amp; Messages</a></li>
																<li><a href="shortcode_compo_labels.html"><i class="fa fa-tags"></i> Labels &amp; Badges</a></li>
																<li><a href="shortcode_compo_pagination.html"><i class="fa fa-arrows-h"></i> Paginations</a></li>
															</ul>
														</div>
														<div class="col-md-3 equal-height-in">
															<ul class="list-unstyled equal-height-list">
																<li><h3>Buttons &amp; Icons</h3></li>
																<!-- Buttons -->
																<li><a href="shortcode_btn_general.html"><i class="fa fa-flask"></i> General Buttons</a></li>
																<li><a href="shortcode_btn_brands.html"><i class="fa fa-html5"></i> Brand &amp; Social Buttons</a></li>
																<li><a href="shortcode_btn_effects.html"><i class="fa fa-bolt"></i> Loading &amp; Hover Effects</a></li>
																<!-- End Buttons -->

																<!-- Icons -->
																<li><a href="shortcode_icon_general.html"><i class="fa fa-chevron-circle-right"></i> General Icons</a></li>
																<li><a href="shortcode_icon_fa.html"><i class="fa fa-chevron-circle-right"></i> Font Awesome Icons</a></li>
																<li><a href="shortcode_icon_line.html"><i class="fa fa-chevron-circle-right"></i> Line Icons</a></li>
																<li><a href="shortcode_icon_line_christmas.html"><i class="fa fa-chevron-circle-right"></i> Line Icons Pro</a></li>
																<li><a href="shortcode_icon_glyph.html"><i class="fa fa-chevron-circle-right"></i> Glyphicons Icons (Bootstrap)</a></li>
																<!-- End Icons -->
															</ul>
														</div>
														<div class="col-md-3 equal-height-in">
															<ul class="list-unstyled equal-height-list">
																<li><h3>Common elements</h3></li>

																<!-- Common Elements -->
																<li><a href="shortcode_thumbnails.html"><i class="fa fa-image"></i> Thumbnails</a></li>
																<li><a href="shortcode_accordion_and_tabs.html"><i class="fa fa-list-ol"></i> Accordion &amp; Tabs</a></li>
																<li><a href="shortcode_timeline1.html"><i class="fa fa-dot-circle-o"></i> Timeline Option 1</a></li>
																<li><a href="shortcode_timeline2.html"><i class="fa fa-dot-circle-o"></i> Timeline Option 2</a></li>
																<li><a href="shortcode_table_general.html"><i class="fa fa-table"></i> Tables</a></li>
																<li><a href="shortcode_compo_progress_bars.html"><i class="fa fa-align-left"></i> Progress Bars</a></li>
																<li><a href="shortcode_compo_panels.html"><i class="fa fa-quote-left"></i> Panels</a></li>
																<li><a href="shortcode_carousels.html"><i class="fa fa-sliders"></i> Carousel Examples</a></li>
																<li><a href="shortcode_maps_google.html"><i class="fa fa-map-marker"></i> Google Maps</a></li>
																<li><a href="shortcode_maps_vector.html"><i class="fa fa-align-center"></i> Vector Maps</a></li>
																<!-- End Common Elements -->
															</ul>
														</div>
														<div class="col-md-3 equal-height-in">
															<ul class="list-unstyled equal-height-list">
																<li><h3>Forms &amp; Infographics</h3></li>

																<!-- Forms -->
																<li><a href="shortcode_form_general.html"><i class="fa fa-bars"></i> Common Bootstrap Forms</a></li>
																<li><a href="shortcode_form_general1.html"><i class="fa fa-bars"></i> General Unify Forms</a></li>
																<li><a href="shortcode_form_advanced.html"><i class="fa fa-bars"></i> Advanced Forms</a></li>
																<li><a href="shortcode_form_layouts.html"><i class="fa fa-bars"></i> Form Layouts</a></li>
																<li><a href="shortcode_form_layouts_advanced.html"><i class="fa fa-bars"></i> Advanced Layout Forms</a></li>
																<li><a href="shortcode_form_states.html"><i class="fa fa-bars"></i> Form States</a></li>
																<li><a href="shortcode_form_sliders.html"><i class="fa fa-bars"></i> Form Sliders</a></li>
																<li><a href="shortcode_form_modals.html"><i class="fa fa-bars"></i> Modals</a></li>
																<!-- End Forms -->

																<!-- Infographics -->
																<li><a href="shortcode_compo_charts.html"><i class="fa fa-pie-chart"></i> Charts &amp; Countdowns</a></li>
																<!-- End Infographics -->
															</ul>
														</div>
													</div>
												</div>
											</div>
										</li>
										 --%>
									</ul>
								</li>
								<!-- End Shortcodes -->
								
								<!-- Blog -->
								<li class="dropdown">
									<a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">My Info</a>
									<ul class="dropdown-menu">
										<li><a href="${myinfo}/pro"><i class="fa fa-asterisk"></i> Profile</a></li>
										<li><a href="${myinfo}/ati"><i class="fa fa-asterisk"></i> Attitude</a></li>
										<li><a href="${myinfo}/goal"><i class="fa fa-asterisk"></i> Goal</a></li>
									</ul>
								</li>
								<sec:authorize access="hasRole('SUPERADMIN')">
									<li class="dropdown">
										<a href="${admin}/list">Admin</a>
									</li>
								</sec:authorize>	
								<!-- End Blog -->
							</ul>
						</div>
					</div><!--/navbar-collapse-->
				</div>
			</div>
			<!-- End Navbar -->
		</div>
		<!--=== End Header v6 ===-->
<!-- ---------------------------------------------------------------------------------------------------------------------------------  -->
		<div class="doBody" style="min-height: 500px; margin: 40px 0px 0px;" >
		<!-- real Body input place  -->
			<jsp:doBody/>
		</div>
<!-- ---------------------------------------------------------------------------------------------------------------------------------  -->
		<!--=== Footer v6 ===-->
		<div id="footer-v6" class="footer-v6">
			<div class="footer">
				<div class="container">
					<div class="row">
						<!-- About Us -->
						<div class="col-md-4 col-sm-4 col-xs-4">
							<div class="heading-footer"><h2>About Hi-Cord</h2></div>
							<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis blandit ut metus a commodo. Pellentesque congue tellus sed enim sollicitudin, id blandit mauris eleifend.</p>
						</div>
						<!-- End About Us -->

						<!-- Useful Links -->
						<div class="col-md-4 col-sm-4 col-xs-4">
							<div class="heading-footer"><h2>Useful Links</h2></div>
							<ul class="list-unstyled footer-link-list">
								<li><a href="${about }">About Me</a></li>
								<li><a href="${portfolio}/music/list">Portfolio</a></li>
								<li><a href="${board}/freeboard/list">Community</a></li>
								<li><a href="${contact}">Contact Us</a></li>
							</ul>
						</div>
						<!-- End Useful Links -->

						<!-- Contacts -->
						<div class="col-md-4 col-sm-4 col-xs-4">
							<div class="heading-footer"><h2>Contacts</h2></div>
							<ul class="list-unstyled contacts">
								<li>
									<i class="radius-3x fa fa-map-marker"></i>
									Korea<br>
									Yeonsugoo, Incheon 
								</li>
								<li>
									<i class="radius-3x fa fa-phone"></i>
									(+82)11 2902 4829<br>
									(+82)11 2902 4829
								</li>
								<li>
									<i class="radius-3x fa fa-globe"></i>
									<a href="#">shun10114@gmail.com</a><br>
									<a href="#">www.shooney.com</a>
								</li>
							</ul>
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
		<script type="text/javascript" src="${template}/plugins/jquery/jquery.min.js"></script>
		<script type="text/javascript" src="${template}/plugins/jquery/jquery-migrate.min.js"></script>
		<script type="text/javascript" src="${template}/plugins/bootstrap/js/bootstrap.min.js"></script>
		
		<!-- Custom & Functional JS -->
		<script type="text/javascript" src="${resources }/summer/summernote.js"></script>
		<script type="text/javascript" src="${resources}/js/custom.js"></script>
		
		<!-- JS Implementing Plugins -->
		<script type="text/javascript" src="${template}/plugins/counter/jquery.counterup.min.js"></script>
		
		<!-- For Slide Js -->
		<script type="text/javascript" src="${template}/plugins/master-slider/masterslider/jquery.easing.min.js"></script>
		<script type="text/javascript" src="${template}/plugins/master-slider/masterslider/masterslider.min.js"></script>
		<script type="text/javascript" src="${template}/js/plugins/master-slider-fw.js"></script>
		<script type="text/javascript" src="${template}/plugins/counter/waypoints.min.js"></script>
		<script type="text/javascript" src="${template}/js/plugins/fancy-box.js"></script>
		<script type="text/javascript" src="${template}/plugins/fancybox/source/jquery.fancybox.pack.js"></script>
		
		<!-- JS Page Level -->
		<script type="text/javascript" src="${template}/js/app.js"></script>
		<script type="text/javascript">
			jQuery(document).ready(function() {
				App.init();
				App.initCounter();
				FancyBox.initFancybox();
				MSfullWidth.initMSfullWidth();
			});
		</script>
</body>
</html>