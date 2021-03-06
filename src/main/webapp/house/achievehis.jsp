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
      <h3 class="panel-title">${houseInfo.hname} -> 清运信息</h3>
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

        <button onclick="house.getAchieveHistory(${houseId})" type="button" class="btn btn-primary">查询</button>

      </div>
      <div class="col-lg-12">
        <!-- col-lg-8 start here -->
        <div class="panel panel-primary">
          <!-- Start .panel -->
          <div class="panel-heading">
            <h4 class="panel-title">清运历史</h4>
          </div>
          <table id="basic-datatables" class="table table-bordered table-striped" cellspacing="0" width="100%">
            <thead>
            <tr>
              <th class="per15">时间</th>
              <th class="per10">电话</th>
              <th class="per10">清运员</th>
              <th class="per10">次数</th>
              <th class="per20">重量</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="achieve" items="${achieveHistoryList}">
              <tr>
                <td><fmt:formatDate value="${achieve.createDate}" pattern="yyyy.MM.dd HH.mm" /> </td>
                <td>${achieve.mobile}</td>
                <td>${achieve.name}</td>
                <td>${achieve.num}</td>
                <td>${achieve.weight/1000}公斤</td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>
        <!-- End .panel -->
      </div>

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
</script>

