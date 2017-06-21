<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-xs-10 col-sm-8 col-md-6 col-md-offset-3 col-sm-offset-2 col-xs-offset-1">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <div class="row">
                <div class="col-md-6">Edit robot</div>
                <div class="col-md-6 text-right"><strong><em>
<c:choose>
    <c:when test="${oldName != null}">
        <c:out value="${oldName}"></c:out>
    </c:when>
    <c:otherwise>
        <c:out value="${robot.name}"/>
    </c:otherwise>
</c:choose>
                </em></strong></div>
            </div>

        </div>
        <div class="panel-body">
            <form  method="post" id="editRobot" role="form">
                <div class="form-group">
                    <label class="form-group">Robot information</label>
                    <div class="input-group ${errorsMap.containsKey("name")?"has-error":""}">
                        <span class="input-group-addon" id="nameAdd">Name</span>
                        <input type="text" name="name" id="name" class="form-control" required="true"
                               aria-describedby="nameAdd" value="${robot.name}"/>
                    </div>
                    <c:if test="${errors.robotNameErrorMsg != null}">
                        <div class="alert alert-danger alert-dismissible" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                                ${errors.robotNameErrorMsg}</div>
                    </c:if>
                </div>
                <div class="row">
                    <div class="col-lg-6">
                        Please select robot competitions
                        <c:forEach items="${competitions}" var="competition">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" name="competitions" value="${competition}"
                                        ${robot.competitions.contains(competition)?"checked":""}/>
                                        ${competition.goodLook()}
                                </label>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="col-lg-6">
                        Please choose robot operators
                        <c:forEach items="${members}" var="member">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" name="operatorsId" value="${member.id}"
                                        ${robot.operators.contains(member)?"checked":""}/>
                                        ${member.name} ${member.surname}
                                </label>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <c:if test="${errors.robotCompetitionsErrorMsg != null}">
                    <div class="alert alert-danger alert-dismissable" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                            ${errors.robotCompetitionsErrorMsg}</div>
                </c:if>
                <c:if test="${errors.robotOperatorsErrorMsg != null}">
                    <div class="alert alert-danger alert-dismissable"
                         role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                            ${errors.robotOperatorsErrorMsg}</div>
                </c:if>
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
                <input type="submit" class="btn btn-success" value="edit robot &raquo;"/>
                <br>
            </form>
        </div>
    </div>
</div>


