
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${deletedRobots!= null}">
    <c:forEach items="${deletedRobots}" var="robot">
        Robot with name <strong>${robot.name}</strong> has been deleted, due to lack of operators<br>
    </c:forEach>
</c:if>