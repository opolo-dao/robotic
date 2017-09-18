<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
    <div class="input-group">
        <span class="input-group-addon">Tournament Statistic</span>
        <select id="tournamentsList" class="form-control">
            <c:forEach items="${tournamentsList}" var="tournament">
                <option value="${tournament.id}">${tournament.name}</option>
            </c:forEach>
        </select>
        <span class="input-group-addon btn-default btn" id="setActiveBtn">Set Active</span>

    </div>
    <br>
    <div class="row">
        <div class="col-md-6">
            <ul>
                <h4>General Quantities</h4>

                <li>Robots - <span id="numberOfRobots"></span>
                </li>
                <li>Participants - <span id="numberOfParticipants"></span>

                </li>
                <li>Teams- <span id="numberOfTeams"></span>

                </li>
            </ul>
        </div>
        <div class="col-md-6">
            <ul id="countries">
                <h4>Participant Countries</h4>
            </ul>
        </div>
    </div>
    <div class="row">
        <h4 class="text-center">Robots by competitions</h4>
        <div class="col-md-3">
            <ul><h5><strong>Sumo</strong></h5>
                <li>Free - <strong id="miniSumo"></strong></li>
                <li>Lego - <strong id="legoSumo"></strong></li>
            </ul>
        </div>
        <div class="col-md-3">
            <ul><h5><strong>Linefollower</strong></h5>
                <li>Free - <strong id="freeLinefollower"></strong></li>
                <li>Lego - <strong id="legoLinefollower"></strong></li>
            </ul>
        </div>
        <div class="col-md-3">
            <ul><h5><strong>Folkrace</strong></h5>
                <li>Free - <strong id="freeFolkrace"></strong></li>
                <li>Lego - <strong id="legoFolkrace"></strong></li>
            </ul>
        </div>
        <div class="col-md-3">
            <ul><h5><strong>Labyrinth</strong></h5>
                <li>Free - <strong id="freeLabyrinth"></strong></li>
                <li>Lego - <strong id="legoLabyrinth"></strong></li>
            </ul>
        </div>
    </div>
</div>
