<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><tiles:getAsString name="title"/></title>
    <link rel="stylesheet" href="/robotic/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/robotic/css/myStyles.css"/>
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
                    </h3>
                </div>
                <div class="panel-body">
                    <tiles:importAttribute name="definition"/>
                    <c:import url= "${definition}" charEncoding="utf-8"/>

                </div>
            </div>
        </li>
        <li class="conditions">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">2.
                        <tiles:getAsString name="conditionsTitle"/></h3>
                </div>
                <div class="panel-body">
                    <tiles:importAttribute name="conditions"/>
                    <c:import url= "${conditions}" charEncoding="utf-8"/>
                </div>
            </div>
        </li>
        <li class="field">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">3.
                        <tiles:getAsString name="fieldTitle"/>
                    </h3>
                </div>
                <div class="panel-body">
                    <tiles:importAttribute name="field"/>
                    <c:import url= "${field}" charEncoding="utf-8"/>
                </div>
            </div>

        </li>
        <li class='robotRequirements'>

            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">4.
                    <tiles:getAsString name="requirementsTitle"/>
                    </h3>
                </div>
                <div class="panel-body">
                    <tiles:importAttribute name="requirements"/>
                    <c:import url= "${requirements}" charEncoding="utf-8"/>
                </div>
            </div>
        </li>
        <li class='gameImplementation'>

            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">5.
                    <tiles:getAsString name="procedureTitle"/>
                    </h3>
                </div>
                <div class="panel-body">
                    <tiles:importAttribute name="procedure"/>
                    <c:import url= "${procedure}" charEncoding="utf-8"/>
                </div>
            </div>

        </li>
    </ul>
</section>

<footer id="footer" class="footer">
    <div class="container">
        <tiles:insertAttribute name="footer"/>
    </div>
</footer>
<script src="/robotic/js/jquery-3.2.1.min.js"></script>
<script src="/robotic/js/bootstrap.min.js"></script>
</body>
</html>
