//var addr = "http://localhost:8082/";
var addr = "http://123.56.105.105/cms/";

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
    getDetails :function(houseId){
        $.ajax({
            type : "GET",
            url : addr + "house/details.do?houseId="+houseId,
            error : function() {
            },
            success : function(ret) {
                $('#content').html(ret);
            }
        });
    },
    getFixHistory : function(houseId){
        $.ajax({
            type : "GET",
            url : addr + "house/fixHis.do?houseId="+houseId,
            error : function() {
            },
            success : function(ret) {
                $('#content').html(ret);
            }
        });
    },
    getAchieveHistory:function(houseId){
        $.ajax({
            type : "GET",
            url : addr + "house/achieveHis.do?houseId="+houseId,
            error : function() {
            },
            success : function(ret) {
                $('#content').html(ret);
            }
        });
    },
    getOrderHistory:function(houseId){
        $.ajax({
            type : "GET",
            url : addr + "house/orderHis.do?houseId="+houseId,
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
    },
    getDetails : function(role, userId){
        if (role == 3){
            $.ajax({
                type : "GET",
                url : addr + "user/orderHis.do?userId="+userId + "&role=" + role,
                error : function() {
                },
                success : function(ret) {
                    $('#content').html(ret);
                }
            });
        }else{
            $.ajax({
                type : "GET",
                url : addr + "user/achieveHis.do?userId="+userId + "&role=" + role,
                error : function() {
                },
                success : function(ret) {
                    $('#content').html(ret);
                }
            });

        }

    }
}
