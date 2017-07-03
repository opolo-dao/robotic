$(function () {
    $("#robotsToCheck").on('click', function () {
        jQuery.get("/robotic/admin/checkrobots", function (data) {
                $("#menuBody").html(data);
                $("#robotsTable").dataTable({
                    "ajax": "/robotic/rest/participants/all",
                    "search": false
                })
            }
        )
    })
})