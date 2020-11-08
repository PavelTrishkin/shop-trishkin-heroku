var stomp = null;

// подключаемся к серверу по окончании загрузки страницы
window.onload = function () {
    connect();
};

function connect() {
    var socket = new SockJS('/socket');
    stomp = Stomp.over(socket);
    stomp.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stomp.subscribe('/topic/bucket', function (bucket) {
            renderItem(bucket);
        });
    });
}

function renderItem(bucketJson) {
    var tbl = $("#table");
    var detail = JSON.parse(bucketJson.body);
    tbl.append("<tr>" +
        "<td>" + detail.amountProducts + "</td>" +
        "<td>" + detail.sum + "</td>" +
        // "<td>"+detail.am+"</td>" +
        // "<td>" +"</td>" +
        "</tr>");
}