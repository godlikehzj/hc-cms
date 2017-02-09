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
      <h3 class="panel-title">详细信息</h3>
    </div>
    <div style="padding:15px 30px;">
      <button onclick="house.getFixHistory(${houseId})" type="button" class="btn btn-primary">报修历史</button>
      <button onclick="house.getAchieveHistory(${houseId})" type="button" class="btn btn-primary">清运历史</button>
      <button onclick="house.getOrderHistory(${houseId})" type="button" class="btn btn-primary">回收历史</button>

    </div>
    <div class="panel-body">
      <div class="col-lg-12">
        <!-- col-lg-8 start here -->
        <div class="panel panel-primary">
          <!-- Start .panel -->
          <div class="panel-heading">
            <h4 class="panel-title">分拣员信息</h4>
          </div>
          <table class="table table-bordered">
            <thead>
            <tr>
              <th class="per10">电话</th>
              <th class="per10">姓名</th>
              <th class="per10">创建时间</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${fenjian}">
              <tr>
                <td>${user.mobile}</td>
                <td>${user.name}</td>
                <td><fmt:formatDate value="${user.create_time}" pattern="yyyy.MM.dd HH.mm" /> </td>
                  <%--<td>xxxx</td>--%>
              </tr>
            </c:forEach>

            </tbody>
          </table>
        </div>
      </div>

      <div class="col-lg-4">
        <!-- col-lg-8 start here -->
        <div class="panel panel-primary">
          <!-- Start .panel -->
          <div class="panel-heading">
            <h4 class="panel-title">遥测数据</h4>
          </div>
          <div class="panel-body">
            <ul class="list-group">
              <li class="list-group-item list-group-item-success">温度：${houseInfo.temperature}摄氏度</li>
              <li class="list-group-item list-group-item-success">湿度：${houseInfo.humidity}%</li>
              <li class="list-group-item <c:choose>
                <c:when test="${houseInfo.gas == 1}">list-group-item-danger"</c:when>
                <c:when test="${houseInfo.gas == 0}">list-group-item-success"</c:when>
              </c:choose>>烟感：<c:choose>
                <c:when test="${houseInfo.gas == 1}">超标</c:when>
                <c:when test="${houseInfo.gas == 0}">正常</c:when>
              </c:choose>
              <li class="list-group-item <c:choose>
                <c:when test="${houseInfo.aq == 1}">list-group-item-danger"</c:when>
                  <c:when test="${houseInfo.aq == 0}">list-group-item-success"</c:when>
              </c:choose>>有害气体：<c:choose>
              <c:when test="${houseInfo.aq == 1}">超标</c:when>
              <c:when test="${houseInfo.aq == 0}">正常</c:when>
            </c:choose>
            </ul>
          </div>
        </div>
        <!-- End .panel -->
      </div>
      <div class="col-lg-8">
        <div class="panel panel-primary">
          <!-- Start .panel -->
          <div class="panel-heading">
            <h4 class="panel-title">测控数据</h4>
          </div>
          <div class="panel-body">
            <div class="col-lg-5">
              <div class="col-lg-12">
                <h4>屋门</h4>
                <table class="table table-bordered">
                  <thead>
                  <tr>
                    <th class="per10">
                      状态
                    </th>
                    <th class="per10">操作</th>
                  </tr>
                  </thead>
                  <tbody>
                  <tr>
                    <td>
                     <c:choose>
                       <c:when test="${houseInfo.door == 1}">关闭</c:when>
                       <c:when test="${houseInfo.door == 0}">打开</c:when>
                     </c:choose> 
                    </td>
                    <td><a href="#" onclick="alert('km')">开门</a></td>

                  </tr>
                  </tbody>
                </table>
              </div>
              <div class="col-lg-12">
                <h4>缓冲箱</h4>
                <table class="table table-bordered">
                  <thead>
                  <tr>
                    <th class="per10">
                      编号
                    </th>
                    <th class="per10">
                      容量
                    </th>
                    <th class="per10">操作</th>
                  </tr>
                  </thead>
                  <tbody>
                  <c:forEach var="capacity" items="${capacitys}" varStatus="status">
                    <tr>
                      <td>${status.index + 1}</td>
                      <td>
                        ${capacity}%
                      </td>
                      <td><a href="#" onclick="alert('km')">开门</a></td>
                    </tr>
                  </c:forEach>
                  </tbody>
                </table>
              </div>
            </div>
            <div class="col-lg-7">
              <h4>资源柜</h4>
              <table class="table table-bordered">
                <thead>
                <tr>
                  <th class="per10">
                    编号
                  </th>
                  <th class="per10">内门</th>
                  <th class="per10">外门</th>
                  <th class="per10">垃圾桶</th>
                  <th class="per10">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="zyg" items="${zygs}" varStatus="status">
                  <tr>
                    <td>${status.index + 1}</td>
                    <td><c:choose>
                      <c:when test="${zyg[0] == 1}">关闭</c:when>
                      <c:when test="${zyg[0] == 0}">打开</c:when>
                    </c:choose>
                    </td>
                    <td><c:choose>
                      <c:when test="${zyg[1] == 1}">关闭</c:when>
                      <c:when test="${zyg[1] == 0}">打开</c:when>
                    </c:choose></td>
                    <td><c:choose>
                      <c:when test="${zyg[2] == 1}">关闭</c:when>
                      <c:when test="${zyg[2] == 0}">打开</c:when>
                    </c:choose></td>
                    <td><a href="#" onclick="alert('km')">开门</a></td>
                  </tr>
                </c:forEach>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>

      <!-- End .panel -->

    </div>
  </div>
</div>

