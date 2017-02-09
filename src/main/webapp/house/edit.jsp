<%@ include file="../common/taglibs.jsp"%>
<%--
  Created by IntelliJ IDEA.
  User: godlikehzj
  Date: 2017/1/14
  Time: 上午1:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="outlet">
  <!-- Start .outlet -->
  <!-- Page start here ( usual with .row ) -->
  <div class="row">
    <!-- Start .row -->
    <div class="col-lg-12">
      <!-- Start col-lg-12 -->
      <div class="panel panel-default toggle">
        <!-- Start .panel -->
        <div class="panel-heading">
          <h3 class="panel-title">修改垃圾屋</h3>
        </div>
        <div class="panel-body">
          <form id="create_house" name="create_house" class="form-horizontal group-border hover-stripped" role="form">
            <div class="form-group">
              <label class="col-lg-2 col-md-2 col-sm-12 control-label">名称</label>
              <div class="col-lg-10 col-md-10">
                <input type="text" class="form-control" id = "hname" name="hname" value="${houseInfo.hname}" autofocus="autofocus">
              </div>
            </div>
            <div class="form-group">
              <label class="col-lg-2 col-md-2 col-sm-12 control-label">描述</label>
              <div class="col-lg-10 col-md-10">
                <input type="text" class="form-control" id = "description" name="description" placeholder="" autofocus="autofocus">
              </div>
            </div>
            <div class="form-group">

              <label class="col-lg-2 col-md-2 col-sm-12 control-label">地理位置</label>
              <div class="col-lg-10 col-md-10">
                <input id="addr" type="text" class="form-control" name="addr" value="${houseInfo.addr}" autofocus="autofocus">
                <button id="search" type="button" onclick="searchByStationName()" class="btn btn-success control-label">查找</button>
                <input id = "location" type="text" value="${houseInfo.lng},${houseInfo.lat}" name="location">
              </div>
            </div>
            <div class="form-group">
              <div class="col-lg-2 col-md-2 col-sm-12 control-label ">
                <button id="create_house_button" type="button" onclick="editHouse(${houseInfo.id})" class="btn btn-success">提交</button>

              </div>
            </div>
          </form>
          <div id="container"
               style="
                                    margin-top:30px;
                                    width: 730px;
                                    height: 590px;
                                    top: 50px;
                                    border: 1px solid gray;
                                    overflow:hidden;">
          </div>
        </div>
      </div>
    </div>
    <!-- End col-lg-12 -->
  </div>
</div>




<script type="text/javascript">
  function editHouse(id){
    var hname = $('#hname').val();
    var despcription = $('#despcription').val();
    var addr = $('#addr').val();
    var location = $('#location').val();

    var param = "hname=" + hname + "&addr=" + addr + "&location=" + location + "&id=" + id;
    house.edit_house(param);
  }
  //        $('#create_house_button').bind('click', function () {
  //
  //
  ////            $("#create_house").submit()
  //        });

  //        var options = {
  //            success: getHouseList(1)
  //        };
  //        $('#create_house').ajaxForm(options);
  var map = new BMap.Map("container");
  map.centerAndZoom("北京", 12);
  map.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
  map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用

  map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
  map.addControl(new BMap.OverviewMapControl()); //添加默认缩略地图控件
  map.addControl(new BMap.OverviewMapControl({isOpen: true, anchor: BMAP_ANCHOR_BOTTOM_RIGHT}));   //右下角，打开

  var localSearch = new BMap.LocalSearch(map);
  localSearch.enableAutoViewport(); //允许自动调节窗体大小
  function searchByStationName() {
    map.clearOverlays();//清空原来的标注
    var keyword = document.getElementById("addr").value;
    localSearch.setSearchCompleteCallback(function (searchResult) {
      var poi = searchResult.getPoi(0);
      document.getElementById("location").value = poi.point.lng + "," + poi.point.lat;
      map.centerAndZoom(poi.point, 13);
      var marker = new BMap.Marker(new BMap.Point(poi.point.lng, poi.point.lat));  // 创建标注，为要查询的地方对应的经纬度
      map.addOverlay(marker);
      var content = document.getElementById("addr").value + "<br/><br/>经度：" + poi.point.lng + "<br/>纬度：" + poi.point.lat;
      var infoWindow = new BMap.InfoWindow("<p style='font-size:14px;'>" + content + "</p>");
      marker.addEventListener("click", function () {
        this.openInfoWindow(infoWindow);
      });
      // marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
    });
    localSearch.search(keyword);

  }

</script>
