<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<spring:url value="/" var="shooney"/><spring:url value="/resources" var="resources" /><spring:url value="/resources/template" var="template"/><spring:url value="/webjars" var="webjars" /><spring:url value="/board" var="board"/><spring:url value="/project" var="project"/><spring:url value="/portfolio" var="portfolio"/><spring:url value="/myinfo" var="myinfo"/><spring:url value="/admin" var="admin"/><spring:url value="/signup" var="signup"/><spring:url value="/login" var="login"/><spring:url value="/logout" var="logout"/><spring:url value="/news" var="news"/><spring:url value="/admin" var="admin" />
<tag:layout>
    <div class="bg-color-ocean-g">
        <div class="container">
            <div class="row">
                <div class="ocean-title col-sm-8 col-xs-12">
                    Welcome to HunSeol Home.

                </div>
            </div>
        </div>
    </div>

    <%-- Notice div Start --%>
    <div class="container">
        <div class="row margin-xs">
            <c:forEach items="${notices}" var="notice">
                <div class="col-sm-12 margin-top-10 margin-bottom-10">
                        ${notice.content}
                </div>
            </c:forEach>
        </div>
    </div>
    <%-- Notice div End --%>

    <hr>

    <%-- Search div Start --%>
    <div class="container">
        <div class="row margin-xs">
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
    <%-- Search div End --%>

    <hr>

    <%-- Count user div Start --%>
    <div class="container">
        <div class="row margin-xs">
            <div class="col-sm-12">
                <p class="font-26b text-center" data-lang="commons:index.count.title"></p>
                <p class="text-center" data-lang="commons:index.count.c1"></p>
            </div>
        </div>

        <div class="row margin-bottom-10">
            <div class="col-lg-4 col-sm-4 col-xs-4">
                <div class="ocean-grid">
                    <h4>Yesterday</h4>
                    <span class="counter">${historys.yesterday}</span>
                </div>
            </div>
            <div class="col-lg-4 col-sm-4 col-xs-4">
                <div class="ocean-grid">
                    <h4>Today</h4>
                    <span class="counter">${historys.today }</span>
                </div>
            </div>
            <div class="col-lg-4 col-sm-4 col-xs-4">
                <div class="ocean-grid">
                    <h4>Total</h4>
                    <span class="counter">${historys.total }</span>
                </div>
            </div>
        </div>
    </div>
    <%-- Count user div End --%>
</tag:layout>
<script type="text/javascript" src="${resources}/js/index.js"></script>
