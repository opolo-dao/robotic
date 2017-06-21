// Javascript to enable link to tab
var url = document.location.toString();
if (url.match('#')) {
    $('#menu a[href="#' + url.split('#')[1] + '"]').tab('show');
} //add a suffix
$('#menu a').on('shown.bs.tab', function (e) {
    window.location.hash = e.target.hash;
})