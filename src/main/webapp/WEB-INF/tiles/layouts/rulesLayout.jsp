<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
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

<header class="text-center">
    <h3><tiles:getAsString name="title"/> <span class="text-primary">
        ${competitionName}
    </span></h3>
</header>
<section class="container">
    <ul style="list-style-type:none;list-style-position: outside;">
        <li class="definition">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">1.
                        <tiles:getAsString name="definitionTitle"/>
                        <sec:authorize access="hasRole('ADMIN')">
                            <button class="button" id="editDefinition" value="definition">edit</button>
                        </sec:authorize>
                    </h3>
                </div>
                <div class="panel-body" id="definitionText">
                    <tiles:importAttribute name="definition"/>
                    <c:import url="${definition}" charEncoding="utf-8"/>

                </div>
            </div>
        </li>
        <li class="conditions">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">2.
                        <tiles:getAsString name="conditionsTitle"/> <sec:authorize access="hasRole('ADMIN')">
                            <button class="button" id="editConditions" value="conditions">edit</button>
                        </sec:authorize>
                    </h3>

                </div>
                <div class="panel-body" id="conditionsText">
                    <tiles:importAttribute name="conditions"/>
                    <c:import url="${conditions}" charEncoding="utf-8"/>
                </div>
            </div>
        </li>
        <li class="field">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">3.
                        <tiles:getAsString name="fieldTitle"/>
                        <sec:authorize access="hasRole('ADMIN')">
                            <button class="button" id="editField" value="field">edit</button>
                        </sec:authorize>
                    </h3>
                </div>
                <div class="panel-body" id="fieldText">
                    <tiles:importAttribute name="field"/>
                    <c:import url="${field}" charEncoding="utf-8"/>
                </div>
            </div>

        </li>
        <li class='requirements'>

            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">4.
                        <tiles:getAsString name="requirementsTitle"/>
                        <sec:authorize access="hasRole('ADMIN')">
                            <button class="button" id="editRequirements" value="requirements">edit</button>
                        </sec:authorize>
                    </h3>
                </div>
                <div class="panel-body" id="requirementsText">
                    <tiles:importAttribute name="requirements"/>
                    <c:import url="${requirements}" charEncoding="utf-8"/>
                </div>
            </div>
        </li>
        <li class='procedure'>

            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">5.
                        <tiles:getAsString name="procedureTitle"/>
                        <sec:authorize access="hasRole('ADMIN')">
                            <button class="button" id="editProcedure" value="procedure">edit</button>
                        </sec:authorize>
                    </h3>
                </div>
                <div class="panel-body" id="procedureText">
                    <tiles:importAttribute name="procedure"/>
                    <c:import url="${procedure}" charEncoding="utf-8"/>
                </div>
            </div>

        </li>
    </ul>
</section>

<footer id="footer" class="footer">
    <div class="container">
        <tiles:importAttribute name="footer"/>
        <c:import url="${footer}" charEncoding="utf-8"/>
    </div>
</footer>
<script src="/robotic/js/jquery-3.2.1.min.js"></script>
<script src="/robotic/js/bootstrap.min.js"></script>
<sec:authorize access="hasRole('ADMIN')">
    <script src="/robotic/ckeditor/ckeditor.js"></script>
</sec:authorize>
<tiles:importAttribute scope="request" name="pageScripts"></tiles:importAttribute>
<script src="${pageScripts}"></script>
</body>
</html>
