<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<spring:url value="/resources/" var="resources" /><spring:url value="/resources/template" var="template" /><spring:url value="/it" var="it" />
<tag:layout tab="${target}">
	<!--=== Call To Action ===-->
	<div class="call-action-v1 bg-color-light">
		<div class="container">
			<div class="call-action-v1-box">
				<div class="call-action-v1-in">
					<p>Unify creative technology company providing key digital
						services and focused on helping our clients to build a successful
						business on web and mobile.</p>
				</div>
				<div class="call-action-v1-in inner-btn page-scroll">
					<button class="btn-u btn-u-blue margin-bottom-5" onclick="NewsModule.saveNews();">Save News</button>
					<button class="btn-u btn-u-orange margin-bottom-5" onclick="NewsModule.stopNews();">Stop News</button>
				</div>
			</div>
		</div>
	</div>
	<!--=== End Call To Action ===-->
	<div class="container content-xs">
		<div class="row">
			<div class="col-sm-12 form-inline">
				<button class="btn-u btn-u-aqua margin-bottom-5" onclick="NewsModule.getNewsDetail();">Get News</button>
				<input class="form-control" id="newsNumber">
				<input class="form-control" id="websiteName" value="${websiteName }">
			</div>
			
			<div class="col-sm-12">
				<div>
					${itWorld.id }
				</div>
				<div>
					${itWorld.title }
				</div>
				<div>
					${itWorld.content }
				</div>			
			</div>
		</div>
	</div>
</tag:layout>
<script type="text/javascript" src="${resources}/js/news.js"></script>