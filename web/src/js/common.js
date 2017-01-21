var addr = "http://localhost:8082/";

var house = {
    getHouseList : function(status){
        $.ajax({
            type : "GET",
            url : addr + "house/list.do?status="+status,
            error : function() {
            },
            success : function(ret) {
                $('#content').html(ret);
            }
        });
    },
    toAdd_house : function(){
        $.ajax({
            type : "GET",
            url : addr + "house/toAdd.do",
            error : function() {
            },
            success : function(ret) {
                $('#content').html(ret);
            }
        });
    },
    add_house : function(param){
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
        $.ajax({
            type : "GET",
            url : addr + "house/toEdit.do?houseId="+id,
            error : function() {
            },
            success : function(ret) {
                $('#content').html(ret);
            }
        });
    },
    edit_house : function(param){
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
        $.ajax({
            type : "GET",
            url : addr + "user/list.do?status="+status + "&role=" + role,
            error : function() {
            },
            success : function(ret) {
                $('#content').html(ret);
            }
        });
    },
    toAdd_user: function (role) {
        $.ajax({
            type : "GET",
            url : addr + "user/toAdd.do?role=" + role,
            error : function() {
            },
            success : function(ret) {
                $('#content').html(ret);
            }
        });
    },
    add_user : function(param, role){
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
        $.ajax({
            type : "GET",
            url : addr + "user/toEdit.do?userId="+id + "&role=" + role,
            error : function() {
            },
            success : function(ret) {
                $('#content').html(ret);
            }
        });
    },
    edit_user : function(param, role){
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
    }
}
