<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="tab-content">
            <div id="info" class="tab-pane fade in active">
                <div class="panel panel-info">
                    <table class="table table-user-information ">
                        <tbody>
                        <tr>
                            <td>Имя</td>
                            <td>${user.name}</td>
                        </tr>
                        <tr>
                            <td>Фамилия</td>
                            <td>${user.surname}</td>
                        </tr>
                        <tr>
                            <td>Email</td>
                            <td>${user.contact.email}</td>
                        </tr>
                        <tr>
                            <td>Телефона</td>
                            <td>${user.contact.phoneNumber}</td>
                        </tr>
                        <tr>
                            <td>Организация</td>
                            <td>${user.organisation}</td>
                        </tr>
                        <tr>
                            <td>Город</td>
                            <td>${user.city}</td>
                        </tr>
                        <tr>
                            <td>Страна</td>
                            <td>${user.state}</td>
                        </tr>

                        </tbody>
                    </table>

                </div>
                <a role="button" class="btn btn-primary"
                   href="menu/edituser">Изменить профиль</a>
            </div>
            <div id="members" class="tab-pane fade">
                <c:forEach items="${team.participants}" var="member" varStatus="i">
                    <div class="panel-group" id="accordion">
                        <div class="panel panel-info">
                            <div class="panel-heading">
                                <h4 class="panel-title row">
                                    <div class="col-lg-6">
                                        <a data-toggle="collapse" data-parent="#accordion"
                                           href="#member${i.count}">${member.name} ${member.surname}</a>
                                    </div>
                                    <div class="col-lg-3 col-lg-offset-3 text-right">
                                        <a href="menu/editmember?id=${member.id}"
                                           class="label-primary label">изменить</a>
                                        <a href="menu/removemember?id=${member.id}"
                                           class="label-warning label">удалить</a>
                                    </div>
                                </h4>
                            </div>
                            <div id="member${i.count}" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <table class="table">
                                        <body>
                                        <tr>
                                            <td>Возраст</td>
                                            <td>${member.age}</td>
                                        </tr>
                                        <tr>
                                            <td>Email</td>
                                            <td>${member.contact.email}</td>
                                        </tr>
                                        <tr>
                                            <td>Телефон</td>
                                            <td>${member.contact.phoneNumber}</td>
                                        </tr>
                                        </body>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                <a role="button" class="btn btn-primary"
                   href="menu/addmember">Добавить участника</a>

            </div>
            <div id="robots" class="tab-pane fade">
                <c:forEach items="${team.robots}" var="robot" varStatus="i">
                    <div class="panel-group" id="accordion">
                        <div class="panel panel-info">
                            <div class="panel-heading">
                                <h4 class="panel-title row">
                                    <div class="col-lg-6">
                                        <a data-toggle="collapse" data-parent="#accordion"
                                           href="#robot${i.count}">${robot.name}</a>
                                    </div>
                                    <div class="col-lg-3 col-lg-offset-3 text-right">
                                        <a href="menu/editrobot?id=${robot.id}"
                                           class="label-primary label">изменить</a>
                                        <a href="menu/removerobot?id=${robot.id}"
                                           class="label-warning label">удалить</a>
                                    </div>
                                </h4>
                            </div>
                            <div id="robot${i.count}" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div class="col-lg-6">
                                        <h4>Список операторов</h4>
                                        <ul>
                                            <c:forEach items="${robot.operators}" var="operator">
                                                <li> ${operator.name} ${operator.surname}</li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                    <div class="col-lg-6">
                                        <h4>Список соревнований</h4>
                                        <ul>
                                            <c:forEach items="${robot.competitions}" var="competition">
                                                <li> ${competition.goodLook()}</li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                <a role="button" class="btn btn-primary"
                   title="Работает, если в комманде есть хотя бы один участник"
                   href="menu/addrobot">Добавить робота</a>
            </div>
        </div>


