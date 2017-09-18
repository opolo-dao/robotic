<div class="row">
    <div class="col-md-4">
        <div id="registeredNumberGroup" class="input-group">
            <span class="input-group-addon" id="numberAdd">Robot registration number</span>
            <input type="number" id="robotRegisteredNumber" class="form-control" required="true"
                   aria-describedby="numberAdd"/>
        </div>
    </div>
    <div class="col-md-4">
        <div class="input-group">
            <span class="input-group-addon" id="timeAdd">Result time in seconds</span>
            <input type="number" id="robotTime" class="form-control" required="true"
                   aria-describedby="timeAdd"/>
        </div>
    </div>
    <div class="col-md-4">
        <button id="register" class="button btn-primary" type="button">Register</button>
    </div>
</div>
<br>
<div class="row">
    <table id="lfResultsTable" class="hover  cell-border compact" cellspacing="0" width="100%" style="font-size: 1em">
        <thead>
        <th>Robot</th>
        <th>Registration number</th>
        <th>Time, seconds</th>
        <th>Registration time</th>
        </thead>
    </table>
</div>