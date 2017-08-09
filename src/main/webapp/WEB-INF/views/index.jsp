<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<spring:url value="/" var="shooney"/><spring:url value="/resources" var="resources" /><spring:url value="/resources/template" var="template"/><spring:url value="/webjars" var="webjars" /><spring:url value="/board" var="board"/><spring:url value="/project" var="project"/><spring:url value="/portfolio" var="portfolio"/><spring:url value="/myinfo" var="myinfo"/><spring:url value="/admin" var="admin"/><spring:url value="/signup" var="signup"/><spring:url value="/login" var="login"/><spring:url value="/logout" var="logout"/><spring:url value="/news" var="news"/><spring:url value="/admin" var="admin" />
<tag:layout>
	<!--=== Call To Action ===-->
    <div class="container">
        <p> Spring Boot, JPA(Hibernate), MariaDB, MongoDB, Redis, JQuery, AngularJS, Bootstrap, Maven, Nginx, Tomcat</p>
        <sec:authorize access="isAuthenticated()">
            <sec:authorize access="hasRole('SUPERADMIN')">
                <div class="call-action-v1-in inner-btn page-scroll">
                    <a href="#portfolio" class="btn-u btn-brd btn-brd-hover btn-u-dark btn-u-block">REGISTER TODAY</a>
                </div>
            </sec:authorize>
        </sec:authorize>
    </div>
	<!--=== End Call To Action ===-->

	<div class="container">
        <div class="row">
		    <c:forEach items="${notices}" var="notice">
                <div class="col-sm-12 margin-top-10 margin-bottom-10">
                    ${notice.content}
                </div>
    		</c:forEach>
        </div>
        <div id="app">
            {{ message }}
        </div>
	</div>

	<!--=== Carallax Counter v1 ===-->
	<div class="parallax-counter-v1 parallaxBg" style="background-position: 50% 20px;">
		<div class="container">
			<div class="row margin-bottom-30">
				<div class="col-sm-12">
					<p class="font-26b text-center color-light" data-lang="commons:index.count.title"></p>
					<p class="text-center color-light" data-lang="commons:index.count.c1"></p>
				</div>
			</div>

			<div class="row margin-bottom-10">
				<div class="col-sm-4 col-xs-4">
					<div class="counters">
						<span class="counter">${historys.yesterday}</span>
						<h4>Yesterday</h4>
					</div>
				</div>
				<div class="col-sm-4 col-xs-4">
					<div class="counters">
						<span class="counter">${historys.today }</span>
						<h4>Today</h4>
					</div>
				</div>
				<div class="col-sm-4 col-xs-4">
					<div class="counters">
						<span class="counter">${historys.total }</span>
						<h4>Total</h4>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--=== End Carallax Counter v1 ===-->
</tag:layout>
<script type="text/javascript" src="${resources}/js/index.js"></script>
