<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <ul class="nav nav-pills nav-stacked tab-nav" id="menu">
        <li role="presentation" class="active"><a href="#info" data-toggle="pill">Team info</a></li>
        <li role="presentation"><a href="#members" data-toggle="pill">Members</a></li>
        <li role="presentation"><a href="#robots" data-toggle="pill" class="${team.participants.isEmpty()?"hidden":""}">Robots</a></li>
    </ul>