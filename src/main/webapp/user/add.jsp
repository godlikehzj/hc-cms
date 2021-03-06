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
          <h3 class="panel-title">创建${roleText}</h3>
        </div>
        <div class="panel-body">
          <form id="create_fj" name="create_fj" class="form-horizontal group-border hover-stripped" role="form">
            <div class="form-group">
              <label class="col-lg-2 col-md-2 col-sm-12 control-label">手机</label>
              <div class="col-lg-10 col-md-10">
                <input type="text" class="form-control" id = "mobile" name="mobile" placeholder="" autofocus="autofocus">
              </div>
            </div>
            <div class="form-group">
              <label class="col-lg-2 col-md-2 col-sm-12 control-label">姓名</label>
              <div class="col-lg-10 col-md-10">
                <input type="text" class="form-control" id = "name" name="name" placeholder="" autofocus="autofocus">
              </div>
            </div>
            <div class="form-group">

              <label class="col-lg-2 col-md-2 col-sm-12 control-label">垃圾屋列表</label>
              <div class="col-lg-10 col-md-10">
                <%--<input id="houseIds" type="text" class="form-control" name="houseIds" placeholder="" autofocus="autofocus">--%>
                <%--<a href="#myModal" role="button" class="btn btn-alt btn-danger mb25" data-toggle="modal">选择</a>--%>
                  <select id="houseList" multiple="multiple">
                    <c:forEach var="house" items="${houseList}">
                      <option value="${house.id}">${house.hname}</option>
                    </c:forEach>
                  </select>
              </div>
            </div>
            <div class="form-group">
              <div class="col-lg-2 col-md-2 col-sm-12 control-label ">
                <button id="create_user_button" type="button" onclick="addUser()" class="btn btn-success">提交</button>
                <button onclick="user.getUserList(${role}, 1)" type="button" class="btn btn-primary">返回</button>

              </div>
            </div>
          </form>

        </div>
      </div>
    </div>
    <!-- End col-lg-12 -->
  </div>
</div>



<script type="text/javascript">
  var houseIds="";
  $('#houseList').multiselect(
          {
            includeSelectAllOption: true,
            selectAllText: '全部选择',
            enableFiltering: true,
            buttonWidth: '100%',
            buttonText: function (options, select) {
              if (options.length === 0) {
                return '请选择垃圾屋';
              }
              else {
                var labels = [];
                var values = [];
                options.each(function () {
                  values.push($(this).attr('value'));

                  labels.push($(this).html());

                });
                houseIds = values.join(",");
                return labels.join(', ') + '';
              }
            }
          }
  );



  function addUser(){
    var mobile = $('#mobile').val();
    var name = $('#name').val();

    var param = "mobile=" + mobile + "&name=" + name + "&houseIds=" + houseIds;
    user.add_user(param, "${role}");
  }
</script>
