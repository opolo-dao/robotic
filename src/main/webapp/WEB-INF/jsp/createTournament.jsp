<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-10 col-md-offset-1">
    <div class="panel panel-primary">
        <div class="panel-heading">
            Create new tournament
        </div>
        <div class="panel-body">
            <form method="post">

                <label class="form-group">Tournament name</label>
                <div class="input-group">
                    <span class="input-group-addon" id="nameAdd">Name</span>
                    <input type="text" name="name" id="name" class="form-control"
                           required="true" aria-describedby="nameAdd"
                           value="${tournament.name}"/>
                </div>
                <br style="font-size: 0.5em">

                <label class="form-group">Open registration</label>
                <div class="row">
                    <div class="col-md-6">
                        <div class="input-group">
                            <span class="input-group-addon" id="startAdd">From</span>
                            <input type="datetime-local" name="startRegistration" id="startRegistration"
                                   class="form-control" required="true"
                                   aria-describedby="startAdd" placeholder="YYYY-MM-DD"
                                   value="${tournament.startRegistrationDate}"/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="input-group">
                            <span class="input-group-addon" id="stopAdd">to</span>
                            <input type="datetime-local" name="endRegistration" id="endRegistration"
                                   class="form-control"
                                   required="true" aria-describedby="stopAdd"
                                   value="${tournament.endRegistrationDate}"/>
                        </div>
                    </div>
                </div>
                <br style="font-size: 0.5em">
                <label class="form-group">Event date</label>

                <div class="input-group ${errors.containsKey("contactEmailErrorMsg")?"has-error":""}">
                    <span class="input-group-addon" id="eventAdd">Date</span>

                    <input type="datetime-local" name="eventDate" id="eventDate" class="form-control"
                           aria-describedby="eventAdd"
                           value="${member.contact.email}"/>
                </div>
                <c:if test="${errors.contactEmailErrorMsg != null}">
                    <div class="alert alert-danger alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                            ${errors.contactEmailErrorMsg}</div>
                </c:if>
                <br style="font-size: 0.5em">

                <label class="form-group">Competitions settings</label>
                <ul class="nav nav-tabs nav-justified" role="tablist">
                    <li role="presentation" class="active"><a href="#sumo" aria-controls="sumo" role="tab"
                                                              data-toggle="tab">Sumo</a></li>
                    <li role="presentation"><a href="#line" aria-controls="line" role="tab" data-toggle="tab">Linefollower</a>
                    </li>
                    <li role="presentation"><a href="#labyrinth" aria-controls="labyrinth" role="tab" data-toggle="tab">Labyrinth</a>
                    </li>
                    <li role="presentation"><a href="#folkrace" aria-controls="folkrace" role="tab" data-toggle="tab">Folkrace</a>
                    </li>
                </ul>

                <!-- Tab panes -->
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active" id="sumo">
                        <div class="row">
                            <div class="col-md-6">Lego</div>
                            <div class="col-md-6">Mini</div>
                        </div>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="line">
                        <div class="row">
                            <div class="col-md-6">
                                <h3>Lego category</h3>
                                <label for="legoLFMaxAttempts">Max attempts</label>
                                <input type="number" class="form-control" id="legoLFMaxAttempts"
                                       name="legoLFMaxAttempts"/>
                                <label for="legoLFMaxTime">Max time in ms</label>
                                <input type="number" class="form-control" id="legoLFMaxTime" name="legoLFMaxTime"/>
                            </div>
                            <div class="col-md-6">
                                <h3>Free category</h3>
                                <label for="freeLFMaxAttempts">Max attempts</label>
                                <input type="number" class="form-control" id="freeLFMaxAttempts"
                                       name="freeLFMaxAttempts"/>
                                <label for="freeLFMaxTime">Max time in ms</label>
                                <input type="number" class="form-control" id="freeLFMaxTime" name="freeLFMaxTime"/>
                            </div>
                        </div>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="labyrinth">
                        <div class="row">
                            <div class="col-md-6">
                                <h3>Lego category</h3>
                                <label for="legoLBMaxAttempts">Max attempts</label>
                                <input type="number" class="form-control" id="legoLBMaxAttempts"
                                       name="legoLBMaxAttempts"/>
                                <label for="legoLBMaxTime">Max time in ms</label>
                                <input type="number" class="form-control" id="legoLBMaxTime" name="legoLBMaxTime"/>
                            </div>
                            <div class="col-md-6"><h3>Free category</h3>
                                <label for="freeLBMaxAttempts">Max attempts</label>
                                <input type="number" class="form-control" id="freeLBMaxAttempts"
                                       name="freeLBMaxAttempts"/>
                                <label for="freeLBMaxTime">Max time in ms</label>
                                <input type="number" class="form-control" id="freeLBMaxTime" name="freeLBMaxTime"/>
                            </div>
                        </div>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="folkrace">
                        <div class="row">
                            <div class="col-md-6">
                                <h3>Lego category</h3>
                                <label for="legoFRMaxAttempts">Max attempts</label>
                                <input type="number" class="form-control" id="legoFRMaxAttempts"
                                       name="legoFRMaxAttempts"/>
                                <label for="legoFRMaxTime">Max time in ms</label>
                                <input type="number" class="form-control" id="legoFRMaxTime" name="legoFRMaxTime"/>
                            </div>
                            <div class="col-md-6"><h3>Free category</h3>
                                <label for="freeFRMaxAttempts">Max attempts</label>
                                <input type="number" class="form-control" id="freeFRMaxAttempts"
                                       name="freeFRMaxAttempts"/>
                                <label for="freeFRMaxTime">Max time in ms</label>
                                <input type="number" class="form-control" id="freeFRMaxTime" name="freeFRMaxTime"/>
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
            </form>
        </div>
    </div>
</div>
