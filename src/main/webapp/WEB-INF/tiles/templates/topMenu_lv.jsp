<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    .dropdown-submenu {
        position: relative;
    }

    .dropdown-submenu > .dropdown-menu {
        top: 0;
        left: 100%;
        margin-top: -6px;
        margin-left: -1px;
        -webkit-border-radius: 0 6px 6px 6px;
        -moz-border-radius: 0 6px 6px;
        border-radius: 0 6px 6px 6px;
    }

    .dropdown-submenu:hover > .dropdown-menu {
        display: block;
    }

    .dropdown-submenu > a:after {
        display: block;
        content: " ";
        float: right;
        width: 0;
        height: 0;
        border-color: transparent;
        border-style: solid;
        border-width: 5px 0 5px 5px;
        border-left-color: #ccc;
        margin-top: 5px;
        margin-right: -10px;
    }

    .dropdown-submenu:hover > a:after {
        border-left-color: #fff;
    }

    .dropdown-submenu.pull-left {
        float: none;
    }

    .dropdown-submenu.pull-left > .dropdown-menu {
        left: -100%;
        margin-left: 10px;
        -webkit-border-radius: 6px 0 6px 6px;
        -moz-border-radius: 6px 0 6px 6px;
        border-radius: 6px 0 6px 6px;
    }
</style>
<div class="navbar-header">
    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
            aria-expanded="false" aria-controls="navbar">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="/robotic">
        <img src="/robotic/pictures/robot_logo.png" class="navbar-brand lead">
    </a>
</div>
<div id="navbar" class="navbar-collapse collapse">
    <ul class="nav navbar-nav">
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
               aria-expanded="false">Sacensības<span class="caret"></span></a>
            <ul class="dropdown-menu">
                <li><a href="/robotic/about/competitions">Par robotikas sacensībām</a></li>
                <li role="separator" class="divider"></li>
                <li><a href="/robotic/about/sumo">Sumo</a></li>
                <li><a href="/robotic/about/linefollower">Līnijsekošana</a></li>
                <li><a href="/robotic/about/labyrinth">Labirints</a></li>
                <li><a href="/robotic/about/folkrace">Folkreiss</a></li>
            </ul>
        </li>
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
               aria-expanded="false">Noteikumi<span class="caret"></span></a>
            <ul class="dropdown-menu">
                <li><a href="/robotic/rules/general">Sacensību nolikums</a></li>
                <li role="separator" class="divider"></li>
                <li><a href="/robotic/rules/sumo">Sumo</a></li>
                <li><a href="/robotic/rules/linefollower">Līnijsekošana</a></li>
                <li><a href="/robotic/rules/labyrinth">Labirints</a></li>
                <li><a href="/robotic/rules/folkrace">Folkreiss</a></li>

            </ul>
        </li>

        <li><a href="/robotic/participants">Dalībnieki</a></li>
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
               aria-expanded="false">Rezultāti<span class="caret"></span></a>
            <ul class="dropdown-menu">

                <c:forEach items="${appService.getAllTournaments()}" var="tournament">
                    <li class="dropdown-submenu">
                        <a href="#">${tournament.name}</a>
                        <ul class="dropdown-menu">

                            <li><a href="/robotic/results/${tournament.id}/sumo">Sumo</a></li>
                            <li><a href="/robotic/results/${tournament.id}/linefollower">Līnijsekošana</a></li>
                            <li><a href="/robotic/results/${tournament.id}/labyrinth">Labirints</a></li>
                            <li><a href="/robotic/results/${tournament.id}/folkrace">Folkreiss</a></li>
                        </ul>
                    </li>
                </c:forEach>
            </ul>
        </li>

        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
               aria-expanded="false">Galerija<span class="caret"></span></a>
            <ul class="dropdown-menu">
                <li><a href="/robotic/videos">Videos</a></li>
                <li><a href="/robotic/photos">Fotos</a></li>
            </ul>
        </li>
        <li><a href="/robotic/agenda">Norises programma</a></li>
        <li><a href="/robotic/about">Par mums</a></li>


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
                    <li><a href="/robotic/menu">Team menu</a></li>
                    <li><a href="/robotic/logout">Logout</a></li>
                </ul>
            </li>
        </sec:authorize>
        <sec:authorize access="hasAnyRole('ADMIN','LF_OPERATOR','REGISTRAR', 'HTML_EDITOR')">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                   aria-expanded="false"><i class="glyphicon-user glyphicon"></i>Menu<span
                        class="caret"></span></a>
                <ul class="dropdown-menu">
                    <sec:authorize access="hasRole('ADMIN')">
                        <li><a href="/robotic/admin">Admin page</a></li>
                    </sec:authorize>
                    <sec:authorize access="hasAnyRole('LF_OPERATOR','ADMIN')">
                        <li><a href="/robotic/operator/lf">LF Operator page</a></li>
                    </sec:authorize>
                    <sec:authorize access="hasAnyRole('ADMIN', 'REGISTRAR')">
                        <li><a href="/robotic/operator/registrar">Registrar page</a></li>
                    </sec:authorize>
                    <li><a href="/robotic/logout">Logout</a></li>
                </ul>
            </li>
        </sec:authorize>
        <sec:authorize access="hasRole('ANONYMOUS')">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                   aria-expanded="false">Pieslēgties<span class="caret"></span></a>
                <ul class="dropdown-menu" style="min-width: 250px; min-height: 120px; padding: 10px;">
                    <li>
                        <div class="row">
                            <div class="col-md-12">
                                <form class="form" role="form" method="post" action="/robotic/login"
                                      accept-charset="UTF-8"
                                      id="login-nav" style="margin-bottom: 0px">
                                    <div style="margin-bottom: 12px" class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                        <input id="username" type="text" class="form-control" name="username" value=""
                                               placeholder="logins">
                                    </div>

                                    <div style="margin-bottom: 12px" class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                        <input id="password" type="password" class="form-control" name="password"
                                               placeholder="parole">
                                    </div>
                                    <button type="submit" class="btn btn-primary btn-block">Pieslēgties</button>
                                    <input type="hidden"
                                           name="${_csrf.parameterName}"
                                           value="${_csrf.token}"/>
                                </form>
                            </div>
                        </div>
                    </li>
                </ul>
            </li>
            <li><a href="/robotic/registration">Reģistrēties</a></li>
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
                <li><a id="ru" href="?lang=ru" class="language"><img src="/robotic/pictures/ru.svg" width="22px">
                    <span>Ru</span></a>
                </li>
            </ul>
        </li>
    </ul>
</div>