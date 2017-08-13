<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<spring:url value="/resources" var="resources"/>
<spring:url value="/resources/template" var="template"/>
<spring:url value="/myinfo" var="myinfo"/>
<spring:url value="/po" var="po"/>
<tag:layout tab="${target}">
    <div class="bg-color-ocean-g">
        <div class="container">
            <div class="row">
                <div class="ocean-title col-sm-12">
                    <p>약력</p>
                </div>
                <div class="ocean-content col-sm-8">
                    <p>
                        안녕하세요. 설훈입니다.<br>
                        제 지난 날의 경험들과 기록을 여러분들에게 공유하고<br>
                        앞으로의 가능성 있는 기회를 여러분들과 함께 나누고자 합니다.
                    </p>
                </div>
                <sec:authorize access="hasRole('SUPERADMIN')">
                    <div class="col-sm-4">

                    </div>
                </sec:authorize>
            </div>
        </div>
    </div>

    <!--=== End Call To Action ===-->
    <div class="container content-xs">
        <div class="row">
            <div class="col-sm-4">
                <img src="${resources}/img/me/me.jpg" class="myprofile-img">
            </div>

            <div class="col-sm-8">
                <div class="ocean-grid">
                    <h2 class="heading-sm">History &amp; Profile</h2>
                    <br>
                    <ul class="list-unstyled myprofile-ul">
                        <li>한국 지도자 육성 장학재단 41기</li>
                        <li>용인대학교 졸업(체육, 경영)</li>
                        <li>ROTC(학군단) 51기</li>
                        <li>설빙 1기 마케팅 공모전 장려상 수상</li>
                        <li>마이다스 아이티 사관학교 1기</li>
                        <li>(주)아이메디신 연구원</li>
                        <li>고려사이버대학교 소프트웨어 공학과 편입</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="container content-xs">
        <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12 no-margin-bottom">
                <div class="ocean-grid">
                    <h2 class="heading-sm">Project &amp; Skill</h2>
                    <p>
                        <a class="skill a-ocean">#Java</a><a class="skill a-ocean">#Spring</a><a class="skill a-ocean">#Hibernate</a>
                        <br>
                        <a class="skill a-ocean">#Python</a><a class="skill a-ocean">#Django</a><a class="skill a-ocean">#Flask</a><a
                            class="skill a-ocean">#SQLAlchemy</a>
                        <br>
                        <a class="skill a-ocean">#CentOS</a><a class="skill a-ocean">#NginX</a><a class="skill a-ocean">#Tomcat</a>
                        <br>
                        <a class="skill a-ocean">#MariaDB</a><a class="skill a-ocean">#MongoDB</a><a class="skill a-ocean">#Redis</a><a
                            class="skill a-ocean">#Oracle</a>
                        <br>
                        <a class="skill a-ocean">#Java Script</a><a class="skill a-ocean">#Ajax</a><a
                            class="skill a-ocean">#JQuery</a><a class="skill a-ocean">#AngularJS</a>
                        <br>
                        <a class="skill a-ocean">#BootStrap</a><a class="skill a-ocean">#Foundation</a>
                    </p>
                    <ul class="list-unstyled">
                        <li>Git Hub | <a href="https://github.com/Seolhun">https://github.com/Seolhun</a></li>
                        <li>T-Story | <a href="https://github.com/Seolhun">http://postitforhooney.tistory.com/</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</tag:layout>