<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<spring:url value="/resources" var="resources" /><spring:url value="/resources/template" var="template"/>
<tag:layout>
		<!--=== Slider ===-->
		<div class="ms-layers-template">
			<!-- masterslider -->
			<div class="master-slider ms-skin-black-2 round-skin" id="masterslider" >
				<div class="ms-slide" style="z-index: 10">
					<img src="${template}/plugins/master-slider/masterslider/style/blank.gif" data-src="${template}/img/bg/11.jpg" alt="">
					<div class="ms-layer ms-promo-info color-light" style="left:15px; top:160px"
						data-effect="bottom(40)"
						data-duration="2000"
						data-delay="700"
						data-ease="easeOutExpo"
					>Welcome To</div>

					<div class="ms-layer ms-promo-info ms-promo-info-in color-light" style="left:15px; top:210px"
						data-effect="bottom(40)"
						data-duration="2000"
						data-delay="1000"
						data-ease="easeOutExpo"
					><span class="color-green">Shooney's</span> Blog</div>

					<div class="ms-layer ms-promo-sub color-light" style="left:15px; top:310px"
						data-effect="bottom(40)"
						data-duration="2000"
						data-delay="1300"
						data-ease="easeOutExpo"
					>We are creative technology company providing <br> key digital services on web and mobile.</div>


					<a class="ms-layer btn-u" style="left:15px; top:390px" href="#"
						data-effect="bottom(40)"
						data-duration="2000"
						data-delay="1300"
						data-ease="easeOutExpo"
					>LEARN MORE</a>

					<a class="ms-layer btn-u btn-u-dark" style="left:150px; top:390px" href="#"
						data-effect="bottom(40)"
						data-duration="2000"
						data-delay="1300"
						data-ease="easeOutExpo"
					>ABOUT US</a>
				</div>

				<div class="ms-slide" style="z-index: 13">
					<img src="${template}/plugins/master-slider/masterslider/style/blank.gif" data-src="${template}/img/bg/7.jpg" alt="">

					<img class="ms-layer" src="${template}/plugins/master-slider/masterslider/style/blank.gif" data-src="${template}/img/mockup/hand-black-iphone-l.png" alt=""
					style="bottom:-10px; left:15px; width:400px; height: auto;"
					data-effect="bottom(100)"
					data-duration="2000"
					data-ease="easeOutExpo"
					data-type="image"
					/>

					<h3 class="ms-layer ms-promo-info-in color-light"  style="left:450px; top:170px;"
					data-effect="right(40)"
					data-duration="2300"
					data-delay="1300"
					data-ease="easeOutExpo"
					>MOST</h3>

					<h3 class="ms-layer ms-promo-info-in color-darker"  style="left:450px; top:230px"
					data-effect="left(40)"
					data-duration="2300"
					data-delay="1400"
					data-ease="easeOutBack"
					>INCREDIBLE</h3>

					<h3 class="ms-layer ms-promo-info-in color-darker"  style="left:450px; top:290px"
					data-effect="left(40)"
					data-duration="2300"
					data-delay="1400"
					data-ease="easeOutBack"
					>NEW <span class="color-light">FEATURES</span></h3>

					<a class="ms-layer btn-u" style="left:450px; top:370px" href="#"
					data-effect="bottom(40)"
					data-duration="2000"
					data-delay="1300"
					data-ease="easeOutExpo"
					>LEARN MORE</a>

					<a class="ms-layer btn-u btn-u-dark" style="left:580px; top:370px" href="#"
					data-effect="bottom(40)"
					data-duration="2000"
					data-delay="1300"
					data-ease="easeOutExpo"
					>DOWNLOAD NOW</a>
				</div>

				<div class="ms-slide" style="z-index: 12">
					<img src="${template}/plugins/master-slider/masterslider/style/blank.gif" data-src="${template}/img/bg/8.jpg" alt="">

					<div class="ms-layer video-box" style="bottom:125px; right:15px; width:650px; height:370px;"
					data-type="video"
					data-effect="rotate3dright(0,30,0,100,r)"
					data-duration="1500"
					data-ease="easeOutQuad"
					>
					<img class="ms-img-bordered" src="${template}/plugins/master-slider/masterslider/style/blank.gif" data-src="${template}/img/main/img20.jpg" alt="">
					<iframe src="http://player.vimeo.com/video/70528799" frameborder="0" webkitallowfullscreen mozallowfullscreen allowfullscreen> </iframe>
				</div>

				<h3 class="ms-layer ms-promo-info color-light"  style="left:10px; top:170px"
				data-effect="bottom(20)"
				data-duration="2300"
				data-delay="2300"
				data-ease="easeOutExpo"
				>AMAZING</h3>

				<h3 class="ms-layer ms-promo-info-in color-light"  style="left:10px; top:245px"
				data-effect="left(100)"
				data-duration="3300"
				data-delay="1900"
				data-ease="easeOutExpo"
				><span class="color-green">FEATURES</span></h3>

				<h3 class="ms-layer normal-title color-light"  style="left:10px; top:312px"
				data-effect="bottom(20)"
				data-duration="2300"
				data-delay="2000"
				data-ease="easeOutExpo"
				>UNIFY BOOTSTRAP TEMPLATE</h3>

				<p class="ms-layer ms-promo-sub ms-promo-sub-in color-light"  style="left:10px; top:360px"
				data-effect="right(40)"
				data-duration="2300"
				data-delay="2300"
				data-ease="easeOutExpo"
				>YOUTUBE, VIMEO AND CUSTOM <br> IFRAME SUPPORTED</p>
			</div>
		</div>
		<!-- end of masterslider -->
	</div>
	<!--=== End Slider ===-->

	<!--=== Call To Action ===-->
	<div class="call-action-v1 bg-color-light">
		<div class="container">
			<div class="call-action-v1-box">
				<div class="call-action-v1-in">
					<p>Unify creative technology company providing key digital services and focused on helping our clients to build a successful business on web and mobile.</p>
				</div>
				<div class="call-action-v1-in inner-btn page-scroll">
					<a href="#portfolio" class="btn-u btn-brd btn-brd-hover btn-u-dark btn-u-block">REGISTER TODAY</a>
				</div>
			</div>
		</div>
	</div>
	<!--=== End Call To Action ===-->

	<script type="text/ng-template" id="embedded.home.html">
    	<h1> Home </h1>
    	{{greeting}}
	</script>
	<script type="text/ng-template" id="embedded.about.html">
    	<h1> About </h1>
    	{{content}}
	</script>
	<div class="content"  data-ng-controller="HomeController">
		<div>
			<div id="navigation">
				<a href="#/home">Home</a>
				<a href="#/about">About</a>
			</div>
			
			<div ng-view></div>
		</div>
	</div>


	<!--=== Carallax Counter v1 ===-->
	<div class="parallax-counter-v1 parallaxBg" style="background-position: 50% 20px;">
		<div class="container">
			<h2 class="title-v2 title-light title-center">SOME FACTS AND SERVICES</h2>
			<p class="space-xlg-hor text-center color-light">If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary.</p>

			<div class="margin-bottom-40"></div>

			<div class="row margin-bottom-10">
				<div class="col-sm-3 col-xs-6">
					<div class="counters">
						<span class="counter">10629</span>
						<h4>Users</h4>
					</div>
				</div>
				<div class="col-sm-3 col-xs-6">
					<div class="counters">
						<span class="counter">277</span>
						<h4>Projects</h4>
					</div>
				</div>
				<div class="col-sm-3 col-xs-6">
					<div class="counters">
						<span class="counter">78</span>
						<h4>Team Members</h4>
					</div>
				</div>
				<div class="col-sm-3 col-xs-6">
					<div class="counters">
						<span class="counter">109</span>
						<h4>Awards</h4>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--=== End Carallax Counter v1 ===-->
</tag:layout>
<script type="text/javascript" src="${resources}/js/index.js"></script>