$(function () {
    $("#editBody").on("click", function () {
        var editor = CKEDITOR.replace("bodyText");
        CKEDITOR.config.allowedContent = true;
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
                url: "/robotic/admin/rest/updatehome",
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
    $("#editFooter").on("click", function () {
        var editor = CKEDITOR.replace("footerText");
        CKEDITOR.config.allowedContent = true;
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
                url: "/robotic/HTML_editor/footer",
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