<table id="robotsTable" class="hover  cell-border compact" cellspacing="0" width="100%" style="font-size: 1em">
    <thead>
    <th>Robot</th>
    <th>Team</th>
    <th>Organisation</th>
    <th>Operators</th>
    <th>Competitions</th>
    <th>Action</th>
    </thead>
</table>
<div id="myModal" class="modal fade" role="dialog">
    <div class="modal-dialog modal-sm">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <div class="modal-title">Send message to robot</div>
                <input type="hidden" id="robotId"/>
            </div>
            <div class="modal-body">
                <textarea id="message"></textarea>
            </div>
            <div class="modal-footer">
                <button type="button" id="sendComment" class="btn btn-default">Send</button>
            </div>
        </div>

    </div>
</div>
