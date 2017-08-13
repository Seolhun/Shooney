<%@ tag description="Layout Template" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ attribute name="tab" required="false" type="java.lang.String" %>
<spring:url value="/" var="shooney"/>
<spring:url value="/resources" var="resources"/>
<spring:url value="/resources/template" var="template"/>
<spring:url value="/webjars" var="webjars"/>
<spring:url value="/blog" var="blog"/>
<spring:url value="/project" var="project"/>
<spring:url value="/portfolio" var="portfolio"/>
<spring:url value="/myinfo" var="myinfo"/>
<spring:url value="/admin" var="admin"/>
<spring:url value="/signup" var="signup"/>
<spring:url value="/login" var="login"/>
<spring:url value="/logout" var="logout"/>
<spring:url value="/news" var="news"/>
<spring:url value="/admin" var="admin"/>
<spring:url value="/menu" var="menu"/>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta http-equiv="Cache-Control" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="keywords" content="Shooney, Blog, News, Music">
    <meta name="description" content="Shooney's Blog">
    <meta name="author" content="Shooney">
    <meta id="csrfToken" name="csrfToken" content="${_csrf.token}"/>
    <meta id="csrfHeader" name="csrfHeader" content="${_csrf.headerName}"/>

    <title>SomeThing New | Shooney Blog</title>

    <link rel="shortcut icon" href="${resources}/img//ocean/logo/logo.png"/>

    <!-- Web Fonts -->
    <link rel='stylesheet' type='text/css'
          href='//fonts.googleapis.com/css?family=Open+Sans:400,300,600&amp;subset=cyrillic,latin'>

    <%-- jQuery --%>
    <script type="text/javascript" src="${webjars}/jquery/2.2.4/jquery.min.js"></script>
    <%-- BootStrap --%>
    <link rel="stylesheet" href="${webjars }/bootstrap/3.3.6/css/bootstrap.min.css">

    <!-- CSS Default -->
    <link rel="stylesheet" href="${resources}/css/app.css">
    <link rel="stylesheet" href="${resources}/css/menu.css">
    <link rel="stylesheet" href="${resources}/css/search.css">

    <!-- Summernote CSS -->
    <link href="${resources }/vendor/summer/summernote.css" rel="stylesheet">
</head>
<body class="tutorial-single translation">
<div class="wrapper">
    <header>
        <div class="main-menu-container container">
            <div class="row text-right">
                <sec:authorize access="isAuthenticated()">
                    <a class="a-black button" href="#me"><sec:authentication property="principal.username"/></a>
                    <sec:authorize access="hasRole('SUPERADMIN')">
                        <a class="a-black button" href="${admin}/user/list">Admin</a>
                    </sec:authorize>
                    <a class="a-black button" href="${logout }">Logout</a></b>
                </sec:authorize>
                <sec:authorize access="isAnonymous()">
                    <a class="a-black button" href="${login }">Sign in</a>
                    <a class="a-ocean button" href="${signup }">Sign up</a>
                </sec:authorize>
            </div>

            <div class="row">
                <div class="logo">
                    <a class="logo-icon" href="${shooney}">
                        <img src="${resources}/img/ocean/logo/logo3.png" width="200" height="50">
                    </a>
                </div>

                <a class="mobile-nav-toggle icon-menu-thin" data-role="left">
                    <span>Menu</span>
                </a>
                <nav class="ocean-menu">
                    <ul>
                        <sec:authorize access="hasRole('SUPERADMIN')">
                            <c:forEach items="${menuList }" var="menu">
                                <c:choose>
                                    <c:when test="${menu.menuUrl==null}">
                                        <li class="dropdown">
                                            <a href="javascript:void(0);" class="dropdown-toggle">${menu.menuName}</a>
                                            <ul class="dropdown-menu">
                                                <c:forEach items="${menu.submenuList}" var="submenu">
                                                    <li>
                                                        <a href="${shooney}${submenu.menuUrl}" class="dropdown-toggle">${submenu.menuName}</a>
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li><a href="${shooney}${menu.menuUrl}"
                                               class="dropdown-toggle">${menu.menuName}</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </sec:authorize>
                    </ul>
                </nav>
            </div>
        </div>
        <!-- End Navbar -->
    </header>
    <!--=== End Header ===-->
    <!-- ----HEADER END -----------------------------------------------------------------------------------------------------------------------------  -->

    <%--------------JSP INNER BODY START--------------%>
    <jsp:doBody/>
    <%--------------JSP INNER BODY END--------------%>


    <!-- ---FOOTER START------------------------------------------------------------------------------------------------------------------------------  -->
    <footer class="Footer">
        <span class="Footer-logo icon-sammy"></span>

        <div>Copyright © 2017 Hi-Cord™ Inc.</div>

        <ul class="Footer-nav">
            <li><a href="/blog">Community</a></li>
            <li><a href="/community/tutorials">Tutorials</a></li>
            <li><a href="/community/questions">Questions</a></li>
            <li><a href="/community/projects">Projects</a></li>
            <li><a href="/community/tags">Tags</a></li>
            <li><a href="/community/newsletter">Newsletter</a></li>
            <li>
                <a href="/community/tutorials/feed">RSS <span class="icon-rss"></span></a>
            </li>
        </ul>

        <br>

        <ul class="Footer-nav Footer-nav--secondary">
            <li><a href="https://github.com/Seolhun">Git Hub</a></li>
            <li><a href="http://postitforhooney.tistory.com/">Blog</a></li>
        </ul>
    </footer>
</div><!--/wrapper-->

<%-- Vue JS--%>
<script type="text/javascript" src="${resources }/vendor/vuejs/vuejs.js"></script>
<script type="text/javascript" src="${resources }/vendor/vuejs/vue-resource.js"></script>

<!-- AngularJS -->
<script type="text/javascript" src="${webjars}/angular/1.6.3/angular.min.js"></script>
<script type="text/javascript" src="${webjars}/angular-sanitize/1.6.3/angular-sanitize.min.js"></script>

<!-- Boostrap Library JS  -->
<script type="text/javascript" src="${webjars}/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${webjars}/bootstrap/3.3.6/js/tooltip.js"></script>

<!-- Smart Editor JS -->
<script type="text/javascript" src="${resources }/vendor/summer/summernote.js"></script>

<!-- Language JS -->
<script type="text/javascript" src="${resources}/vendor/i18next/i18next.min.js"></script>
<script type="text/javascript" src="${resources }/js/common/common-lang.js"></script>
<script type="text/javascript" src="${resources }/js/common/common-function.js"></script>
</body>
</html>