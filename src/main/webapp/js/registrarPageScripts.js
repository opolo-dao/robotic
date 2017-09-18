$(function () {
    $("#teamSelect").on("change", function (e) {
        var selectedTeamId = $("option:selected", this)[0].value;
        jQuery.get({
            "url": "/robotic/operator/registrar/getteamrobots",
            "data": {"teamid": selectedTeamId},
            "success": function (data) {
                $("#teamRobots option").remove();

                for (i = 0; i < data.length; i++) {
                    $("#teamRobots").append($("<option>",
                        {
                            value: data[i]["id"],
                            text: data[i]["name"]
                        }))
                }
            }
        });
    })
    $("#registerRobotBtn").on("click", function () {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        var sec = {};
        sec[header] = token;
        var data = {};
        data["id"] = $("option:selected", $("#teamRobots"))[0].value;
        data["number"] = $("#registrationNumber")[0].value;
        jQuery.post({
            "url": "/robotic/operator/registrar/setrobotnumber",
            "data": data,
            "headers": sec,
            "success": function () {
                alert("Robot successfully registered");
            },
            "error": function () {
                alert("Upps. Maybe this number already occupied or robot doesnt accepted by administrator to active tournament");
            }
        });
    })
})