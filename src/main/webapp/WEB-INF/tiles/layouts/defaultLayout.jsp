<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=10">
    <security:csrfMetaTags/>
    <title><tiles:getAsString name="title"/></title>
    <link rel="stylesheet" href="/robotic/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/robotic/css/myStyles.css"/>
    <sec:authorize access="hasRole('ADMIN')">
        <security:csrfMetaTags/>
    </sec:authorize>
</head>
<body>
<tiles:importAttribute name="flag" scope="request"/>
<nav class="navbar navbar-default navbar-fixed-top" <%--style="font-family: 'Comic Sans MS'"--%>>
    <div class="container">
        <tiles:insertAttribute name="topMenu"/>
    </div>
</nav>

<div class="container" id="bodyContainer" style="margin-top: 70px">
    <sec:authorize access="hasRole('ADMIN')">
        <button class="button" id="editBody" value="about">Edit</button>
    </sec:authorize>
    <div id="bodyText">
        <tiles:importAttribute name="body"/>
        <c:import url="${body}" charEncoding="utf-8"/>
    </div>
</div>
<footer id="footer" class="footer center-block">
    <div class="container">
        <tiles:insertAttribute name="footer"/>
    </div>
</footer>
<tiles:importAttribute scope="request" name="pageScripts"/>
<script src="/robotic/js/jquery-3.2.1.min.js"></script>
<script src="/robotic/js/bootstrap.min.js"></script>
<sec:authorize access="hasRole('ADMIN')">
    <script src="/robotic/ckeditor/ckeditor.js"></script>
</sec:authorize>
<script src="${pageScripts}"></script>
</body>
</html>
