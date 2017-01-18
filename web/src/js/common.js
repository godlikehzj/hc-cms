var addr = "http://localhost:8082/";

function getHouseList(status){
    $.ajax({
        type : "GET",
        url : addr + "house/list.do?status="+status,
        error : function() {
        },
        success : function(ret) {
            $('#content').html(ret);
        }
    });
}

function add_house(){
    $.ajax({
        type : "GET",
        url : addr + "house/toAdd.do",
        error : function() {
        },
        success : function(ret) {
            $('#content').html(ret);
        }
    });
}