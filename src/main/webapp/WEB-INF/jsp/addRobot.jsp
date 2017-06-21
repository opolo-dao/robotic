<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-xs-10 col-sm-8 col-md-6 col-md-offset-3 col-sm-offset-2 col-xs-offset-1">
    <div class="panel panel-primary">
        <div class="panel-heading">
            Add new robot to team
        </div>
        <div class="panel-body">
            <form method="post" id="robotRegistration" role="form">
                <div class="form-group">
                    <label class="form-group">Robot information</label>
                    <div class="input-group ${errorsMap.containsKey("name")?"has-error":""}">
                        <span class="input-group-addon" id="nameAdd">Name</span>
                        <input type="text" name="name" id="name" class="form-control" required="true"
                               aria-describedby="nameAdd" value="${name}"/>
                    </div>
                    <c:if test="${errors.robotNameErrorMsg != null}">
                        <div class="alert alert-danger alert-dismissible" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                                ${errors.robotNameErrorMsg}</div>
                    </c:if>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        Please select robot competitions
                        <c:forEach items="${competitions}" var="competition">
                            <div class="checkbox" >
                                <label>
                                    <input type="checkbox" name="competitions" value="${competition}"
                                    ${checkedCompetitions.contains(competition)?"checked":""}>
                                        ${competition.goodLook()}
                                </label>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="col-md-6">
                        Please choose robot operators
                        <c:forEach items="${members}" var="member">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" name="operatorsId" value="${member.id}"
                                        ${checkedParticipants.contains(member.id)?"checked":""}>
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
                <input type="submit" class="btn btn-success" value="add robot &raquo;"/>

            </form>
        </div>
    </div>
</div>
