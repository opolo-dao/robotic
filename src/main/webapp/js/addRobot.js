var checkbox = document.getElementsByName("competitions")
var checked = checkedCount();
function checkedCount() {
    var c = 0;
    var free = false;
    for (i = 0; i < checkbox.length; i++) {

        if (checkbox[i].checked == true) {
            if (checkbox[i].value.search(/free|mini/) > -1)
                free = true;
            c++;
        }

    }
    if (free == false && c != 0) disableAllFreeCompetitionsCheckboxes();
    if (free == true && c != 0) disableAllLegoCompetitionsCheckboxes();
    return c;
}
function enableAllCompetitionsCheckboxes() {
    for (i = 0; i < checkbox.length; i++) {
        checkbox[i].disabled = false;
        checkbox[i].parentElement.style.opacity = "1";

    }
}
function disableAllFreeCompetitionsCheckboxes() {
    for (i = 0; i < checkbox.length; i++) {
        if (checkbox[i].value.search(/free|mini/) > -1) {
            checkbox[i].parentElement.style.opacity = "0.5";
            checkbox[i].disabled = true;
        }

    }
}
function disableAllLegoCompetitionsCheckboxes() {
    for (i = 0; i < checkbox.length; i++) {
        if (checkbox[i].value.search(/lego/) > -1) {
            checkbox[i].disabled = true;
            checkbox[i].parentElement.style.opacity = "0.5";
        }
    }
}
for (i = 0; i < checkbox.length; i++) {
    checkbox[i].onclick = function (e) {
        if (e.srcElement.checked == true) {
            checked++;
            if (checked == 1) {
                if (e.srcElement.value.search(/mini|free/) > -1) {
                    disableAllLegoCompetitionsCheckboxes();
                }
                else {
                    disableAllFreeCompetitionsCheckboxes();
                }
            }
        }
        else {
            checked--
            if (checked == 0) {
                enableAllCompetitionsCheckboxes();
            }
        }
    }

}


