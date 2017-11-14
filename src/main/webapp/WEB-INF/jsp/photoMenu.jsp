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

    #photos img {
        height: 50px;
        display: inline-block;
        vertical-align: top;
    }

    #photos .two {
        display: inline-block;
    }
</style>
<div class="row">
    <div class="col-md-4">
        <h4 class="text-center">Upload new Photo</h4>
        <form id="fileForm" enctype="multipart/form-data">
            <label for="photoName">New name for file</label> <input class="form-control" id="photoName" name="photoName"
                                                                    placeholder="New Filename"/>
            <input type="file" name="file" accept="image/*"/>
            <br/>
            <label for="photoFolder">Photo folder</label>
            <input list="photoFolders" class="form-control" id="photoFolder" name="photoFolder"
                   placeholder="Photo folder">
            <datalist id="photoFolders">
                <c:forEach items="${photoFolders}" var="folder">
                    <option value="${folder}"></option>
                </c:forEach>
            </datalist>
            </input>
            <br/>
            <label for="photoTitle">Photo title</label> <input class="form-control" id="photoTitle" name="title"
                                                               placeholder="Title"/><br/>
            <label for="photoDescription">Photo description</label> <input class="form-control" type="text"
                                                                           id="photoDescription"
                                                                           name="description"
                                                                           placeholder="Description"/>

            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
        </form>
        <button class="btn btn-success" id="uploadPhoto" style="width: 100%">Upload</button>
    </div>
    <div class="col-md-4">
        <h4 class="text-center">Photos are on the server</h4>
        <div id="photos">
            <c:forEach items="${filesList}" var="filename" varStatus="i">
                <div name="photoOption">
                    <img src="${filename}" alt="${filename}">
                    <div class="two">
                        <p style="margin-bottom: 5px"><a href="<c:url value="${filename}"></c:url>" target="_blank">
                            <c:set value="${fn:split(filename, '/')}" var="pos"/>
                            <c:out value="${pos[fn:length(pos)-1]}"/>
                        </a>
                        </p>
                        <p style="margin-bottom: 5px">
                            <button value="${filename}" onclick="deletePhoto(this)" class="btn btn-danger btn-xs">
                                Delete
                            </button>
                        </p>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <div class="col-md-4">
        <h4 class="text-center">Add video from Youtube</h4>
        <label for="videoId">Video id from youtube</label> <input type="text" id="videoId" class="form-control"
                                                                  placeholder="Video id">
        <label for="videoTitle">Video title</label> <input class="form-control" id="videoTitle" name="title"
                                                           placeholder="Title"/><br/>
        <button id="addVideoBtn" class="bnt btn-primary" style="width: 100%">Add</button>
        <h4 class="text-center">Videos are on the server</h4>
        <div>
            <ul id="videos" style="list-style-type: none">
                <c:forEach items="${videoList}" var="video" varStatus="i">
                    <li>${video.title}
                        <button value="${video.id},${video.title}" onclick="deleteVideo(this)"
                                class="btn btn-danger btn-xs">
                            Delete
                        </button>
                    </li>

                </c:forEach>
            </ul>
        </div>
    </div>
</div>
