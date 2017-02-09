var addr = "http://localhost:8082/";
//var addr = "http://123.56.105.105/cms/";
var timerId;

function getContent(url){
    $.ajax({
        type : "GET",
        headers : {
            province:$('#selProvince').val(),
            city:$('#selCity').val(),
            district:$('#selDistrict').val()
        },
        url : url,
        error : function() {
        },
        success : function(ret) {
            $('#content').html(ret);
        }
    });
}

var house = {
    getHouseList : function(status){
        clearInterval(timerId);
        var url = addr + "house/list.do?status="+status;
        getContent(url);
        timerId = setInterval(function(){getContent(url)}, 5000);
    },

    getDetails :function(houseId){
        clearInterval(timerId);
        var url = addr + "house/details.do?houseId="+houseId;
        getContent(url);
        timerId = setInterval(function(){getContent(url)}, 5000);
    },

    getFixHistory : function(houseId){
        clearInterval(timerId);
        var url = addr + "house/fixHis.do?houseId="+houseId;
        getContent(url);
        timerId = setInterval(function(){getContent(url)}, 5000);
    },

    getAchieveHistory:function(houseId){
        clearInterval(timerId);
        var url = addr + "house/achieveHis.do?houseId="+houseId;
        getContent(url);
        timerId = setInterval(function(){getContent(url)}, 5000);
    },

    getOrderHistory:function(houseId){
        clearInterval(timerId);
        var url = addr + "house/orderHis.do?houseId="+houseId;
        getContent(url);
        timerId = setInterval(function(){getContent(url)}, 5000);
    },

    toAdd_house : function(){
        clearInterval(timerId);
        var url = addr + "house/toAdd.do";
        getContent(url);
    },

    add_house : function(param){
        clearInterval(timerId);

        $.ajax({
            type : "GET",
            url : addr + "house/add.do?" + param,
            error : function() {
            },
            success : function(ret) {
                if (ret.status == 0){
                    house.getHouseList(1)
                } else{
                    alert(ret.message);
                }
            }
        });
    },

    toEdit_house:function(id){
        clearInterval(timerId);
        var url = addr + "house/toEdit.do?houseId="+id;
        getContent(url);
    },
    edit_house : function(param){
        clearInterval(timerId);

        $.ajax({
            type : "GET",
            url : addr + "house/edit.do?" + param,
            error : function() {
            },
            success : function(ret) {
                if (ret.status == 0){
                    house.getHouseList(1)
                } else{
                    alert(ret.message);
                }
            }
        });
    }


}

var user = {
    getUserList : function(role, status){
        clearInterval(timerId);
        var url = addr + "user/list.do?status="+status + "&role=" + role;
        getContent(url);
        timerId = setInterval(function(){getContent(url)}, 5000);
    },
    toAdd_user: function (role) {
        clearInterval(timerId);
        var url = addr + "user/toAdd.do?role=" + role;
        getContent(url);
    },
    add_user : function(param, role){
        clearInterval(timerId);

        $.ajax({
            type : "GET",
            url : addr + "user/add.do?" + param + "&role=" + role,
            error : function() {
            },
            success : function(ret) {
                if (ret.status == 0){
                    user.getUserList(role,1)
                } else{
                    alert(ret.message);
                }
            }
        });
    },
    toEdit_user:function(id, role){
        clearInterval(timerId);
        var url = addr + "user/toEdit.do?userId="+id + "&role=" + role;
        getContent(url);
    },
    edit_user : function(param, role){
        clearInterval(timerId);

        $.ajax({
            type : "GET",
            url : addr + "user/edit.do?" + param ,
            error : function() {
            },
            success : function(ret) {
                if (ret.status == 0){
                    user.getUserList(role,1)
                } else{
                    alert(ret.message);
                }
            }
        });
    },
    getDetails : function(role, userId){
        clearInterval(timerId);

        if (role == 3){
            var url = addr + "user/orderHis.do?userId="+userId + "&role=" + role;
            getContent(url);
            timerId = setInterval(function(){getContent(url)}, 5000);
        }else{
            var url = addr + "user/achieveHis.do?userId="+userId + "&role=" + role;
            getContent(url);
            timerId = setInterval(function(){getContent(url)}, 5000);
        }
    }
}
