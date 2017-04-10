<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<spring:url value="/" var="shooney"/><spring:url value="/resources" var="resources" /><spring:url value="/resources/template" var="template"/><spring:url value="/webjars" var="webjars" /><spring:url value="/board" var="board"/><spring:url value="/project" var="project"/><spring:url value="/portfolio" var="portfolio"/><spring:url value="/myinfo" var="myinfo"/><spring:url value="/admin" var="admin"/><spring:url value="/signup" var="signup"/><spring:url value="/login" var="login"/><spring:url value="/logout" var="logout"/><spring:url value="/news" var="news"/><spring:url value="/admin" var="admin" />
<tag:layout>
		<!--=== Slider ===-->
		<div class="ms-layers-template">
			<!-- masterslider -->
			<div class="master-slider ms-skin-black-2 round-skin" id="masterslider" >
				<div class="ms-slide" style="z-index: 10">
					<img src="${template}/plugins/master-slider/masterslider/style/blank.gif" data-src="${template}/img/bg/11.jpg" alt="">
					<div class="ms-layer ms-promo-info color-light" style="left:25%; top:160px"
						data-effect="bottom(40)"
						data-duration="2000"
						data-delay="700"
						data-ease="easeOutExpo"
					>
						Welcome To
					</div>

					<div class="ms-layer ms-promo-info ms-promo-info-in color-light" style="left:25%; top:210px"
						data-effect="bottom(40)"
						data-duration="2000"
						data-delay="1000"
						data-ease="easeOutExpo"
					>
						<span class="color-green">Shooney's</span>Blog
					</div>

					<div class="ms-layer ms-promo-sub color-light" style="left:25%; top:310px"
						data-effect="bottom(40)"
						data-duration="2000"
						data-delay="1300"
						data-ease="easeOutExpo"
					><span data-lang="commons:index.s1"></span><br><span data-lang="commons:index.s2"></span></div>

					<a class="ms-layer btn-u" style="left:25%; top:390px" href="#"
						data-effect="bottom(40)"
						data-duration="2000"
						data-delay="1300"
						data-ease="easeOutExpo"
					>LEARN MORE</a>

					<a class="ms-layer btn-u btn-u-dark" style="left:150px; top:390px" href="${myinfo }/pro"
						data-effect="bottom(40)"
						data-duration="2000"
						data-delay="1300"
						data-ease="easeOutExpo"
					>ABOUT ME</a>
				</div>

				<div class="ms-slide" style="z-index: 13">
					<img src="${template}/plugins/master-slider/masterslider/style/blank.gif" data-src="${template}/img/bg/7.jpg" alt="">

					<img class="ms-layer" src="${template}/plugins/master-slider/masterslider/style/blank.gif" data-src="${template}/img/mockup/hand-black-iphone-l.png" alt=""
						style="bottom:-10px; left:25%; width:400px; height: auto;"
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
					<!-- <iframe src="http://player.vimeo.com/video/70528799" frameborder="0" webkitallowfullscreen mozallowfullscreen allowfullscreen> </iframe> -->
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
				<sec:authorize access="isAuthenticated()">
					<sec:authorize access="hasRole('SUPERADMIN')">
						<div class="call-action-v1-in inner-btn page-scroll">
							<a href="#portfolio" class="btn-u btn-brd btn-brd-hover btn-u-dark btn-u-block">REGISTER TODAY</a>
						</div>
					</sec:authorize>
				</sec:authorize>
			</div>
		</div>
	</div>
	<!--=== End Call To Action ===-->
	
	<div class="container content-xs">
		<div class="row margin-bottom-50">
			<div class="col-sm-12">
				<h4>인기 있는 프로젝트 나열하기. (5 ~ 10개) - 어드민에서 갯수 조절가능하게 하기.</h4>
			</div>
		</div>
		
		<div class="row">
			<div class="col-sm-12">
				<p class="font-26b">앞으로 업데이트 될 내용</p>
				<h4>일정 : 2017.04.29</h4>
				<h5>1. 접속 된 유저 아이피를 통한 카운트 기능 구현 에정 - interceptor</h5>
				<h5>2. 웹 크롤링 Image 주소를 통해 파일업로드해서 이미지 받기. - Crawl 로직추가하기.</h5>
				<h5>3. 웹 크롤링 데이터 확대 및 통계/분석 기능. => 30만 뉴스데이터 분석을 통한 핫 이슈 단어 랭크내기</h5>
				<br>
				<h4>일정 : 2017.05.28</h4>
					<h5>1. Spring WebSocket을 적용하여 알림 기능 구현 예정(알림 기능을 Spring Stomp + Sock.Js로 구현 할 예정)</h5>  
					<h5>2. Spring Social을 통해 자신이 올린 포트폴리오 등에 대한 정보를 공유할 수 있는 기능.</h5>
					<h5>3. Cache를 통한 속도 최적화(Redis) - 연결 완료.</h5>
			</div>
		</div>
	</div>

	<!--=== Carallax Counter v1 ===-->
	<div class="parallax-counter-v1 parallaxBg" style="background-position: 50% 20px;">
		<div class="container">
			<p class="font-26b text-center color-light" data-lang="commons:index.count.title"></p>
			<p class="space-xlg-hor text-center color-light" data-lang="commons:index.count.c1">
				
			</p>

			<div class="margin-bottom-40"></div>
			
			<div class="row margin-bottom-10">
				<div class="col-sm-3 col-xs-6">
					<div class="counters">
						<span class="counter">10629</span>
						<h4>Total</h4>
					</div>
				</div>
				<div class="col-sm-3 col-xs-6">
					<div class="counters">
						<span class="counter">78</span>
						<h4>Today</h4>
					</div>
				</div>
				<div class="col-sm-3 col-xs-6">
					<div class="counters">
						<span class="counter">78</span>
						<h4>Yesterday</h4>
					</div>
				</div>
				<div class="col-sm-3 col-xs-6">
					<div class="counters">
						<span class="counter">277</span>
						<h4>Projects</h4>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--=== End Carallax Counter v1 ===-->
</tag:layout>
<script type="text/javascript" src="${resources}/js/index.js"></script>