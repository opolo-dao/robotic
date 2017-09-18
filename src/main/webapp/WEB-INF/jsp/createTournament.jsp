<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-10 col-md-offset-1">
    <div class="panel panel-primary">
        <div class="panel-heading">
            Create new tournament
        </div>
        <div class="panel-body">
            <form:form modelAttribute="tournament" method="post">

                <label class="form-group">Tournament name</label>
                <div class="input-group">
                    <span class="input-group-addon" id="nameAdd">Name</span>
                    <form:input path="name" type="text" name="name" id="name" class="form-control"
                                required="true" aria-describedby="nameAdd"
                                value="${tournament.name}"/>
                </div>
                <br style="font-size: 0.5em">

                <label class="form-group">Open registration</label>
                <div class="row">
                    <div class="col-md-6">
                        <div class="input-group">
                            <span class="input-group-addon" id="startAdd">From</span>
                            <form:input path="startRegistrationDateTime" type="text" name="startRegistration"
                                        id="startRegistration"
                                        class="form-control" required="true"
                                        aria-describedby="startAdd"
                                        value="${tournament.startRegistrationDateTime}"/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="input-group">
                            <span class="input-group-addon" id="stopAdd">to</span>
                            <form:input path="endRegistrationDateTime" type="text" name="endRegistration"
                                        id="endRegistration"
                                        class="form-control"
                                        required="true" aria-describedby="stopAdd"
                                        value="${tournament.endRegistrationDateTime}"/>
                        </div>
                    </div>
                </div>
                <br style="font-size: 0.5em">
                <label class="form-group">Event date</label>

                <span class="input-group-addon" id="eventAdd">Date</span>

                <form:input path="eventDateTime" type="text" name="eventDate" id="eventDate"
                            class="form-control"
                            aria-describedby="eventAdd"
                            value="${tournament.eventDateTime}"
                />
                <br style="font-size: 0.5em">

                <label class="form-group">Competitions settings</label>
                <ul class="nav nav-tabs nav-justified" role="tablist">
                    <li role="presentation" class="active"><a href="#line" aria-controls="line" role="tab"
                                                              data-toggle="tab">Linefollower</a>
                    </li>
                    <li role="presentation"><a href="#labyrinth" aria-controls="labyrinth" role="tab" data-toggle="tab">Labyrinth</a>
                    </li>
                    <li role="presentation"><a href="#folkrace" aria-controls="folkrace" role="tab" data-toggle="tab">Folkrace</a>
                    </li>
                </ul>

                <!-- Tab panes -->
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active" id="line">
                        <div class="row">
                            <div class="col-md-6">
                                <h3>Lego category</h3>
                                <label for="legoLFMaxAttempts">Max attempts</label>
                                <form:input path="legoLinefollowerSettings.numberOfAttempts" type="number"
                                            class="form-control" id="legoLFMaxAttempts"
                                            name="legoLFMaxAttempts"/>
                                <label for="legoLFMaxTime">Max time in seconds</label>
                                <form:input path="legoLinefollowerSettings.maxTimeS" type="number" class="form-control"
                                            id="legoLFMaxTime" name="legoLFMaxTime"/>
                            </div>
                            <div class="col-md-6">
                                <h3>Free category</h3>
                                <label for="freeLFMaxAttempts">Max attempts</label>
                                <form:input path="freeLinefollowerSettings.numberOfAttempts" type="number"
                                            class="form-control" id="freeLFMaxAttempts"
                                            name="freeLFMaxAttempts"/>
                                <label for="freeLFMaxTime">Max time in seconds</label>
                                <form:input path="freeLinefollowerSettings.maxTimeS" type="number" class="form-control"
                                            id="freeLFMaxTime" name="freeLFMaxTime"/>
                            </div>
                        </div>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="labyrinth">
                        <div class="row">
                            <div class="col-md-6">
                                <h3>Lego category</h3>
                                <label for="legoLBMaxAttempts">Max attempts</label>
                                <form:input path="legoLabyrinthSettings.maxAttemptsNumber" type="number"
                                            class="form-control" id="legoLBMaxAttempts"
                                            name="legoLBMaxAttempts"/>
                            </div>
                            <div class="col-md-6"><h3>Free category</h3>
                                <label for="freeLBMaxAttempts">Max attempts</label>
                                <form:input path="freeLabyrinthSettings.maxAttemptsNumber" type="number"
                                            class="form-control" id="freeLBMaxAttempts"
                                            name="freeLBMaxAttempts"/>
                            </div>
                        </div>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="folkrace">
                        <div class="row">
                            <div class="col-md-6">
                                <h3>Lego category</h3>
                                <label for="legoFRGroupSize">Group size</label>
                                <form:input path="legoFolkraceSettings.groupSize" type="number" class="form-control"
                                            id="legoFRGroupSize"
                                            name="legoFRGroupSize"/>
                            </div>
                            <div class="col-md-6"><h3>Free category</h3>
                                <label for="freeFRGroupSize">Group size</label>
                                <form:input path="freeFolkraceSettings.groupSize" type="number" class="form-control"
                                            id="freeFRGroupSize"
                                            name="freeFRGroupSize"/>
                            </div>
                        </div>
                    </div>
                </div>
                <br style="font-size: 0.5em">
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
                <input type="hidden"
                       name="formToken"
                       value="${formToken}"/>
                <input type="submit" class="btn btn-success" value="Create tournament &raquo;"/>
            </form:form>
        </div>
    </div>
</div>


