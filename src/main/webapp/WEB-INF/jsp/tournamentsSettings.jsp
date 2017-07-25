<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<select id="tournamentsList">
    <c:forEach var="tournament" items="${tournamentsList}">
        <option value="${tournament.id}">${tournament.name}</option>
    </c:forEach>
</select>
<div>
    <ul>
        <c:forEach var="country" items="${countries}">
        <li>
            ${country}</ul>
    </c:forEach>
    </ul>
</div>