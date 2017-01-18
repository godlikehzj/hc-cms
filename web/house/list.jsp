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
                <button id="create_house" onclick="add_house()" type="button" class="btn btn-success">新建</button>
                <select id="select_statu" style="margin-left: 20px">
                  <option>上线</option>
                  <option>下线</option>
                </select>
              </div>
            </div>
            <table id="basic-datatables" class="table table-striped table-bordered" cellspacing="0" width="100%">
              <thead>
              <tr>
                <th>id</th>
                <th>名称</th>
                <th>描述</th>
                <th>位置</th>
                <th>操作</th>
                <th>详细信息</th>
              </tr>
              </thead>

              <tbody>
              <c:forEach var="house" items="${houseLists}">
                <tr>
                  <td>${house.id}</td>
                  <td>${house.hname}</td>
                  <td>${house.addr}</td>
                  <td>${house.lng},${house.lat}</td>
                  <td>上线 | 修改</td>
                  <td>查看</td>
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