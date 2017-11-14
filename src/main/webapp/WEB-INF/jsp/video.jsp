<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul id="svList">
    <c:forEach items="${videoList}" var="video">
        <li class="svThumb ytVideo" data-videoID="${video.id}">${video.title}</li>
    </c:forEach>
</ul>