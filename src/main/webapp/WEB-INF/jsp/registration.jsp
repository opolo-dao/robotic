<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-xs-10 col-sm-8 col-md-6 col-md-offset-3 col-sm-offset-2 col-xs-offset-1">
    <div class="panel panel-primary">
        <div class="panel-heading">Registration form</div>
        <div class="panel-body">
            <form:form action="/robotic/registration" method="post" id="registration" role="form"
                       modelAttribute="registrationInfo">
                <label for="teamInfo" class="">Team name</label>
                <div class="form-group" id="teamInfo" name="teamInfo">
                    <div class="row">
                        <div class="col-md-12" style="padding: 5px 10px 5px 10px">
                            <div class="input-group">
                                <span class="input-group-addon" id="teamNameAdd">Name</span>
                                <form:input path="teamName" type="text" name="teamName" id="teamName"
                                            class="form-control"
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
                                <form:input path="orgName" type="text" name="orgName" id="orgName" class="form-control"
                                            required="true" aria-describedby="orgNameAdd"
                                            placeholder="Organisation name"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6" style="padding: 5px 2px 5px 10px">
                            <div class="input-group">
                                <span class="input-group-addon" id="cityAdd">City</span>
                                <form:input path="city" type="text" name="city" id="city" class="form-control"
                                            required="true" aria-describedby="cityAdd" placeholder="Organisation city"/>
                            </div>
                        </div>
                        <div class="col-md-6" style="padding: 5px 10px 5px 2px">
                            <div class="input-group">
                                <span class="input-group-addon" id="stateAdd">State</span>
                                <form:input path="state" type="text" name="state" id="state" class="form-control"
                                            required="true" aria-describedby="stateAdd"
                                            placeholder="Organisation state"/>
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
                                <form:input path="name" type="text" name="name" id="name" class="form-control"
                                            required="true"
                                            aria-describedby="nameAdd" placeholder="Supervisor name"/>
                            </div>
                        </div>

                        <div class="col-md-6" style="padding: 5px 10px 5px 2px">
                            <div class="input-group">
                                <span class="input-group-addon" id="surnameAdd">Surname</span>
                                <form:input path="surname" type="text" name="surname" id="surname" class="form-control"
                                            required="true"
                                            aria-describedby="surnameAdd" placeholder="Supervisor surname"/>
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
                                <form:input path="phone" type="tel" name="phone" id="phone" class="form-control"
                                            aria-describedby="phoneAdd" placeholder="Supervisor phone number"/>
                            </div>
                        </div>
                        <div class="col-md-6" style="padding: 5px 10px 5px 2px">
                            <div class="input-group">
                                <span class="input-group-addon" id="emailAdd">Email</span>
                                <form:input path="email" type="text" name="email" id="email" class="form-control"
                                            required="true"
                                            aria-describedby="emailAdd" placeholder="Supervisor email"/>
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
                <label for="loginInfo" class="">Authorization information</label>
                <div class="form-group" id="loginInfo" name="loginInfo">
                    <div class="row">
                        <div class="col-md-12" style="padding: 5px 10px 5px 10px">
                            <div class="input-group">
                                <span class="input-group-addon" id="loginAdd">Login</span>
                                <form:input path="login" type="text" name="login" id="login" class="form-control"
                                            required="true"
                                            aria-describedby="loginAdd" placeholder="Login"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6" style="padding: 5px 2px 5px 10px">
                            <div class="input-group">
                                <span class="input-group-addon" id="password1Add">Password</span>
                                <form:input path="password1" type="password" name="password1" id="password1"
                                            class="form-control" required="true"
                                            aria-describedby="passwordAdd" placeholder="Password"/>
                            </div>
                        </div>
                        <div class="col-md-6" style="padding: 5px 10px 5px 2px">
                            <div class="input-group">
                                <span class="input-group-addon" id="password2Add">Password</span>
                                <form:input path="password2" type="password" name="password2" id="password2"
                                            class="form-control" required="true"
                                            aria-describedby="password2Add" placeholder="Confirm password"/>
                            </div>
                        </div>
                        <c:if test="${errors.userPasswordErrorMsg != null}">
                            <div class="alert alert-danger alert-dismissible" role="alert">
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                    ${errors.userPasswordErrorMsg}</div>
                        </c:if>

                    </div>
                </div>
                <input type="submit" class="btn btn-success" value="Register team &raquo;">

                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
            </form:form>
        </div>
    </div>
</div>
