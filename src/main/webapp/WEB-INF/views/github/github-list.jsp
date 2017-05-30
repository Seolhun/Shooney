<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<spring:url value="/resources" var="resources" />
<spring:url value="/resources/template" var="template"/>
<tag:layout tab="${target}">
<!--=== Call To Action ===-->
<div class="call-action-v1 bg-color-light">
	<div class="container">
		<div class="call-action-v1-box">
			<div class="call-action-v1-in">
				<p>GitHub API와 D3를 통해 데이터시각화를 사용합니다</p>
			</div>
			<sec:authorize access="hasRole('SUPERADMIN')">
				<div class="call-action-v1-in inner-btn page-scroll">

				</div>
			</sec:authorize>
		</div>
	</div>
</div>
<!--=== End Call To Action ===-->
<div class="container content-xs">
	<div class="row">
		<div class="col-sm-12">
			<div class="row service-box-v1">
				<div class="col-sm-12">
					<div class="service-block service-block-default">
                        <select name="searchType">
                            <c:forEach items="${searchTypes}" var="st">
                                <option value="${st.type}">${st.name}</option>
                            </c:forEach>
                        </select>
                        <input type="text" name="topic" class="form-control2" id="searchParam" value="python">
                        <input type="button" class="btn-u btn-u-aqua2 rounded" onclick="GithubService.test()" value="TEST">
                        <input type="button" class="btn-u btn-u-aqua2 rounded" onclick="GithubService.githubSearch()" value="검색하기">

					</div>
                    <div id="searchResult">

                    </div>
				</div>
			</div>
		</div>
	</div>
</div>
</tag:layout>
<script type="text/javascript" src="${resources}/js/github/github-module.js"></script>