<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="tab-content">
            <div id="info" class="tab-pane fade in active">
                <div class="panel panel-info">

                </div>
                <a role="button" class="btn btn-primary"
                   href="menu/edituser">edit profile</a>
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
                                           class="label-primary label">edit</a>
                                        <a href="menu/removemember?id=${member.id}"
                                           class="label-warning label">remove</a>
                                    </div>
                                </h4>
                            </div>
                            <div id="member${i.count}" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <table class="table">
                                        <body>
                                        <tr>
                                            <td>Age</td>
                                            <td>${member.age}</td>
                                        </tr>
                                        <tr>
                                            <td>Email</td>
                                            <td>${member.contact.email}</td>
                                        </tr>
                                        <tr>
                                            <td>Phone</td>
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
                   href="menu/addmember">add member</a>

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
                                           class="label-primary label">edit</a>
                                        <a href="menu/removerobot?id=${robot.id}"
                                           class="label-warning label">remove</a>
                                    </div>
                                </h4>
                            </div>
                            <div id="robot${i.count}" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div class="col-lg-6">
                                        <h4> Operators list</h4>
                                        <ul>
                                            <c:forEach items="${robot.operators}" var="operator">
                                                <li> ${operator.name} ${operator.surname}</li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                    <div class="col-lg-6">
                                        <h4> Competitions list</h4>
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
                   title="Works when team has at least one member"
                   href="menu/addrobot">add robot</a>
            </div>
        </div>


