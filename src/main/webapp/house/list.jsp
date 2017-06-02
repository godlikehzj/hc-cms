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
            <h4 class="panel-title">垃圾屋列表</h4>
          </div>
          <div class="panel-body">
            <div class="row">
              <div class="col-md-6 col-xs-12 ">
                <button id="create_house" onclick="house.toAdd_house()" type="button" class="btn btn-success">新建</button>
                <select id="select_statu" style="margin-left: 20px" onchange="queryByStatus()">
                  <option value="1" <c:if test="${status==1}">selected="selected" </c:if>>上线</option>
                  <option value="0" <c:if test="${status==0}">selected="selected" </c:if>>下线</option>
                </select>
              </div>
            </div>
            <table id="basic-datatables" class="table table-striped table-bordered" cellspacing="0" width="100%">
              <thead>
              <tr>
                <th>id</th>
                <th>名称</th>
                <th>温度</th>
                <th>湿度</th>
                <th>烟感</th>
                <th>有害气体</th>
                <th>容量</th>
                <th>操作</th>
              </tr>
              </thead>

              <tbody>
              <c:forEach var="house" items="${houseLists}">
                <tr>
                  <td>${house.id}</td>
                  <td>${house.hname}</td>
                  <td>${house.temperature}度</td>
                  <td>${house.humidity}%</td>
                  <td><c:choose>
                    <c:when test="${house.gas == 1}">超标</c:when>
                    <c:when test="${house.gas == 0}">正常</c:when>
                  </c:choose></td>
                  <td><c:choose>
                    <c:when test="${house.aq == 1}">超标</c:when>
                    <c:when test="${house.aq == 0}">正常</c:when>
                  </c:choose></td>
                  <td>${house.capacity}</td>
                  <td><a href="javascript:void(0);" onclick="changeStatu(${house.id}, ${status});" >${status==1?"下线":"上线"}</a> |
                    <a href="javascript:void(0);" onclick="house.toEdit_house(${house.id});" >修改</a> |
                    <a href="javascript:void(0);" onclick="house.getDetails(${house.id})">查看</a></td>
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
  function changeStatu(houseId, statu){
    $.ajax({
      type : "GET",
      url : addr + "house/changeStatu.do?" + "houseId=" + houseId + "&statu=" + statu,
      error : function() {
      },
      success : function(ret) {
        if (ret.status == 0){
          house.getHouseList(statu)
        } else{
          alert(ret.message);
        }
      }
    });
  }

  function getStatuText(){
    if (statu == 1){
      return "下线";
    }else if (statu = 0){
      return "上线";
    }
  }
  function queryByStatus(){
    var statu = $("#select_statu option:selected").val();
    house.getHouseList(statu);
  }
  </script>