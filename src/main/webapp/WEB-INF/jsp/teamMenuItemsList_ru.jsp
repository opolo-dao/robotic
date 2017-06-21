<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <ul class="nav nav-pills nav-stacked tab-nav" id="menu">
        <li role="presentation" class="active"><a href="#info" data-toggle="pill">Информация о команде</a></li>
        <li role="presentation"><a href="#members" data-toggle="pill">Участники</a></li>
        <li role="presentation"><a href="#robots" data-toggle="pill" class="${team.participants.isEmpty()?"hidden":""}">Роботы</a></li>
    </ul>