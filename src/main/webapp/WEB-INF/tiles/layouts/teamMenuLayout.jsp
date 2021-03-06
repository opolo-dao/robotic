<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><tiles:getAsString name="title"/></title>
    <link rel="stylesheet" href="/robotic/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/robotic/css/myStyles.css"/>
    <link rel="icon" href="/robotic/pictures/favicon.ico"/>
</head>
<body>
<tiles:importAttribute name="flag" scope="request"/>
<nav class="navbar navbar-default navbar-fixed-top" <%--style="font-family: 'Comic Sans MS'"--%>>
    <div class="container">
        <tiles:insertAttribute name="topMenu"/>
    </div>
</nav>

<div class="container" style="margin-top: 70px">
    <div class="page-header">
        <tiles:insertAttribute name="menuHeader"/>
    </div>
    <div class="row">
        <div class="col-lg-2">
            <tiles:insertAttribute name="menuItemsList"/>
        </div>

        <div class="col-lg-8">
            <tiles:insertAttribute name="menuBody"/>
        </div>

        <div class="col-lg-2">
            <tiles:insertAttribute name="menuAdditionals"/>
        </div>
    </div>
</div>
<footer id="footer" class="footer">
    <div class="container">
        <tiles:importAttribute name="footer"/>
        <c:import url="${footer}" charEncoding="utf-8"/>
    </div>

</footer>
<tiles:importAttribute name="pageScripts" scope="request"/>
<script src="/robotic/js/jquery-3.2.1.min.js"></script>
<script src="/robotic/js/bootstrap.min.js"></script>
<script src="/robotic/js/myScripts.js"></script>

<script src="${pageScripts}"></script>

</body>
</html>
