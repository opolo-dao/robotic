$(function () {
    $("#editBody").on("click", function () {
        var editor = CKEDITOR.replace("bodyText");
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
                url: "/robotic/admin/rest/updategeneralrules",
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
        $("#bodyContainer").append(button)
    })
})
