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
                <input id="houseIds" type="text" class="form-control" name="houseIds" placeholder="" autofocus="autofocus">
                <a href="#myModal" role="button" class="btn btn-alt btn-danger mb25" data-toggle="modal">选择</a>
              </div>
            </div>
            <div class="form-group">
              <div class="col-lg-2 col-md-2 col-sm-12 control-label ">
                <button id="create_user_button" type="button" onclick="addUser()" class="btn btn-success">提交</button>

              </div>
            </div>
          </form>
          <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                  <h4 class="modal-title">Modal title</h4>
                </div>
                <div class="modal-body">
                  <!-- content in modal, tinyMCE 4 texarea -->
                                            <textarea class="modal-example" name="content">
                                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis <a href="javascript:if(confirm('http://themes.suggelab.com/sprflat.v1.0.4/le-test-page/de-la-subsub  \n\n���ļ��޷��� Teleport Ultra ����, ��Ϊ ���������淢�����, ��ֹ�����ء�  \n\n�����ڷ������ϴ���?'))window.location='http://themes.suggelab.com/sprflat.v1.0.4/le-test-page/de-la-subsub'" tppabs="http://themes.suggelab.com/sprflat.v1.0.4/le-test-page/de-la-subsub" target="_self"
                                                                                                                                                                                                                 rel="nofollow">nostrud</a> exercitation <a href="#test" target="_self" rel="nofollow">ullamco</a> laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit
                                                  esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
                                            </textarea>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                  <button type="button" class="btn btn-primary">Save changes</button>
                </div>
              </div>
              <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
          </div>
        </div>
      </div>
    </div>
    <!-- End col-lg-12 -->
  </div>
</div>

<script type="text/javascript">
  function addUser(){
    var mobile = $('#mobile').val();
    var name = $('#name').val();
    var houseIds = $('#houseIds').val();

    var param = "mobile=" + mobile + "&name=" + name + "&houseIds=" + houseIds;
    user.add_user(param, "${role}");
  }

</script>
