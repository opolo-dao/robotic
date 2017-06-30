$(function () {

    function changeActive(event) {
        event.preventDefault();
        $("#tablenav").children().removeClass('btn-primary');
        $(this).addClass("btn-primary");
    }

    function prepareTable() {
        var table = $('#table1').DataTable({
            "ajax": "/robotic/rest/participants/all",
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
                }
            ]
        });
        return table;
    }

    function changeData(event) {
        table.ajax.url("/robotic/rest/participants/" + event.target.id).load();

    }


    var table = prepareTable();
    $("#tablenav").children().on('click', changeActive);
    $("#tablenav").children().on('click', changeData);


})
