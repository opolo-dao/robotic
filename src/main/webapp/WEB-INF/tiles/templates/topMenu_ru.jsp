<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=utf-8" %>
<div class="navbar-header">
    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
            aria-expanded="false" aria-controls="navbar">
        <span class="sr-only">Переключатель</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="/robotic">
        <img src="/robotic/pictures/robot_logo.png" class="navbar-brand"/>
    </a>
</div>
<div id="navbar" class="navbar-collapse collapse">
    <ul class="nav navbar-nav">
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
               aria-expanded="false">Правила</a>
            <ul class="dropdown-menu">
                <li><a href="/robotic/rules/general">Общие</a></li>
                <li role="separator" class="divider"></li>
                <li><a href="/robotic/rules/sumo">Sumo</a></li>
                <li><a href="/robotic/rules/linefollower">LineFollower</a></li>
                <li><a href="/robotic/rules/labyrinth">Labyrinth</a></li>
                <li><a href="/robotic/rules/folkrace">Folkrace</a></li>

            </ul>
        </li>
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
               aria-expanded="false">Участники</a>
            <ul class="dropdown-menu">
                <li><a href="/robotic/participants/general">Все участники</a></li>
                <li role="separator" class="divider"></li>
                <li><a href="/robotic/participants/sumo">Sumo</a></li>
                <li><a href="/robotic/participants/linefollower">LineFollower</a></li>
                <li><a href="/robotic/participants/labyrinth">Labyrinth</a></li>
                <li><a href="/robotic/participants/folkrace">Folkrace</a></li>
            </ul>
        </li>
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
               aria-expanded="false">Таблицы матчей</a>
            <ul class="dropdown-menu">
                <li><a href="/robotic/tables/sumo">Sumo</a></li>
                <li><a href="/robotic/tables/linefollower">LineFollower</a></li>
                <li><a href="/robotic/tables/labyrinth">Labyrinth</a></li>
                <li><a href="/robotic/tables/folkrace">Folkrace</a></li>
            </ul>
        </li>
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
               aria-expanded="false">Результаты</a>
            <ul class="dropdown-menu">
                <li><a href="/robotic/results/sumo">Sumo</a></li>
                <li><a href="/robotic/results/linefollower">LineFollower</a></li>
                <li><a href="/robotic/results/labyrinth">Labyrinth</a></li>
                <li><a href="/robotic/results/folkrace">Folkrace</a></li>
            </ul>
        </li>
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
               aria-expanded="false">Фото&Видео<span class="caret"></span></a>
            <ul class="dropdown-menu">
                <li><a href="/robotic/videos">Видео</a></li>
                <li><a href="/robotic/photos">Фото</a></li>
            </ul>
        </li>
        <li><a href="/robotic/about">Расписание</a></li>
        <li><a href="/robotic/about">О нас</a></li>

    </ul>

    <ul class="nav navbar-nav navbar-right">
        <sec:authorize access="hasRole('TEAM_OWNER')">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                   aria-expanded="false">
                    <i class="glyphicon-user glyphicon"></i>
                    <strong><em> <sec:authentication property="principal.user.team.name"/></em></strong>
                    <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/robotic/menu">Меню команды</a></li>
                    <li><a href="/robotic/logout">Выйти</a></li>
                </ul>
            </li>
        </sec:authorize>
        <sec:authorize access="hasRole('ADMIN')">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                   aria-expanded="false">Menu<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/robotic/admin">Страница администратора</a></li>
                    <li><a href="/robotic/logout">Выйти</a></li>
                </ul>
            </li>
        </sec:authorize>
        <sec:authorize access="hasRole('ANONYMOUS')">
            <li><a href="/robotic/login">Войти</a></li>
            <li><a href="/robotic/registration">Регистрация</a></li>
        </sec:authorize>

        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" role="button"
               aria-expanded="false"><img id="langImg" src="${flag}" alt="" width="22px"> <span
                    class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
                <li><a id="en" href="?lang=en" class="language"> <img src="/robotic/pictures/gb.svg" width="22px">
                    <span>En</span></a></li>
                <li><a id="lv" href="?lang=lv" class="language"> <img src="/robotic/pictures/lv.svg" width="22px">
                    <span>Lv</span></a></li>
                <li><a id="ru" href="?lang=ru" class="language"><img src="/robotic/pictures/ru.svg" width="22px"> <span>Ru</span></a>
                </li>
            </ul>
        </li>
    </ul>
</div>