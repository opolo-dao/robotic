<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<select id="teamSelect">
    <option disabled selected value> Select team</option>
    <c:forEach items="${teams}" var="team">
        <option value="${team.id}">${team.name}</option>
    </c:forEach>
</select>
<select id="teamRobots"></select>
<input id="registrationNumber" type="number" min="1">
<button id="registerRobotBtn">Register</button>