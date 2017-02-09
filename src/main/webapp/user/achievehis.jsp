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
      <h3 class="panel-title"><c:choose>
          <c:when test="${role == 2}">清运业绩</c:when>
          <c:when test="${role == 1}">分拣业绩</c:when>
        </c:choose></h3>
    </div>


    <div class="panel-body">

      <c:if test="${role == 2}">
        <div class="col-lg-12">
          <!-- col-lg-8 start here -->
          <div class="panel panel-primary">
            <!-- Start .panel -->
            <div class="panel-heading">
              <h4 class="panel-title">清运在途</h4>
            </div>
            <table id="qingyun_online" class="table table-bordered table-striped" cellspacing="0" width="100%">
              <thead>
              <tr>
                <th class="per10">电话</th>
                <th class="per10">清运员</th>
                <th class="per10">倒计时</th>
              </tr>
              </thead>
              <tbody>

              <tr>
                <td>${handle.mobile}</td>
                <td>${handle.name}</td>
                <td id="handle_time"></td>
              </tr>

              </tbody>
            </table>
          </div>
          <!-- End .panel -->
        </div>
      </c:if>
      <div class="col-lg-12">
        <!-- col-lg-8 start here -->
        <div class="panel panel-primary">
          <!-- Start .panel -->
          <div class="panel-heading">
            <h4 class="panel-title"><c:choose>
              <c:when test="${role == 2}">清运历史</c:when>
              <c:when test="${role == 1}">分拣历史</c:when>
            </c:choose></h4>
          </div>
          <table id="basic-datatables" class="table table-bordered table-striped" cellspacing="0" width="100%">
            <thead>
            <tr>
              <th class="per15">时间</th>
              <th class="per10">电话</th>
              <th class="per10">清运员</th>
              <th class="per10">垃圾屋</th>
              <th class="per10">次数</th>
              <th class="per20">重量</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="achieve" items="${achieveHistoryList}">
              <tr>
                <td><fmt:formatDate value="${achieve.createDate}" pattern="yyyy.MM.dd HH.mm" /> </td>
                <td>${user.mobile}</td>
                <td>${user.name}</td>
                <td>${achieve.hname}</td>
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
      <button onclick="user.getUserList(${role}, 1)" type="button" class="btn btn-primary">返回</button>

    </div>
  </div>
</div>
<script src="../assets/plugins/tables/datatables/jquery.dataTables.js" tppabs="http://themes.suggelab.com/sprflat.v1.0.4/assets/plugins/tables/datatables/jquery.dataTables.js"></script>
<script src="../assets/plugins/tables/datatables/dataTables.tableTools.js" tppabs="http://themes.suggelab.com/sprflat.v1.0.4/assets/plugins/tables/datatables/dataTables.tableTools.js"></script>
<script src="../assets/plugins/tables/datatables/dataTables.bootstrap.js" tppabs="http://themes.suggelab.com/sprflat.v1.0.4/assets/plugins/tables/datatables/dataTables.bootstrap.js"></script>
<script src="../assets/plugins/tables/datatables/dataTables.responsive.js" tppabs="http://themes.suggelab.com/sprflat.v1.0.4/assets/plugins/tables/datatables/dataTables.responsive.js"></script>
<%--<script src="../assets/js/pages/data-tables.js" tppabs="http://themes.suggelab.com/sprflat.v1.0.4/assets/js/pages/data-tables.js"></script>--%>
<script>
  $('#basic-datatables').dataTable({
    "oLanguage": {
      "sSearch": "",
      "sLengthMenu": "<span>_MENU_</span>"
    },
    "sDom": "<'row'<'col-md-6 col-xs-12 'l><'col-md-6 col-xs-12'f>r>t<'row'<'col-md-4 col-xs-12'i><'col-md-8 col-xs-12'p>>"
  });
</script>

