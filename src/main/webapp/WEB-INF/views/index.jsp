<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<spring:url value="/" var="shooney"/><spring:url value="/resources" var="resources" /><spring:url value="/resources/template" var="template"/><spring:url value="/webjars" var="webjars" /><spring:url value="/board" var="board"/><spring:url value="/project" var="project"/><spring:url value="/portfolio" var="portfolio"/><spring:url value="/myinfo" var="myinfo"/><spring:url value="/admin" var="admin"/><spring:url value="/signup" var="signup"/><spring:url value="/login" var="login"/><spring:url value="/logout" var="logout"/><spring:url value="/news" var="news"/><spring:url value="/admin" var="admin" />
<tag:layout>
    <%-- Search DIV Start --%>
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <form id="search-div" v-cloak>
                    <div class="bar">
                        <!-- Create a binding between the searchString model and the text field -->
                        <input type="text" v-model="searchString" placeholder="Enter your search terms" />
                    </div>
                    <ul>
                        <!-- Render a li element for every entry in the computed filteredArticles array. -->

                        <li v-for="article in filteredArticles">
                            <a v-bind:href="article.url"><img v-bind:src="article.image" /></a>
                            <p>{{article.title}}</p>
                        </li>
                    </ul>
                </form>
            </div>
        </div>
    </div>
    <%-- Search DIV End --%>

    <!--=== Call To Action ===-->
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <div>Spring Boot, JPA(Hibernate), MariaDB, MongoDB, Redis, JQuery, AngularJS, Bootstrap, Maven, Nginx, Tomcat</div>
            </div>
        </div>
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
