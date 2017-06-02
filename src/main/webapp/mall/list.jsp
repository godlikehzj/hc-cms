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
            <h4 class="panel-title">商品列表</h4>
          </div>
          <div class="panel-body">
            <div class="row">
              <div class="col-md-6 col-xs-12 ">
                <button id="create_commodity" onclick="mall.toAdd_Commodity()" type="button" class="btn btn-success">新建</button>

                <select id="select_statu" style="margin-left: 20px" onchange="queryByStatus()">
                  <option value="1" <c:if test="${status==1}">selected="selected" </c:if>>正常</option>
                  <option value="0" <c:if test="${status==0}">selected="selected" </c:if>>下线</option>
                </select>
              </div>
            </div>
            <table id="basic-datatables" class="table table-striped table-bordered" cellspacing="0" width="100%">
              <thead>
              <tr>
                <th>名称</th>
                <th>描述</th>
                <th>消耗积分</th>
                <th>图片</th>
                <th>操作</th>
              </tr>
              </thead>

              <tbody>
              <c:forEach var="commodity" items="${commodities}">
                <tr>
                  <td>${commodity.name}</td>
                  <td>${commodity.description}</td>
                  <td>${commodity.cost_point}</td>
                  <td><a href="${commodity.img}" target="view_window">点击查看</a></td>
                  <td><a href="javascript:void(0);" onclick="changeStatu(${commodity.id}, ${status});" >${status==1?"下线":"上线"}</a></td>
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

<script type="text/javascript">
  $('#basic-datatables').dataTable({
    "oLanguage": {
      "sSearch": "",
      "sLengthMenu": "<span>_MENU_</span>"
    },
    "sort": false,
    "sDom": "<'row'<'col-md-6 col-xs-12 'l><'col-md-6 col-xs-12'f>r>t<'row'<'col-md-4 col-xs-12'i><'col-md-8 col-xs-12'p>>"
  });

  function changeStatu(commodityId, status){
    $.ajax({
      type : "GET",
      url : addr + "mall/changeStatu.do?" + "commodityId=" + commodityId + "&status=" + status,
      error : function() {
      },
      success : function(ret) {
        if (ret.status == 0){
          mall.getCommodityList(status);
        } else{
          alert(ret.message);
        }
      }
    });
  }

  function queryByStatus(){
    var statu = $("#select_statu option:selected").val();
    mall.getCommodityList(statu);
  }
</script>