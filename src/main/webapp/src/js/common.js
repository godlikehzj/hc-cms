var addr = "http://localhost:8082/";
//var addr = "http://www.bjhongchaohuanbao.com/cms/";
var timerId;

function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = year + seperator1 + month + seperator1 + strDate;
    return currentdate;
}

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

function loadMap(){
    $.ajax({
        type : "GET",
        headers : {
            province:$('#selProvince').val(),
            city:$('#selCity').val(),
            district:$('#selDistrict').val()
        },
        url : addr + "house/getlist.do",
        error : function() {
        },
        success : function(data) {

            var opts = {
                width : 250,     // 信息窗口宽度
                height: 120,     // 信息窗口高度
                title : "垃圾屋信息" , // 信息窗口标题
                enableMessage:true//设置允许信息窗发送短息
            };

            var IconOK = new BMap.Icon("img/marker1.png", new BMap.Size(25, 25), {
                imageSize:new BMap.Size(25,25)
            });

            var IconNo = new BMap.Icon("img/marker2.png", new BMap.Size(25, 25), {
                imageSize:new BMap.Size(25,25)
            });

            $(data).each(function(index,element){
                var content = "名称：" + element.hname;
                content += "<br>地址："+element.addr;
                content += "<br>状态：";
                var statu = 0;
                if (element.gas != 0){
                    content += "烟感异常 ";
                    statu = 1;
                }
                if (element.aq != 0){
                    content += " 空气质量异常";
                    statu = 1;
                }
                if (element.door != 0){
                    content += " 门锁异常";
                    statu = 1;
                }
                if (element.lamp != 0){
                    content += " 紫光灯异常";
                    statu = 1;
                }

                var capacities = new Array;
                capacities = element.capacity.split(',');
                for(var i = 0; i < capacities.length; i++){
                    if (capacities[i] > 85){
                        content += " 垃圾已满";
                        statu = 1;
                        break;
                    }
                }

                if (statu == 0){
                    content += "正常";
                }

                content += '<br><button onclick="house.getDetails('+element.id+')">查看详细</button>';

                var icon;
                if (statu == 1){
                    icon = IconNo;
                }else{
                    icon = IconOK;
                }
                var marker = new BMap.Marker(new BMap.Point(element.lng,element.lat), {icon:icon});  // 创建标注

                map.addOverlay(marker);               // 将标注添加到地图中
                addClickHandler(content,marker);
            })
            function addClickHandler(content,marker){
                marker.addEventListener("click",function(e){
                        openInfo(content,e)}
                );
            }
            function openInfo(content,e){
                var p = e.target;
                var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
                var infoWindow = new BMap.InfoWindow(content,opts);  // 创建信息窗口对象
                map.openInfoWindow(infoWindow,point); //开启信息窗口
            }

        }
    });
}

function initMap(){
    clearInterval(timerId);
    $('#content').html('<div class="content-wrapper" id="content_wrapper" style="width:100%; height: 800px"></div>')
    map = new BMap.Map("content_wrapper");
    var location;
    if ($('#selDistrict').val() != 0){
        location = $('#selDistrict').find("option:selected").text();
    }else if($('#selCity').val() != 0){
        location = $('#selProvince').find("option:selected").text() + $('#selCity').find("option:selected").text();;
    }else if($('#selProvince').val() != 0){
        location = $('#selProvince').find("option:selected").text();
    }else{
        location = "北京";
    }

    if(typeof data === 'string')
        data=$.parseJSON(data);

    map.centerAndZoom(location, 13);
    map.enableScrollWheelZoom();
    loadMap();
    timerId = setInterval(function(){loadMap()}, 5000);
}

var statistic = {
    getList : function(){
        clearInterval(timerId);
        var url = addr + "statistic/list.do"
        getContent(url);
        timerId = setInterval(function(){getContent(url)}, 5000);
    }
};
var house = {
    getHouseList : function(status){
        clearInterval(timerId);
        var url = addr + "house/list.do?status="+status;
        getContent(url);
        //timerId = setInterval(function(){getContent(url)}, 5000);
    },

    getDetails :function(houseId){
        clearInterval(timerId);
        var url = addr + "house/details.do?houseId="+houseId;
        getContent(url);
        timerId = setInterval(function(){getContent(url)}, 5000);
    },

    getFixHistory : function(houseId){
        clearInterval(timerId);
        var from = $('#time_from').val();
        var to = $('#time_to').val();
        if (from == undefined || to == undefined){
            from = getNowFormatDate();
            to = getNowFormatDate();
        }
        var url = addr + "house/fixHis.do?houseId="+houseId + "&from=" + from + "&to="+ to;
        getContent(url);
    },

    getAchieveHistory:function(houseId){
        clearInterval(timerId);
        var from = $('#time_from').val();
        var to = $('#time_to').val();
        if (from == undefined || to == undefined){
            from = getNowFormatDate();
            to = getNowFormatDate();
        }
        var url = addr + "house/achieveHis.do?houseId="+houseId + "&from=" + from + "&to="+ to;
        getContent(url);
        //timerId = setInterval(function(){getContent(url)}, 5000);
    },

    getOrderHistory:function(houseId){
        clearInterval(timerId);
        var from = $('#time_from').val();
        var to = $('#time_to').val();
        if (from == undefined || to == undefined){
            from = getNowFormatDate();
            to = getNowFormatDate();
        }

        var url = addr + "house/orderHis.do?houseId="+houseId + "&from=" + from + "&to="+ to;
        getContent(url);
    },

    getSortHistory:function(houseId){
        clearInterval(timerId);
        var from = $('#time_from').val();
        var to = $('#time_to').val();
        if (from == undefined || to == undefined){
            from = getNowFormatDate();
            to = getNowFormatDate();
        }
        var url = addr + "house/sortHis.do?houseId="+houseId+ "&from=" + from + "&to="+ to;
        getContent(url);
    },

    getPutHistory:function(houseId){
        clearInterval(timerId);
        var from = $('#time_from').val();
        var to = $('#time_to').val();
        if (from == undefined || to == undefined){
            from = getNowFormatDate();
            to = getNowFormatDate();
        }
        var url = addr + "house/putHis.do?houseId="+houseId+ "&from=" + from + "&to="+ to;
        getContent(url);
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
            headers : {
                province:$('#selProvince').val(),
                city:$('#selCity').val(),
                district:$('#selDistrict').val()
            },
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
        //timerId = setInterval(function(){getContent(url)}, 5000);
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
            headers : {
                province:$('#selProvince').val(),
                city:$('#selCity').val(),
                district:$('#selDistrict').val()
            },
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
            url : addr + "user/edit.do?" + param + "&role=" + role ,
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
        var from = $('#time_from').val();
        var to = $('#time_to').val();
        if (from == undefined || to == undefined){
            from = getNowFormatDate();
            to = getNowFormatDate();
        }

        var url;
        if (role == 3){
            url = addr + "user/orderHis.do?userId="+userId + "&role=" + role + "&from=" + from + "&to="+ to;
        }else if(role == 2){
            url = addr + "user/cleanHis.do?userId="+userId + "&role=" + role + "&from=" + from + "&to="+ to;
        }else if (role == 1){
            url = addr + "user/sortHis.do?userId="+userId + "&role=" + role + "&from=" + from + "&to="+ to;
        }
        getContent(url);
        //timerId = setInterval(function(){getContent(url)}, 5000);
    },

    getAllInfo : function(role){
        clearInterval(timerId);

        var url;
        if (role == 3){
            url = addr + "user/allRecover.do";
        }else if(role == 2){
            url = addr + "user/allClean.do";
        }else if (role == 1){
            url = addr + "user/allSort.do";
        }

        getContent(url);
        //timerId = setInterval(function(){getContent(url)}, 5000);
    }
}

var customer = {
    getCustomerList : function(statu){
        clearInterval(timerId);
        var url = addr + "customer/list.do?statu="+statu;
        getContent(url);
    },
    getDetails : function(customerId){
        clearInterval(timerId);
        var from = $('#time_from').val();
        var to = $('#time_to').val();
        if (from == undefined || to == undefined){
            from = getNowFormatDate();
            to = getNowFormatDate();
        }
        var url = addr + "customer/detail.do?customerId="+customerId + "&from=" + from + "&to="+ to;
        getContent(url);
    }
}

var mall ={
    getCommodityList : function(statu){
        clearInterval(timerId);
        var url = addr + "mall/list.do?statu="+statu;
        getContent(url);
    },
    toAdd_Commodity : function(){
        clearInterval(timerId);
        var url = addr + "mall/toAdd.do";
        getContent(url);
    },
    toEdit_Commodity : function (id) {
        clearInterval(timerId);
        var url = addr + "mall/toEdit.do?id="+id;
        getContent(url);
    }
}
