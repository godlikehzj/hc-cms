<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../common/taglibs.jsp"%>

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
      <h3 class="panel-title">${houseInfo.hname} -> 投放信息</h3>
    </div>


    <div class="panel-body">
      <div class="controls-row form-group">
        <div class="control-group col col-md-4">
          <label for="time_from" class="col-md-6 control-label">开始日期</label>
          <div class="input-group date form_date col-md-6" data-date="" data-date-format="yyyy-mm-dd" data-link-field="time_from" data-link-format="yyyy-mm-dd">
            <input class="form-control" size="16" type="text" value="${from}" readonly>
            <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
          </div>
          <input type="hidden" id="time_from" value="${from}" />
        </div>

        <div class="control-group col col-md-4">
          <label for="time_to" class="col-md-6 control-label">结束日期</label>
          <div class="input-group date form_date col-md-6" data-date="" data-date-format="yyyy-mm-dd" data-link-field="time_to" data-link-format="yyyy-mm-dd">
            <input class="form-control" size="16" type="text" value="${to}" readonly>
            <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
          </div>
          <input type="hidden" id="time_to" value="${to}"/>
        </div>

        <button onclick="house.getPutHistory(${houseId})" type="button" class="btn btn-primary">查询</button>

      </div>
      <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
        <!-- col-lg-3 start here -->
        <div class="tile lime">
          <!-- tile start here -->
          <div class="tile-icon">
            <i class="ec-download s64"></i>
          </div>
          <div class="tile-content">
            <div class="number">${times}</div>
            <h3>投放次数</h3>
          </div>
        </div>
        <!-- tile end here -->
      </div>

      <div class="col-lg-6 col-md-6">
        <!-- Start col-lg-6 -->
        <div class="panel panel-default toggle">
          <!-- Start .panel -->
          <div class="panel-heading">
            <h4 class="panel-title"><i class="im-pie"></i>投放概述</h4>
          </div>
          <div class="panel-body">
            <div id="pie-chart" style="width: 100%; height:250px;"></div>
          </div>
        </div>
        <!-- End .panel -->
      </div>

      <div class="col-lg-12">
        <!-- col-lg-8 start here -->
        <div class="panel panel-primary">
          <!-- Start .panel -->
          <div class="panel-heading">
            <h4 class="panel-title">投放详情</h4>
          </div>
          <table id="basic-datatables" class="table table-bordered table-striped" cellspacing="0" width="100%">
            <thead>
            <tr>
              <th class="per10">时间</th>
              <th class="per10">居民用户</th>
              <th class="per10">垃圾屋</th>
              <th class="per10">类型</th>
              <th class="per10">重量</th>
              <th class="per10">查看图片</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="weight" items="${weights}">
              <tr>
                <td><fmt:formatDate value="${weight.createTime}" pattern="yyyy.MM.dd HH.mm" /> </td>
                <td>${weight.nick}</td>
                <td>${weight.hname}</td>
                <td><c:choose>
                  <c:when test="${weight.category == 0}">总重量</c:when>
                  <c:when test="${weight.category == 1}">干垃圾</c:when>
                  <c:when test="${weight.category == 2}">湿垃圾</c:when>
                </c:choose></td>
                <td>${weight.weight}</td>
                <td>
                  <c:choose>
                    <c:when test="${weight.category == 0}">-</c:when>
                    <c:otherwise><a href="${weight.url}" target="view_window">点击查看</a></c:otherwise>
                  </c:choose>
                </td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>
        <!-- End .panel -->
      </div>

      <%--<div class="col-lg-12">--%>
        <%--<!-- col-lg-8 start here -->--%>
        <%--<div class="panel panel-primary">--%>
          <%--<!-- Start .panel -->--%>
          <%--<div class="panel-heading">--%>
            <%--<h4 class="panel-title">投放历史</h4>--%>
          <%--</div>--%>
          <%--<table id="basic-datatables2" class="table table-bordered table-striped" cellspacing="0" width="100%">--%>
            <%--<thead>--%>
            <%--<tr>--%>
              <%--<th class="per10">时间</th>--%>
              <%--<th class="per10">用户</th>--%>
              <%--<th class="per10">门号</th>--%>
            <%--</tr>--%>
            <%--</thead>--%>
            <%--<tbody>--%>
            <%--<c:forEach var="action" items="${actions}">--%>
              <%--<tr>--%>
                <%--<td><fmt:formatDate value="${action.createTime}" pattern="yyyy.MM.dd HH.mm.ss" /> </td>--%>
                <%--<td>${action.nick}</td>--%>
                <%--<td>${action.doorId}</td>--%>
              <%--</tr>--%>
            <%--</c:forEach>--%>
            <%--</tbody>--%>
          <%--</table>--%>
        <%--</div>--%>
        <%--<!-- End .panel -->--%>
      <%--</div>--%>

    </div>
    <div style="padding:15px 30px;">
      <button onclick="house.getDetails(${houseId})" type="button" class="btn btn-primary">返回</button>

    </div>
  </div>
</div>
<script src="${ctx}/assets/plugins/tables/datatables/jquery.dataTables.js" tppabs="http://themes.suggelab.com/sprflat.v1.0.4/assets/plugins/tables/datatables/jquery.dataTables.js"></script>
<script src="${ctx}/assets/plugins/tables/datatables/dataTables.tableTools.js" tppabs="http://themes.suggelab.com/sprflat.v1.0.4/assets/plugins/tables/datatables/dataTables.tableTools.js"></script>
<script src="${ctx}/assets/plugins/tables/datatables/dataTables.bootstrap.js" tppabs="http://themes.suggelab.com/sprflat.v1.0.4/assets/plugins/tables/datatables/dataTables.bootstrap.js"></script>
<script src="${ctx}/assets/plugins/tables/datatables/dataTables.responsive.js" tppabs="http://themes.suggelab.com/sprflat.v1.0.4/assets/plugins/tables/datatables/dataTables.responsive.js"></script>
<%--<script src="../assets/js/pages/data-tables.js" tppabs="http://themes.suggelab.com/sprflat.v1.0.4/assets/js/pages/data-tables.js"></script>--%>
<script>
  $('.form_date').datetimepicker({
    language:  'cn',
    weekStart: 1,
    todayBtn:  1,
    autoclose: 1,
    todayHighlight: 1,
    startView: 2,
    minView: 2,
    forceParse: 0
  });


  $('#basic-datatables').dataTable({
    "oLanguage": {
      "sSearch": "",
      "sLengthMenu": "<span>_MENU_</span>"
    },
    "sort": false,
    "sDom": "<'row'<'col-md-6 col-xs-12 'l><'col-md-6 col-xs-12'f>r>t<'row'<'col-md-4 col-xs-12'i><'col-md-8 col-xs-12'p>>"
  });

  var objColors = $('body').data('sprFlat').getColors();
  var colours = {
    white: objColors.white,
    dark: objColors.dark,
    red : objColors.red,
    blue: objColors.blue,
    green : objColors.green,
    yellow: objColors.yellow,
    brown: objColors.brown,
    orange : objColors.orange,
    purple : objColors.purple,
    pink : objColors.pink,
    lime : objColors.lime,
    magenta: objColors.magenta,
    teal: objColors.teal,
    textcolor: '#5a5e63',
    gray: objColors.gray
  }
  var options = {
    series: {
      pie: {
        show: true,
        innerRadius: 0,
        radius: 'auto',
        highlight: {
          opacity: 0.1
        },
        stroke: {
          width: 2,
        }
      }
    },
    legend:{
      show:true,
      labelFormatter: function(label, series) {
        return '<div style="font-weight:bold;font-size:13px;">'+ label +'</div>'
      },
      labelBoxBorderColor: null,
      margin: 50,
      width: 20,
      padding: 1
    },
    grid: {
      hoverable: true,
      clickable: true,
    },
    tooltip: true, //activate tooltip
    tooltipOpts: {
      content: "%s : %y.1"+"%",
      shifts: {
        x: -30,
        y: -50
      },
      theme: 'dark',
      defaultTheme: false
    }
  };
  var dry = ${dry};
  var wet = ${wet};
  var total = ${total};
  var data = [
    { label: "总重量:" + total + "克",  data: dry*100/total, color: colours.blue},
    { label: "干垃圾:" + dry + "克",  data: dry*100/total, color: colours.blue},
    { label: "湿垃圾:" + wet + "克",  data: wet*100/total, color: colours.green},
    { label: "减量:" + (total - wet - dry) + "克",  data: (total - wet - dry)*100/total, color: colours.red}
  ];
  $.plot($("#pie-chart"), data, options);

  var initPieChart = function(lineWidth, size, animateTime, colours) {
    $(".pie-chart").easyPieChart({
      barColor: colours.dark,
      borderColor: colours.dark,
      trackColor: '#d9dde2',
      scaleColor: false,
      lineCap: 'butt',
      lineWidth: lineWidth,
      size: size,
      animate: animateTime
    });
  };

  initPieChart(10,40, 1500, colours);
</script>

