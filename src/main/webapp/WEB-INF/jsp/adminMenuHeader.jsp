<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="addNew" value="admin/createtournament"/>
<div class="row">
    <span class="col-lg-2 text-center">Selected tournament</span>
    <span class="col-lg-8 text-center"><h4>${tournament.name}</h4></span>
    <span class="col-lg-2 text-center"><a class="btn btn-primary" href="${addNew}">Crete new</a> </span>
</div>