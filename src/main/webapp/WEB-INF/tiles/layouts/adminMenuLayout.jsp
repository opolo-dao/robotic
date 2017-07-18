<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><tiles:getAsString name="title"/></title>
    <link rel="stylesheet" href="/robotic/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/robotic/css/myStyles.css"/>
    <link rel="stylesheet" href="/robotic/DataTables/datatables.css"/>
    <security:csrfMetaTags/>
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

        <div id="menuBody" class="col-lg-8">

        </div>

        <div class="col-lg-2">
            <tiles:insertAttribute name="menuAdditionals"/>
        </div>
    </div>
</div>
<footer id="footer" class="footer">
    <div class="container">
        <tiles:insertAttribute name="footer"/>
    </div>

</footer>
<tiles:importAttribute scope="request" name="pageScripts"/>
<script src="/robotic/js/jquery-3.2.1.min.js"></script>
<script src="/robotic/js/bootstrap.min.js"></script>
<script src="/robotic/DataTables/datatables.js"></script>
<script src="/robotic/js/myScripts.js"></script>
<script src="${pageScripts}"></script>
</body>
</html>
