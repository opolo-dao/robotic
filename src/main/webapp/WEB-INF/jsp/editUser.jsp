<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-xs-10 col-sm-8 col-md-6 col-md-offset-3 col-sm-offset-2 col-xs-offset-1">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <div class="row">
                <div class="col-md-6">Edit profile</div>
                <div class="col-md-6 text-right"><strong><em>${user.name} ${user.surname}</em></strong></div></div>

        </div>
        <div class="panel-body">
            <form method="post" id="editUser" role="form">
                <label for="teamInfo" class="">Team name</label>
                <div class="form-group" id="teamInfo" name="teamInfo">
                    <div class="row">
                        <div class="col-md-12" style="padding: 5px 10px 5px 10px">
                            <div class="input-group">
                                <span class="input-group-addon" id="teamNameAdd">Name</span>
                                <input type="text" name="teamName" id="teamName"
                                       class="form-control" value="${user.team.name}"
                                       required="true" aria-describedby="teamNameAdd" placeholder="Team name"/>
                            </div>
                        </div>
                    </div>
                    <c:if test="${errors.teamNameErrorMsg != null}">
                        <div class="alert alert-danger alert-dismissible" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                                ${errors.teamNameErrorMsg}</div>
                    </c:if>
                </div>
                <label for="organisation" class="">Organisation info</label>
                <div class="form-group" id="organisation" name="organisation">
                    <div class="row">
                        <div class="col-md-12" style="padding: 5px 10px 5px 10px">
                            <div class="input-group">
                                <span class="input-group-addon" id="orgNameAdd">Name</span>
                                <input type="text" name="orgName" id="orgName" class="form-control"
                                       required="true" aria-describedby="orgNameAdd"
                                       value="${user.organisation}" placeholder="Organisation name"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6" style="padding: 5px 2px 5px 10px">
                            <div class="input-group">
                                <span class="input-group-addon" id="cityAdd">City</span>
                                <input type="text" name="city" id="city" class="form-control"
                                       required="true" aria-describedby="cityAdd"
                                       value="${user.city}" placeholder="Organisation city"/>
                            </div>
                        </div>
                        <div class="col-md-6" style="padding: 5px 10px 5px 2px">
                            <div class="input-group">
                                <span class="input-group-addon" id="stateAdd">State</span>
                                <input type="text" name="state" id="state" class="form-control"
                                       required="true" aria-describedby="stateAdd"
                                       value="${user.city}" placeholder="Organisation state"/>
                            </div>
                        </div>
                    </div>

                </div>
                <label for="person">Supervisor info</label>
                <div class="form-group" id="person" name="person">
                    <div class="row">
                        <div class="col-md-6" style="padding: 5px 2px 5px 10px">
                            <div class="input-group">
                                <span class="input-group-addon" id="nameAdd">Name</span>
                                <input type="text" name="name" id="name" class="form-control"
                                       required="true" aria-describedby="nameAdd"
                                       value="${user.name}" placeholder="Supervisor name"/>
                            </div>
                        </div>

                        <div class="col-md-6" style="padding: 5px 10px 5px 2px">
                            <div class="input-group">
                                <span class="input-group-addon" id="surnameAdd">Surname</span>
                                <input type="text" name="surname" id="surname" class="form-control"
                                       required="true" aria-describedby="surnameAdd"
                                       value="${user.surname}" placeholder="Supervisor surname"/>
                            </div>
                        </div>
                    </div>
                </div>
                <label for="contact" class="">Contact information</label>
                <div class="form-group" id="contact" name="contact">
                    <div class="row">
                        <div class="col-md-6" style="padding: 5px 2px 5px 10px">
                            <div class="input-group">
                                <span class="input-group-addon" id="phoneAdd">Phone</span>
                                <input type="tel" name="phone" id="phone" class="form-control"
                                       aria-describedby="phoneAdd"
                                       value="${user.contact.phoneNumber}" placeholder="Supervisor phone number"/>
                            </div>
                        </div>
                        <div class="col-md-6" style="padding: 5px 10px 5px 2px">
                            <div class="input-group">
                                <span class="input-group-addon" id="emailAdd">Email</span>
                                <input type="text" name="email" id="email" class="form-control"
                                       required="true" aria-describedby="emailAdd"
                                       value="${user.contact.email}" placeholder="Supervisor email"/>
                            </div>
                            <c:if test="${errors.contactEmailErrorMsg != null}">
                                <div class="alert alert-danger alert-dismissible" role="alert">
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                                            aria-hidden="true">&times;</span></button>
                                        ${errors.contactEmailErrorMsg}</div>
                            </c:if>
                        </div>
                    </div>
                </div>

                <input type="submit" class="btn btn-success" value="Confirm &raquo;">

                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
            </form>
        </div>
    </div>
</div>
