$(function () {
    $("li.btn").on('click', function (e) {
        getGallery(e.target.id);
        $("li.btn").removeClass("btn-primary");
        $(this).addClass("btn-primary");
    })
    $("#photoNav :first-child").trigger('click');

    function getGallery(galleryId) {
        jQuery.get({
            "url": "/robotic/photos/getgallery",
            "data": {"galleryId": galleryId},
            "success": function (data) {
                var photoList = $("ul.photoUL");
                photoList.html("");
                for (let i = 0; i < data.length; i++) {
                    let li = $("<li class='photoUL'></li>");
                    let img = $("<img class='photoUL'>")
                    img.attr("src", location.protocol + "//" + location.host + "/store/photos/" + data[i].filename);
                    img.attr("alt", data[i].title);
                    li.append(img);
                    let p = $("<p class='text'></p>");
                    p.text(data[i].comment);
                    li.append(p);
                    photoList.append(li);
                }
                photoList.bsPhotoGallery({
                    "classes": "col-lg-2 col-md-4 col-sm-3 col-xs-4 col-xxs-12",
                    "hasModal": true
                })
            }
        });
    }
})
