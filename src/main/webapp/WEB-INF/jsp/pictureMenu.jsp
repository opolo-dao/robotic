<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<style>
    .easyPaginateNav a {
        padding: 5px;
    }

    .easyPaginateNav a.current {
        font-weight: bold;
        text-decoration: underline;
    }

    #pictures img {
        height: 50px;
        display: inline-block;
        vertical-align: top;
    }

    #pictures .two {
        display: inline-block;
    }
</style>
<div class="row">
    <div class="col-md-4">
        <h4>Upload new file</h4>
        <form id="fileForm" method="post" action="/robotic/admin/upload" enctype="multipart/form-data">
            <input name="name"/>
            <input type="file" name="file" accept="image/*"/>
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
        </form>
        <button class="btn btn-success" id="uploadPicture" style="width: 100%">Upload</button>
    </div>
    <div class="col-md-8">
        <h4>Files are on the server</h4>
        <div id="pictures">
            <c:forEach items="${filesList}" var="filename" varStatus="i">
                <div name="imageOption">

                    <img src="${filename}" alt="${filename}">
                    <div class="two">
                        <p style="margin-bottom: 5px"><a href="<c:url value="${filename}"></c:url>" target="_blank">
                            <c:set value="${fn:split(filename, '/')}" var="pos"/>
                            <c:out value="${pos[fn:length(pos)-1]}"/>
                        </a>
                        </p>
                        <p style="margin-bottom: 5px">
                            <button value="${filename}" onclick="deletePicture(this)" class="btn btn-danger btn-xs">
                                Delete
                            </button>
                        </p>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>