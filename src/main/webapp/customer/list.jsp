<%@ include file="../common/taglibs.jsp"%>
<%--
  Created by IntelliJ IDEA.
  User: godlikehzj
  Date: 2017/1/12
  Time: 下午11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="content-wrapper">
  <div class="col-lg-12 heading">
    <%--<ul id="crumb" class="breadcrumb">--%>
    <%--</ul>--%>
  </div>
  <div class="outlet">
    <div class="row">
      <div class="col-lg-12">
        <!-- col-lg-12 start here -->
        <div class="panel panel-default plain toggle panelClose panelRefresh">
          <!-- Start .panel -->
          <div class="panel-heading white-bg">
            <h4 class="panel-title">居民用户列表</h4>
          </div>
          <div class="panel-body">
            <div class="row">
              <div class="col-md-6 col-xs-12 ">
                <select id="select_statu" style="margin-left: 20px" onchange="queryByStatus()">
                  <option value="1" <c:if test="${status==1}">selected="selected" </c:if>>正常</option>
                  <option value="0" <c:if test="${status==0}">selected="selected" </c:if>>冻结</option>
                </select>
              </div>
            </div>
            <table id="basic-datatables" class="table table-striped table-bordered" cellspacing="0" width="100%">
              <thead>
              <tr>
                <th>昵称</th>
                <th>手机</th>
                <th>积分</th>
                <th>详细信息</th>
              </tr>
              </thead>

              <tbody>
              <c:forEach var="customer" items="${customers}">
                <tr>
                  <td>${customer.nick}</td>
                  <td>${customer.mobile}</td>
                  <td>${customer.point}</td>
                  <td><a href="javascript:void(0);" onclick="changeStatu(${customer.id}, ${status});" >${status==1?"冻结":"解冻"}</a> |
                    <a href="javascript:void(0);" onclick="customer.getDetails(${customer.id})">查看</a></td>
                </tr>
              </c:forEach>

              </tbody>
            </table>
          </div>
        </div>
        <!-- End .panel -->
      </div>
    </div>
  </div>
</div>
<script src="${ctx}/assets/plugins/tables/datatables/jquery.dataTables.js" tppabs="http://themes.suggelab.com/sprflat.v1.0.4/assets/plugins/tables/datatables/jquery.dataTables.js"></script>
<script src="${ctx}/assets/plugins/tables/datatables/dataTables.tableTools.js" tppabs="http://themes.suggelab.com/sprflat.v1.0.4/assets/plugins/tables/datatables/dataTables.tableTools.js"></script>
<script src="${ctx}/assets/plugins/tables/datatables/dataTables.bootstrap.js" tppabs="http://themes.suggelab.com/sprflat.v1.0.4/assets/plugins/tables/datatables/dataTables.bootstrap.js"></script>
<script src="${ctx}/assets/plugins/tables/datatables/dataTables.responsive.js" tppabs="http://themes.suggelab.com/sprflat.v1.0.4/assets/plugins/tables/datatables/dataTables.responsive.js"></script>
<script type="text/javascript">
  $('#basic-datatables').dataTable({
    "oLanguage": {
      "sSearch": "",
      "sLengthMenu": "<span>_MENU_</span>"
    },
    "sort": false,
    "sDom": "<'row'<'col-md-6 col-xs-12 'l><'col-md-6 col-xs-12'f>r>t<'row'<'col-md-4 col-xs-12'i><'col-md-8 col-xs-12'p>>"
  });

  function changeStatu(customerId, status){
    $.ajax({
      type : "GET",
      url : addr + "customer/changeStatu.do?" + "customerId=" + customerId + "&status=" + status,
      error : function() {
      },
      success : function(ret) {
        if (ret.status == 0){
          customer.getCustomerList(status);
        } else{
          alert(ret.message);
        }
      }
    });
  }

  function queryByStatus(){
    var statu = $("#select_statu option:selected").val();
    customer.getCustomerList(statu);
  }
</script>