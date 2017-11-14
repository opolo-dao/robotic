<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
    <div class="col-md-12">
        <h4 class="text-center">Mail templates working menu</h4>
    </div>
</div>
<div class="row">
    <select id="mailTemplates" style="vertical-align: middle">
        <c:forEach items="${templates}" var="template">
            <option>${template}</option>
        </c:forEach>
    </select>

    <button id="deleteMailTemplateButton" type="button" class="btn btn-danger btn-xs" style="height: 21px">Delete
    </button>
    <button id="newMailTemplateButton" type="button" class="btn btn-primary btn-xs" style="height: 21px">New</button>

    <input id="newTemplateName" type="text" style="height: 21px; vertical-align: middle"
           placeholder="New template name">
    <select id="mailingEvents" style="vertical-align: middle">
        <c:forEach items="${mailingEvents}" var="event">
            <option>${event}</option>
        </c:forEach>
    </select>
    <button id="setMailingEventBtn" type="button" class="btn btn-primary btn-xs">Set</button>

    <button id="saveButton" type="button" class="btn btn-primary btn-xs" style="float: right" disabled>Save</button>

</div>
<div class="row" style="margin-top: 5px">
    <textarea id="mail"></textarea>
</div>

