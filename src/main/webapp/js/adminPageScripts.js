$(function () {
    function getTournamentMenu() {
        jQuery.get({
            "url": "/robotic/admin/tournamentssettings",
            "success": function (data) {
                $("#menuBody").html(data);
                $("#tournamentsList").on('change', function (event) {
                    $("#tournamentsList option:selected").each(function () {
                        var tournamentId = {"tournamentId": $(this)[0].value}
                        getTournamentStatistic(tournamentId)
                    })
                })
            }
        })
    }

    function getTournamentStatistic(tournamentId) {
        jQuery.get({
            "url": "/robotic/admin/rest/gettournamentstatistic",
            "data": tournamentId,
            "success": function (data) {
                console.log(data)
            }
        })
    }

    getTournamentMenu();
    $("#uploadPicture").on("click", function () {
        jQuery.get({
            "url": "/robotic/admin/uploadpicture",
            "success": function (data) {
                $("#menuBody").html(data);
            }
        })
    })
    $("#robotsToCheck").on('click', function () {
        jQuery.get("/robotic/admin/checkrobots", function (data) {
            $("#menuBody").html(data);
            var table = $("#robotsTable").DataTable({
                "ajax": "/robotic/admin/rest/robotstochecklist",
                "searching": false,
                "ordering": false,
                "columnDefs": [
                    {
                        "render": function (data, type, row) {
                            var arr = data.split(',');
                            if (arr.length > 1) {
                                var result = "<ul style='margin: 0px;list-style-type: none;padding: 0px'>";
                                for (i = 0; i < arr.length; i++) {
                                    result += '<li>' + arr[i] + '</li>';
                                }

                                return result += '</ul>';
                            }
                            else return data;
                        },
                        "targets": [3, 4]
                    },
                    {
                        "targets": -1,
                        "data": null,
                        "defaultContent": "<button>Accept</button>"
                    }
                ]
            })
            $('#robotsTable tbody').on('click', 'button', function () {
                    var row = table.row($(this).parents('tr'));
                    var data = row.data();
                    var token = $("meta[name='_csrf']").attr("content");
                    var header = $("meta[name='_csrf_header']").attr("content");
                    var sec = {};
                    sec[header] = token;
                    console.log(sec);
                    jQuery.post({
                        "url": "/robotic/admin/rest/acceptrobot",
                        "data": {"id": data[5]},
                        "headers": sec
                    });
                    row.remove().draw(false);
                    var badge = $("#robotsToCheckBadge")[0];
                    badge.innerHTML = badge.innerHTML - 1;

                }
            )

        })

    })
    $("#tournaments").on("click", function () {
        getTournamentMenu();
    })

})