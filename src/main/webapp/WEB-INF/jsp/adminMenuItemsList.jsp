<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul class="nav nav-pills nav-stacked tab-nav" id="menu">
    <li role="presentation" class="active">
        <a id="tournaments" role="button">Tournament</a>
    </li>
    <li role="presentation"><a id="uploadPicture" role="button">Upload pictures</a></li>
    <li role="presentation"><a role="button">Competitions</a></li>
    <li role="presentation">
        <a id="robotsToCheck" role="button">Check robots<span id="robotsToCheckBadge"
                                                              class="badge">${robotsToCheckBadge}</span></a>
    </li>
</ul>