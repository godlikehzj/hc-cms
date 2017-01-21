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
            <h4 class="panel-title">${roleText}列表</h4>
          </div>
          <div class="panel-body">
            <div class="row">
              <div class="col-md-6 col-xs-12 ">
                <button id="create_house" onclick="user.toAdd_user(${role})" type="button" class="btn btn-success">新建</button>
                <select id="select_statu" style="margin-left: 20px" onchange="queryByStatus()">
                  <option value="1" <c:if test="${status==1}">selected="selected" </c:if>>正常</option>
                  <option value="0" <c:if test="${status==0}">selected="selected" </c:if>>冻结</option>
                </select>
              </div>
            </div>
            <table id="basic-datatables" class="table table-striped table-bordered" cellspacing="0" width="100%">
              <thead>
              <tr>
                <th>id</th>
                <th>电话</th>
                <th>姓名</th>
                <th>垃圾屋ID</th>
                <th>业绩</th>
                <th>操作</th>
              </tr>
              </thead>

              <tbody>
              <c:forEach var="user" items="${users}">
                <tr>
                  <td>${user.id}</td>
                  <td>${user.mobile}</td>
                  <td>${user.name}</td>
                  <td>${user.houseIds}</td>
                  <td>查看</td>
                  <td><a href="javascript:void(0);" onclick="changeStatu(${user.id}, ${status});" >${status==1?"冻结":"解冻"}</a> | <a href="javascript:void(0);" onclick="user.toEdit_user(${user.id}, ${role});" >修改</a> </td>
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
  function changeStatu(userId, status){
    $.ajax({
      type : "GET",
      url : addr + "user/changeStatu.do?" + "userId=" + userId + "&status=" + status,
      error : function() {
      },
      success : function(ret) {
        if (ret.status == 0){
          user.getUserList("${role}", status);
        } else{
          alert(ret.message);
        }
      }
    });
  }

  function queryByStatus(){
    var statu = $("#select_statu option:selected").val();
    user.getUserList("${role}", statu);
  }
</script>