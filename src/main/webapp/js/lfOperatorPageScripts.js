$(function () {
    var response = "Robot registration number";
    $("#registeredNumberGroup").tooltip({
        title: function () {
            return response;
        },
        container: 'body'
    })

    var table = $("#lfResultsTable").DataTable({
        "ajax": "/robotic/operator/rest/lf/table",
        "searching": false,
        "paging": false,
        "info": false
    })
    $("#register").on("click", function () {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        var sec = {};
        sec[header] = token;
        var data = {};
        data["number"] = $("#robotRegisteredNumber")[0].value;
        data["time"] = $("#robotTime")[0].value;
        if (data["number"] == "" || data["time"] == "") {
            alert("Incorrect input data");
            return;
        }
        jQuery.post({
            "url": "/robotic/operator/rest/lf/register",
            "data": data,
            "headers": sec,
            "success": function (data) {
                table.row.add(data).draw(false);
                $("#robotRegisteredNumber")[0].value = "";
                $("#robotTime")[0].value = "";
            },
            "error": function (xhr) {
                alert(xhr.responseText)
            }
        });
    })
    $("#robotRegisteredNumber").on('change', function () {
        var number = $(this)[0].value;
        $("#robotRegisteredNumber").blur();
        jQuery.get({
            "url": "/robotic/operator/rest/lf/checknumber",
            "data": {"number": number},
            "success": function (data, status) {
                $("#registeredNumberGroup").removeClass("has-error").addClass("has-success");
                response = data;
            },
            "error": function (xhr) {
                $("#registeredNumberGroup").removeClass("has-success").addClass("has-error");

                response = xhr.responseText;
            }
        })
    })
})