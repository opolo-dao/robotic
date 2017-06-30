<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-xs-10 col-sm-8 col-md-6 col-md-offset-3 col-sm-offset-2 col-xs-offset-1">
    <div class="panel panel-primary">
        <div class="panel-heading">
            Add new team member
        </div>
        <div class="panel-body">
            <form method="post" accept-charset="UTF-8">
                <label class="form-group">Personal data</label>
                    <br style="font-size: 0.5em">
                    <div class="input-group">
                        <span class="input-group-addon" id="nameAdd">Name</span>
                        <input type="text" name="name" id="name" class="form-control"
                               required="true" aria-describedby="nameAdd"
                        value="${member.name}"/>
                    </div>
                    <br style="font-size: 0.5em">
                    <div class="input-group">
                        <span class="input-group-addon" id="surnameAdd">Surname</span>
                        <input type="text" name="surname" id="surname"
                               class="form-control" required="true"
                               aria-describedby="surnameAdd"
                        value="${member.surname}"/>
                    </div>
                    <br style="font-size: 0.5em">
                    <div class="input-group">
                        <span class="input-group-addon" id="ageAdd">Age</span>
                        <input type="number" name="age" id="age" class="form-control"
                               required="true" min="1" aria-describedby="ageAdd"
                        value="${member.age}"/>
                    </div>
                    <br style="font-size: 0.5em">
                    <label class="form-group">Contact information</label>
                    <div class="input-group ${errors.containsKey("contactEmailErrorMsg")?"has-error":""}">
                        <span class="input-group-addon" id="emailAdd">Email</span>

                        <input type="email" name="email" id="email" class="form-control"
                               placeholder="leave empty if not exist"
                               aria-describedby="emailAdd"
                        value="${member.contact.email}"/>
                    </div>
                <c:if test="${errors.contactEmailErrorMsg != null}">
                    <div class="alert alert-danger alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                            ${errors.contactEmailErrorMsg}</div>
                </c:if>

                    <br style="font-size: 0.5em">
                    <div class="input-group">
                        <span class="input-group-addon" id="phoneAdd">Phone number</span>
                        <input type="tel" name="phone" id="phone"
                               class="form-control" placeholder="leave empty if not exist"
                               aria-describedby="phoneAdd"
                        value="${member.contact.phoneNumber}"/>
                    </div>
                    <br style="font-size: 0.5em">
                    <input type="hidden"
                           name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                <input type="hidden"
                       name="formToken"
                       value="${formToken}"/>
                    <input type="submit" class="btn btn-success" value="add member &raquo;"/>
            </form>
        </div>
    </div>
</div>
