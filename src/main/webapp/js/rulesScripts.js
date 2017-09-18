$(function () {
    CKEDITOR.config.allowedContent = true;
    $("button").on("click", function () {

        var part = $(this)[0].value
        var editor = CKEDITOR.replace(part + "Text");
        var button = document.createElement("button");
        button.innerHTML = "ok";
        button.id = part + "OK";
        button.onclick = function () {
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            var sec = {};
            sec[header] = token;
            $.ajax({
                type: "POST",
                url: "/robotic/admin/rest/updaterules",
                data: {
                    part: part,
                    text: editor.getData()
                },
                headers: sec,
                success: function () {
                    editor.destroy()
                    button.remove();
                }
            })

        }
        $("." + part + " .panel-title").append(button)
    })
})