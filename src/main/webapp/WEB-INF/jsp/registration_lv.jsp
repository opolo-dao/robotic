<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-xs-10 col-sm-8 col-md-6 col-md-offset-3 col-sm-offset-2 col-xs-offset-1">
    <div class="panel panel-primary">
        <div class="panel-heading">Reģistrācijas lapa</div>
        <div class="panel-body">
            <form:form action="/robotic/registration" method="post" id="registration" role="form"
                       modelAttribute="registrationInfo">
                <label for="teamInfo" class="">Komandas nosaukums</label>
                <div class="form-group" id="teamInfo" name="teamInfo">
                    <div class="row">
                        <div class="col-md-12" style="padding: 5px 10px 5px 10px">
                            <div class="input-group">
                                <span class="input-group-addon" id="teamNameAdd">Nosaukums</span>
                                <form:input path="teamName" type="text" name="teamName" id="teamName"
                                            class="form-control"
                                            required="true" aria-describedby="teamNameAdd"
                                            placeholder="Komandas nosaukums"/>
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
                <label for="organisation" class="">Iestādes informācija</label>
                <div class="form-group" id="organisation" name="organisation">
                    <div class="row">
                        <div class="col-md-12" style="padding: 5px 10px 5px 10px">
                            <div class="input-group">
                                <span class="input-group-addon" id="orgNameAdd">Nosaukums</span>
                                <form:input path="orgName" type="text" name="orgName" id="orgName" class="form-control"
                                            required="true" aria-describedby="orgNameAdd"
                                            placeholder="Iestādes nosaukums"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6" style="padding: 5px 2px 5px 10px">
                            <div class="input-group">
                                <span class="input-group-addon" id="cityAdd">Pilsēta</span>
                                <form:input path="city" type="text" name="city" id="city" class="form-control"
                                            required="true" aria-describedby="cityAdd" placeholder="Iestādes pilsēta"/>
                            </div>
                        </div>
                        <div class="col-md-6" style="padding: 5px 10px 5px 2px">
                            <div class="input-group">
                                <span class="input-group-addon" id="stateAdd">Valsts</span>
                                <form:input path="state" type="text" name="state" id="state" class="form-control"
                                            required="true" aria-describedby="stateAdd"
                                            placeholder="Iestādes valsts"/>
                            </div>
                        </div>
                    </div>

                </div>
                <label for="person">Komandas vadītājs</label>
                <div class="form-group" id="person" name="person">
                    <div class="row">
                        <div class="col-md-6" style="padding: 5px 2px 5px 10px">
                            <div class="input-group">
                                <span class="input-group-addon" id="nameAdd">Vārds</span>
                                <form:input path="name" type="text" name="name" id="name" class="form-control"
                                            required="true"
                                            aria-describedby="nameAdd" placeholder="Vadītāja vārds"/>
                            </div>
                        </div>

                        <div class="col-md-6" style="padding: 5px 10px 5px 2px">
                            <div class="input-group">
                                <span class="input-group-addon" id="surnameAdd">Uzvārds</span>
                                <form:input path="surname" type="text" name="surname" id="surname" class="form-control"
                                            required="true"
                                            aria-describedby="surnameAdd" placeholder="vadītāja uzvārds"/>
                            </div>
                        </div>
                    </div>
                </div>
                <label for="contact" class="">Kontaktinformācija</label>
                <div class="form-group" id="contact" name="contact">
                    <div class="row">
                        <div class="col-md-6" style="padding: 5px 2px 5px 10px">
                            <div class="input-group">
                                <span class="input-group-addon" id="phoneAdd">Tālrunis</span>
                                <form:input path="phone" type="tel" name="phone" id="phone" class="form-control"
                                            aria-describedby="phoneAdd" placeholder="Tālrunis"/>
                            </div>
                        </div>
                        <div class="col-md-6" style="padding: 5px 10px 5px 2px">
                            <div class="input-group">
                                <span class="input-group-addon" id="emailAdd">E-pasts</span>
                                <form:input path="email" type="text" name="email" id="email" class="form-control"
                                            required="true"
                                            aria-describedby="emailAdd" placeholder="e-pasts"/>
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
                <label for="loginInfo" class="">Autorizācijas informācija</label>
                <div class="form-group" id="loginInfo" name="loginInfo">
                    <div class="row">
                        <div class="col-md-12" style="padding: 5px 10px 5px 10px">
                            <div class="input-group">
                                <span class="input-group-addon" id="loginAdd">Login</span>
                                <form:input path="login" type="text" name="login" id="login" class="form-control"
                                            required="true"
                                            aria-describedby="loginAdd" placeholder="Login"/>
                            </div>
                            <c:if test="${errors.userLoginErrorMsg != null}">
                                <div class="alert alert-danger alert-dismissible" role="alert">
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                                            aria-hidden="true">&times;</span></button>
                                        ${errors.userLoginErrorMsg}</div>
                            </c:if>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6" style="padding: 5px 2px 5px 10px">
                            <div class="input-group">
                                <span class="input-group-addon" id="password1Add">Parole</span>
                                <form:input path="password1" type="password" name="password1" id="password1"
                                            class="form-control" required="true"
                                            aria-describedby="passwordAdd" placeholder="Parole"/>
                            </div>
                        </div>
                        <div class="col-md-6" style="padding: 5px 10px 5px 2px">
                            <div class="input-group">
                                <span class="input-group-addon" id="password2Add">Parole</span>
                                <form:input path="password2" type="password" name="password2" id="password2"
                                            class="form-control" required="true"
                                            aria-describedby="password2Add" placeholder="Paroles atkartojums"/>
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
                <input type="submit" class="btn btn-success" value="Reģistrēt komandu &raquo;">

                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
            </form:form>
        </div>
    </div>
</div>
