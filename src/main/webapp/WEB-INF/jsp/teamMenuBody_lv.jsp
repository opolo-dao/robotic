<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="tab-content">
    <div id="info" class="tab-pane fade in active">
        <div class="panel panel-info">
            <table class="table table-user-information ">
                <tbody>
                <tr>
                    <td>Vārds</td>
                    <td>${user.name}</td>
                </tr>
                <tr>
                    <td>Uzvārds</td>
                    <td>${user.surname}</td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td>${user.contact.email}</td>
                </tr>
                <tr>
                    <td>Tālrunis</td>
                    <td>${user.contact.phoneNumber}</td>
                </tr>
                <tr>
                    <td>Iestādes nosaukums</td>
                    <td>${user.organisation}</td>
                </tr>
                <tr>
                    <td>Pilsēta</td>
                    <td>${user.city}</td>
                </tr>
                <tr>
                    <td>Valsts</td>
                    <td>${user.state}</td>
                </tr>

                </tbody>
            </table>

        </div>
        <a role="button" class="btn btn-primary"
           href="menu/edituser">mainīt profilu</a>
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
                                   class="label-primary label">mainīt</a>
                                <a href="menu/removemember?id=${member.id}"
                                   class="label-warning label">dzēst</a>
                            </div>
                        </h4>
                    </div>
                    <div id="member${i.count}" class="panel-collapse collapse">
                        <div class="panel-body">
                            <table class="table">
                                <body>
                                <tr>
                                    <td>Vecums</td>
                                    <td>${member.age}</td>
                                </tr>
                                <tr>
                                    <td>Email</td>
                                    <td>${member.contact.email}</td>
                                </tr>
                                <tr>
                                    <td>Tālrunis</td>
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
           href="menu/addmember">pievienot dalībnieku</a>

    </div>
    <div id="robots" class="tab-pane fade">
        <c:forEach items="${team.robots}" var="robot" varStatus="i">
        <div class="panel-group" id="accordion">
            <c:if test="${robot.checked && robot.adminComment == null}">
            <c:if test="${robot.tournaments.contains(activeTournament)}">
            <div class="panel panel-success">
                </c:if>
                <c:if test="${!robot.tournaments.contains(activeTournament)}">
                <div class="panel panel-warning">
                    </c:if>
                    </c:if>
                    <c:if test="${!robot.checked}">
                    <div class="panel panel-info">
                        </c:if>
                        <c:if test="${robot.checked && robot.adminComment != null}">
                        <div class="panel panel-danger">
                            </c:if>

                            <div class="panel-heading">
                                <h4 class="panel-title row">
                                    <div class="col-lg-3">
                                        <a data-toggle="collapse" data-parent="#accordion"
                                           href="#robot${i.count}">${robot.name}</a>
                                    </div>
                                    <c:if test="${!robot.checked}">
                                        <div class="col-lg-6">
                                            <i class="glyphicon glyphicon-search"></i>Robots gaida administratora
                                            apstiprināšanu
                                        </div>
                                    </c:if>
                                    <c:if test="${robot.checked}">
                                        <div class="col-lg-6">
                                            <c:if test="${robot.adminComment == null}">
                                                <c:if test="${robot.tournaments.contains(activeTournament)}">
                                                    <i class="glyphicon glyphicon-ok-sign"></i>
                                                    Pielaists sacensībām ${activeTournament.name}
                                                </c:if>
                                                <c:if test="${!robot.tournaments.contains(activeTournament)}">
                                                    <i class="glyphicon glyphicon-minus-sign"></i>
                                                    Piedalījās iepriekšējos turnīros
                                                </c:if>
                                            </c:if>
                                            <c:if test="${robot.adminComment != null}">
                                                <i
                                                        class="glyphicon glyphicon-thumbs-down"></i> ${robot.adminComment}
                                            </c:if>
                                        </div>
                                    </c:if>
                                    <div class="col-lg-3 text-right">
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
                                        <h4>Operatoru saraksts</h4>
                                        <ul>
                                            <c:forEach items="${robot.operators}" var="operator">
                                                <li> ${operator.name} ${operator.surname}</li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                    <div class="col-lg-6">
                                        <h4>Sacensību sarakts</h4>
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
                       href="menu/addrobot">pievienot robotu</a>
                </div>
            </div>



