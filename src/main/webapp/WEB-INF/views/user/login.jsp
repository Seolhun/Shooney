<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<spring:url value="/resources" var="resources" /><spring:url value="/resources/template" var="template"/><spring:url value="/" var="shooney"/><spring:url value="/bo" var="bo"/><spring:url value="/po" var="po"/><spring:url value="/myinfo" var="myinfo"/><spring:url value="/admin" var="admin"/><spring:url value="/signup" var="signup"/><spring:url value="/login" var="login"/><spring:url value="/logout" var="logout"/>
<tag:layout>
	<!--=== Content Part ===-->
	<div class="container content-xs">
		<div class="row equal-height-columns">
			<div class="col-md-12 col-sm-12 form-block equal-height-column">
				<h2 class="margin-bottom-30">Login To Your Account</h2>
				<form:form action="${login }">
					<c:if test="${param.error != null}">
						<div class="alert alert-danger">
							<!-- <p>Invalid username and password.</p> -->
							<p>${errorMsg }</p>
						</div>
					</c:if>
					<c:if test="${param.logout != null}">
						<div class="alert alert-success">
							<p>You have been logged out successfully.</p>
						</div>
					</c:if>
					<div class="reg-block">
						<div class="input-group margin-bottom-20">
							<span class="input-group-addon rounded-left">
								<i class="glyphicon glyphicon-user color-ocean"></i>
							</span>
							<input class="form-control rounded-right" name="email" placeholder="Username">
						</div>
						<div class="input-group margin-bottom-20">
							<span class="input-group-addon rounded-left">
								<i class="glyphicon glyphicon-lock color-ocean"></i>
							</span>
							<input type="password" class="form-control rounded-right" name="password" placeholder="Password">
						</div>

						<div class="checkbox">
							<ul class="list-inline">
								<li>
									<label>
										<input type="checkbox" name="remember-me"> Remember me
									</label>
								</li>

								<li class="pull-right">
									<a href="#">Forgot password?</a>
								</li>
							</ul>
						</div>

						<div class="row margin-bottom-10">
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
								<button class="btn-u btn-u-ocean btn-block rounded">Login In</button>
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
								<a href="${signup }"><button type="button" class="btn-u btn-u-blue btn-block rounded">Sign Up</button></a>
							</div>
						</div>

						<div class="social-login text-center">
							<div class="or rounded-x">Or</div>
							<ul class="list-inline margin-bottom-20">
								<li>
									<button class="rounded btn-u-lg btn-u btn-u-dark-blue">
										<i class="fa fa-facebook"></i> Facebook Sign in
									</button>
								</li>
								<li>
									<button class="rounded btn-u-lg btn-u btn-u-aqua">
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
</tag:layout>
