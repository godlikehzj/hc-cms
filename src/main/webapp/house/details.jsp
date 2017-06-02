<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: godlikehzj
  Date: 2017/1/25
  Time: 下午4:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-lg-12">
  <!-- Start col-lg-12 -->
  <div class="panel panel-default">
    <!-- Start .panel -->
    <div class="panel-heading">
      <h3 class="panel-title">${houseInfo.hname} -> ${houseInfo.addr}</h3>
    </div>

    <div style="padding:15px 30px;">
      <button onclick="house.getFixHistory(${houseId})" type="button" class="btn btn-primary">报修历史</button>
      <button onclick="house.getAchieveHistory(${houseId})" type="button" class="btn btn-primary">清运历史</button>
      <button onclick="house.getOrderHistory(${houseId})" type="button" class="btn btn-primary">回收历史</button>
      <button onclick="house.getSortHistory(${houseId})" type="button" class="btn btn-primary">分拣历史</button>
      <button onclick="house.getPutHistory(${houseId})" type="button" class="btn btn-primary">投放历史</button>

      <button onclick="house.getHouseList(1)" type="button" class="btn btn-primary" style="float: right">返回</button>

    </div>
    <div class="panel-body">

      <div class="col-lg-4">
        <!-- col-lg-8 start here -->
        <div class="panel panel-primary">
          <!-- Start .panel -->
          <div class="panel-heading">
            <h4 class="panel-title">遥测数据</h4>
          </div>
          <div class="panel-body">
            <ul class="list-group">
              <li class="list-group-item list-group-item-success">温度：${houseInfo.temperature}摄氏度</li>
              <li class="list-group-item list-group-item-success">湿度：${houseInfo.humidity}%</li>
              <li class="list-group-item <c:choose>
                <c:when test="${houseInfo.gas == 1}">list-group-item-danger"</c:when>
                <c:when test="${houseInfo.gas == 0}">list-group-item-success"</c:when>
              </c:choose>>烟感：<c:choose>
                <c:when test="${houseInfo.gas == 1}">超标</c:when>
                <c:when test="${houseInfo.gas == 0}">正常</c:when>
              </c:choose>
              <li class="list-group-item <c:choose>
                <c:when test="${houseInfo.aq == 1}">list-group-item-danger"</c:when>
                  <c:when test="${houseInfo.aq == 0}">list-group-item-success"</c:when>
              </c:choose>>有害气体：<c:choose>
              <c:when test="${houseInfo.aq == 1}">超标</c:when>
              <c:when test="${houseInfo.aq == 0}">正常</c:when>
            </c:choose>
            </ul>
          </div>
        </div>
        <!-- End .panel -->
      </div>
      <div class="col-lg-8">
        <div class="panel panel-primary">
          <!-- Start .panel -->
          <div class="panel-heading">
            <h4 class="panel-title">缓冲箱</h4>
          </div>
          <div class="panel-body">
            <!-- col-lg- start here -->
            <!--<div class="panel panel-default toggle">-->
            <!-- Start .panel -->
            <!--<div class="panel-heading">-->
            <!--<h4 class="panel-title"><i class="im-pie"></i> Easy pie chart</h4>-->
            <!--</div>-->
            <!--<div class="panel-body">-->
            <c:forEach var="capacity" items="${capacitys}" varStatus="status">
              <div <c:choose>
                <c:when test="${capacity >= 85}">class="pie-charts red-pie"</c:when>
                <c:otherwise>class="pie-charts green-pie"</c:otherwise>
              </c:choose>>
                <div <c:choose>
                  <c:when test="${capacity >= 85}">class="easy-pie-chart-red"</c:when>
                  <c:otherwise>class="easy-pie-chart-green"</c:otherwise>
                </c:choose> data-percent="${capacity}">${capacity}%</div>
                <div class="label">${status.index + 1}号缓冲箱</div>
                <div><button><a href="#" onclick="alert('km')">开门</a></button></div>
              </div>
            </c:forEach>


          </div>
        </div>
      </div>

      <div class="col-lg-12">
        <!-- Start col-lg-6 -->
        <div class="panel panel-primary">
          <!-- Start .panel -->
          <div class="panel-heading">
            <h4 class="panel-title"><i class="im-bars"></i>用户最近投放重量</h4>
          </div>
          <input id="weight" value="${weightHistory}" style="display: none">
          <div class="panel-body">
            <div id="ordered-bars-chart" style="width: 100%; height:250px;"></div>
          </div>
        </div>
        <!-- End .panel -->
      </div>
      <div class="col-lg-12">
        <div class="panel panel-primary">
          <!-- Start .panel -->
          <div class="panel-heading">
            <h4 class="panel-title">资源柜</h4>
          </div>
          <div class="panel-body">
            <div class="col-lg-12">
              <h4>状态：<c:choose>
                <c:when test="${handleStatu ==1}">等待处理</c:when>
                <c:otherwise>空闲</c:otherwise>
              </c:choose></h4>
            </div>
            <table class="table table-bordered">
            <thead>
            <tr>
              <th class="per10">
                编号
              </th>
              <c:forEach var="zyg" items="${zygs}" varStatus="status">
                <th>${status.index + 1}</th>
              </c:forEach>
            </tr>
            </thead>
            <tbody>

            <tr>
              <td>外门</td>
              <c:forEach var="zyg" items="${zygs}" varStatus="status">
                <td><c:choose>
                  <c:when test="${zyg.outdoor == 1}">关闭</c:when>
                  <c:when test="${zyg.outdoor == 0}">打开</c:when>
                  <c:otherwise>未知</c:otherwise>
                </c:choose></td>
              </c:forEach>

            </tr>

            <tr>
              <td>状态</td>
              <c:forEach var="zyg" items="${zygs}" varStatus="status">
                <td><c:choose>
                  <c:when test="${zyg.tank == 0}">未满</c:when>
                  <c:when test="${zyg.tank == 1}">已满</c:when>
                  <c:when test="${zyg.tank == 2}">桶丢失</c:when>
                  <c:otherwise>未知</c:otherwise>
                </c:choose></td>
              </c:forEach>

            </tr>

            <tr>
              <td>重量</td>
              <c:forEach var="zyg" items="${zygs}" varStatus="status">
                <td><c:choose>
                  <c:when test="${zyg.weight >= 0}">${zyg.weight}</c:when>
                  <c:otherwise>未知</c:otherwise>
                  </c:choose></td>
              </c:forEach>

            </tr>

            <tr>
              <td>操作</td>
              <c:forEach var="zyg" items="${zygs}" varStatus="status">
                <td><a href="#" onclick="alert('km')">开门</a></td>
              </c:forEach>

            </tr>
            </tbody>
          </table>
          </div>
        </div>
      </div>

          </div>
        </div>
      </div>

      <!-- End .panel -->

    </div>
  </div>
</div>

<script>

    var objColors = $('body').data('sprFlat').getColors();
    var colours = {
      white: objColors.white,
      dark: objColors.dark,
      red: objColors.red,
      blue: objColors.blue,
      green: objColors.green,
      yellow: objColors.yellow,
      brown: objColors.brown,
      orange: objColors.orange,
      purple: objColors.purple,
      pink: objColors.pink,
      lime: objColors.lime,
      magenta: objColors.magenta,
      teal: objColors.teal,
      textcolor: '#5a5e63',
      gray: objColors.gray
    }

    $(".easy-pie-chart-red").easyPieChart({
      barColor: colours.red,
      borderColor: colours.red,
      trackColor: '#fbccbf',
      scaleColor: false,
      lineCap: 'butt',
      lineWidth: 20,
      size: 100,
      animate: 1500
    });
    $(".easy-pie-chart-green").easyPieChart({
      barColor: colours.green,
      borderColor: colours.green,
      trackColor: '#b1f8b1',
      scaleColor: false,
      lineCap: 'butt',
      lineWidth: 20,
      size: 100,
      animate: 1500
    });

    var d1 = [];

    var weight =new Array();
    if ($('#weight').val().length != 0){
      weight = $('#weight').val().split(',');
      for(var i = 0; i < weight.length; i++){
        if (i < 10){
          d1.push([i, weight[i]])
        }
      }
    }

    for (var i = d1.length; i <= 10; i += 1){
      d1.push([i]);
    }

    var ds = new Array();

    ds.push({
      label: "单位：公斤",
      data: d1,
      bars: {order: 0}
    });

    var stack = 0, bars = false, lines = false, steps = false;

    var options = {
      bars: {
        show: true,
        barWidth: 0.5,
        fill: 1
      },
      grid: {
        show: true,
        aboveData: false,
        color: colours.textcolor,
        labelMargin: 5,
        axisMargin: 0,
        borderWidth: 0,
        borderColor: null,
        minBorderMargin: 5,
        clickable: true,
        hoverable: true,
        autoHighlight: false,
        mouseActiveRadius: 20
      },
      series: {
        stack: stack
      },
      legend: {position: "ne"},
      colors: [colours.green],
      tooltip: false, //activate tooltip
      tooltipOpts: {
        content: "%s : %y.0",
        shifts: {
          x: -30,
          y: -50
        }
      }
    };

    $.plot($("#ordered-bars-chart"), ds, options);

</script>

