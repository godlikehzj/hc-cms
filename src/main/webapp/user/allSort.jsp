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
      <h3 class="panel-title">全部分拣信息</h3>
    </div>

    <div class="panel-body">

      <div class="col-lg-12">
        <!-- col-lg-8 start here -->
        <div class="panel panel-primary">
          <!-- Start .panel -->
          <div class="panel-heading">
            <h4 class="panel-title">全部分拣操作</h4>
          </div>
          <table id="basic-datatables" class="table table-bordered table-striped" cellspacing="0" width="100%">
            <thead>
            <tr>
              <th class="per15">时间</th>
              <th class="per10">分拣员</th>
              <th class="per10">垃圾屋</th>
              <th class="per10">类型</th>
              <th class="per20">重量</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="sort" items="${allSortHistory}">
              <tr>
                <td><fmt:formatDate value="${sort.createTime}" pattern="yyyy.MM.dd HH.mm" /> </td>
                <td>${sort.user}</td>
                <td>${sort.hname}</td>
                <td><c:choose>
                  <c:when test="${sort.category == 0}">投入重量</c:when>
                  <c:when test="${sort.category == 1}">干垃圾</c:when>
                  <c:when test="${sort.category == 2}">湿垃圾</c:when>
                  <c:when test="${sort.category == 3}">玻璃</c:when>
                  <c:when test="${sort.category == 4}">金属</c:when>
                  <c:when test="${sort.category == 5}">纸张</c:when>
                  <c:when test="${sort.category == 6}">塑料</c:when>
                  <c:when test="${sort.category == 7}">衣物</c:when>
                </c:choose></td>
                <td>${sort.weight/1000}公斤</td>
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
<script src="${ctx}/assets/plugins/tables/datatables/jquery.dataTables.js" tppabs="http://themes.suggelab.com/sprflat.v1.0.4/assets/plugins/tables/datatables/jquery.dataTables.js"></script>
<script src="${ctx}/assets/plugins/tables/datatables/dataTables.tableTools.js" tppabs="http://themes.suggelab.com/sprflat.v1.0.4/assets/plugins/tables/datatables/dataTables.tableTools.js"></script>
<script src="${ctx}/assets/plugins/tables/datatables/dataTables.bootstrap.js" tppabs="http://themes.suggelab.com/sprflat.v1.0.4/assets/plugins/tables/datatables/dataTables.bootstrap.js"></script>
<script src="${ctx}/assets/plugins/tables/datatables/dataTables.responsive.js" tppabs="http://themes.suggelab.com/sprflat.v1.0.4/assets/plugins/tables/datatables/dataTables.responsive.js"></script>
<%--<script src="../assets/js/pages/data-tables.js" tppabs="http://themes.suggelab.com/sprflat.v1.0.4/assets/js/pages/data-tables.js"></script>--%>
<script>
  $('#basic-datatables').dataTable({
    "oLanguage": {
      "sSearch": "",
      "sLengthMenu": "<span>_MENU_</span>"
    },
    "sort": false,
    "sDom": "<'row'<'col-md-6 col-xs-12 'l><'col-md-6 col-xs-12'f>r>t<'row'<'col-md-4 col-xs-12'i><'col-md-8 col-xs-12'p>>"
  });

</script>

