$(function () {
    var globRow;

    function getTournamentMenu() {
        jQuery.get({
            "url": "/robotic/admin/tournamentsinfo",
            "success": function (data) {
                $("#menuBody").html(data);
                $("#tournamentsList").on('change', function (event) {
                    $("#tournamentsList option:selected").each(function () {
                        var tournamentId = $(this)[0].value;
                        getTournamentStatistic(tournamentId)
                    })
                })
                getTournamentStatistic($("#tournamentsList option:selected")[0].value);
                $("#setActiveBtn").on('click', function () {
                    var tournamentId = $("#tournamentsList option:selected")[0].value;
                    jQuery.get({
                        "url": "/robotic/admin/rest/setactivetournament",
                        "data": {"tournamentId": tournamentId},
                        "success": function (data) {
                            location.reload()
                        }
                    })
                })

            }
        })

    }

    function getTournamentStatistic(tournamentId) {
        jQuery.get({
            "url": "/robotic/admin/rest/gettournamentstatistic",
            "data": {"tournamentId": tournamentId},
            "success": function (data) {
                $("#numberOfRobots").html(data["robots"]);
                $("#numberOfParticipants").html(data["participants"]);
                $("#numberOfTeams").html(data["teams"]);
                var countryUL = $("#countries");
                $("#countries > li").remove();
                for (i = 0; i < data["countries"].length; i++) {
                    var li = $("<li></li>");
                    li.html(data["countries"][i]);
                    countryUL.append(li);
                }
                var competitions = data["competitionsRobots"];
                for (c in competitions) {
                    $("#" + c).html(competitions[c]);
                }
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
            $("#sendComment").on("click", function () {
                var id = $("#robotId").val();
                var comment = $("#message").val();
                var token = $("meta[name='_csrf']").attr("content");
                var header = $("meta[name='_csrf_header']").attr("content");
                var sec = {};
                sec[header] = token;
                jQuery.post({
                    "url": "/robotic/admin/rest/commentrobot",
                    "data": {
                        "id": id,
                        "comment": comment
                    },
                    "headers": sec
                });
                $("#myModal").modal('hide');
                globRow.remove().draw(false);

                var badge = $("#robotsToCheckBadge")[0];
                badge.innerHTML = badge.innerHTML - 1;
            })
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
                        "defaultContent": "<button value='1'>Accept</button><button value='0'>Decline</button>"
                    }
                ]
            })

            $('#robotsTable tbody').on('click', 'button', function () {
                    var row = table.row($(this).parents('tr'));
                globRow = row;
                    var data = row.data();
                    var token = $("meta[name='_csrf']").attr("content");
                    var header = $("meta[name='_csrf_header']").attr("content");
                    var sec = {};
                    sec[header] = token;
                if ($(this).val() == 1) {
                    jQuery.post({
                        "url": "/robotic/admin/rest/acceptrobot",
                        "data": {"id": data[5]},
                        "headers": sec
                    });
                    row.remove().draw(false);
                    var badge = $("#robotsToCheckBadge")[0];
                    badge.innerHTML = badge.innerHTML - 1;

                }
                else {
                    $("#robotId").val(data[5]);
                    $("#myModal").modal('show');


                }

                }
            )

        })

    })

    $("#tournaments").on("click", function () {
        getTournamentMenu();
    })
    $("#editFooter").on("click", function () {
        CKEDITOR.config.allowedContent = true;
        var editor = CKEDITOR.replace("footerText");
        var button = document.createElement("button");
        button.innerHTML = "ok";
        button.id = "OK";
        button.onclick = function () {
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            var sec = {};
            sec[header] = token;
            $.ajax({
                type: "POST",
                url: "/robotic/admin/rest/updatefooter",
                data: {
                    text: editor.getData()
                },
                headers: sec,
                success: function () {
                    editor.destroy()
                    button.remove();
                }
            })

        }
        $("#footer").append(button)
    })

})