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
          <h3 class="panel-title">创建商品</h3>
        </div>
        <div class="panel-body">
          <form id="create_mc" name="create_mc" enctype="multipart/form-data" action="${ctx}/mall/add.do" class="form-horizontal group-border hover-stripped">
            <div class="form-group">
              <label class="col-lg-2 col-md-2 col-sm-12 control-label">名称</label>
              <div class="col-lg-10 col-md-10">
                <input type="text" class="form-control" id = "name" name="name" placeholder="" autofocus="autofocus">
              </div>
            </div>
            <div class="form-group">
              <label class="col-lg-2 col-md-2 col-sm-12 control-label">描述</label>
              <div class="col-lg-10 col-md-10">
                <input type="text" class="form-control" id = "description" name="description" placeholder="" autofocus="autofocus">
              </div>
            </div>

            <div class="form-group">
              <label class="col-lg-2 col-md-2 col-sm-12 control-label">消耗积分</label>
              <div class="col-lg-10 col-md-10">
                <input type="text" class="form-control" id = "cost_point" name="cost_point" placeholder="" autofocus="autofocus">
              </div>
            </div>

            <div class="form-group">
              <label class="col-lg-2 col-md-2 col-sm-12 control-label">上传图片</label>
              <div class="col-lg-10 col-md-10">
                <input type="file" class="form-control" id = "img" name="img" placeholder="" autofocus="autofocus">
              </div>
            </div>

            <div class="form-group">
              <div class="col-lg-2 col-md-2 col-sm-12 control-label ">
                <a href="javascript:void(0);"  id="create_user_button" class="btn btn-success">提交</a>
                <button onclick="mall.getCommodityList(1)" type="button" class="btn btn-primary">返回</button>

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
$(function(){

//    var options = {
//        success : mall.getCommodityList(1)
//    };
//    $('#create_mc').ajaxForm(options);

  $('#create_user_button').bind('click',function(){

      $('#create_mc').ajaxSubmit(function () {
          mall.getCommodityList(1);
      });
  });


})
</script>
