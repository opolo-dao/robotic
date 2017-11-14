<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<style>
    ul.photoUL {
        padding: 0 0 0 0;
        margin: 0 0 0 0;
    }

    ul li.photoUL {
        list-style: none;
        margin-bottom: 25px;
    }

    ul li img.photoUL {
        cursor: pointer;
    }
</style>

<ul class="nav nav-pills" id="photoNav" style="margin-bottom: 10px">
    <c:forEach items="${photosMap.keySet()}" var="folder">
        <li class="btn btn-default" id="${folder}" style="margin: 2px;">${folder}</li>
    </c:forEach>
</ul>
<ul class="photoUL">
</ul>


<%--
    <li class="photoUL">
        <img class="photoUL" src="${photoStore}${photo.filename}" alt="${photo.title}">
        <p class="text">${photo.comment}</p>
    </li>
--%>




