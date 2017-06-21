<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-4 col-md-offset-4">
    <div class="panel panel-default">
        <div class="panel-heading"><h3 class="panel-title"><strong>Sign in </strong></h3>
            <div style="float:right; font-size: 80%; position: relative; top:-10px"><a href="#">Forgot password?</a>
            </div>
        </div>

        <div class="panel-body">
            <form role="form" method="post">
                <div style="margin-bottom: 12px" class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                    <input id="username" type="text" class="form-control" name="username" value=""
                           placeholder="username">
                </div>

                <div style="margin-bottom: 12px" class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                    <input id="password" type="password" class="form-control" name="password"
                           placeholder="password">
                </div>

                <div class="input-group">
                    <div class="checkbox" style="margin-top: 0px;">
                        <label>
                            <input id="login-remember" type="checkbox" name="remember" value="1"> Remember me
                        </label>
                    </div>
                </div>
                <c:if test="${param.logout != null}">
                    <div class="alert alert-info alert-dismissible text-center" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                        You have logged out!
                    </div>
                </c:if>
                <c:if test="${param.error != null}">
                    <div class="alert alert-danger text-center" role="alert">Invalid username or password!</div>
                </c:if>
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
                <button type="submit" class="btn btn-success">Sign in</button>

                <hr style="margin-top:10px;margin-bottom:10px;">

                <div class="form-group">
                </div>
            </form>
        </div>
    </div>
</div>
