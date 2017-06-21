<%--
<button id="but" class="btn btn-danger" onclick="send()">Send</button>
<div id = "text" ></div>
<script type="text/javascript">
    function send() {
        var cont = {
            name: "Vasily",
            surname: "Pupkind"
        };
        var json = JSON.stringify(cont);
        var xhr = new XMLHttpRequest();
        var url = "http://localhost:8080/robotic/rest/hello";
        xhr.open("POST", url, true);
        xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
xhr.setRequestHeader("X-CSRF-TOKEN", document.getElementsByName("_csrf")[0].content)
        xhr.onreadystatechange = function () {
            if (xhr.readyState != 4) return;
            $(but)[0].innerHTML = "ready";
            alert(xhr.responseText);
        }
        xhr.send(json);

    }
setInterval(function () {
    var hoverArray = document.querySelectorAll(':hover');
    for(i = 0; i < hoverArray.length; i++){
        console.log(hoverArray[i].nodeName)
    }

}, 100);
</script>--%>
