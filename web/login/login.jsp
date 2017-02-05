<%--
  Created by IntelliJ IDEA.
  User:
  Date: 2016/9/27
  Time: 上午10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>首页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="${ctx}/assets/css/icons.css" rel="stylesheet" />
    <!-- jQueryUI -->
    <link href="${ctx}/assets/css/sprflat-theme/jquery.ui.all.css"  rel="stylesheet" />
    <link href="${ctx}/assets/css/bootstrap.css"  rel="stylesheet" />
    <link href="${ctx}/assets/css/plugins.css" rel="stylesheet" />
    <link href="${ctx}/assets/css/main.css" rel="stylesheet" />
    <link href="${ctx}/assets/css/custom.css" rel="stylesheet" />
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="${ctx}/assets/img/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="${ctx}/assets/img/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="${ctx}/assets/img/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="${ctx}/assets/img/ico/apple-touch-icon-57-precomposed.png">
    <link rel="icon" href="${ctx}/assets/img/ico/favicon.ico" type="image/png">
    <style>
        .pr-domain {
            right: 25px !important;
            height: 25px;
            line-height: 25px;
            color: #616161;
            font-size: 16px;
            overflow: hidden;
            display: block;
            right: -86px;
            position: absolute;
            top: 5px;
        }
    </style>
</head>
<body class="login-page" onkeydown="LoginFunction.keyLogin(event);">
<div id="login" class="animated bounceIn">
    <%--<img id="logo" src="${ctx}/assets/img/logo.png" alt="sprFlat Logo">--%>
        <span id="logo" style="font-size: 40px;color:black;font-weight: bold">虹巢环保</span>
        <div class="login-wrapper">
        <ul id="myTab" class="nav nav-tabs nav-justified bn">
            <li>
                <a href="#log-in" data-toggle="tab">登录</a>
            </li>
            <!-- <li class="">
                <a href="#register" data-toggle="tab">Register</a>
            </li> -->
        </ul>
        <div id="myTabContent" class="tab-content bn">
            <div class="tab-pane fade active in" id="log-in">
                <form class="form-horizontal mt10" action="#" id="login-form" role="form">
                    <div class="form-group">
                        <div class="col-lg-12">
                            <input type="text" name="passport" id="email" class="form-control left-icon" onblur="LoginFunction.validateName('0')">
                            <label class='help-block' style="display: none;"></label>
                            <i class="ec-user s16 left-input-icon"></i>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-12">
                            <input type="password" name="password" id="password" class="form-control left-icon" onblur="LoginFunction.validatePasswd('0')">
                            <label class='help-block' style="display: none;"></label>
                            <i class="ec-locked s16 left-input-icon"></i>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-8">
                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-4">
                            <a class="btn btn-success pull-right" type="button" onclick="LoginFunction.login();">登录</a>
                        </div>
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>

<script src="${ctx}/assets/js/libs/jquery-2.1.1.min.js"></script>
<script src="${ctx}/assets/js/libs/jquery-ui-1.10.4.min.js"></script>
<script>
    LoginFunction = {
        validate : function () {
            return LoginFunction.validateName("1") && LoginFunction.validatePasswd("1");
        },
        validateName : function (showErro) {
            $name = $("#email");

            if($name.val() == null || $name.val() == ''){
                if(showErro == "1"){
                    $name.parent().parent().addClass("has-error");
                    $name.parent().find("label").html("用户名不能为空!").css("display","block");
                }
                return false;
            }else {
                $name.parent().parent().addClass("has-success");
                $name.parent().find("label").html("").css("display","none");
            }
            return true;
        },
        validatePasswd : function (showErro) {
            $passwd = $("#password");
            if($passwd.val() == null || $passwd.val() == ''){
                if(showErro == "1"){
                    $passwd.parent().parent().addClass("has-error");
                    $passwd.parent().find("label").html("密码不能为空!").css("display","block");
                }
                return false;
            }else {
                $passwd.parent().parent().addClass("has-success");
                $passwd.parent().find("label").html("").css("display","none");
            }
            return true;
        },
        login : function(){
            if(LoginFunction.validate()){
                $("#login-form").attr("action","${ctx}/login/login.do");
                $("#login-form").submit();
            }
        },
        keyLogin : function (event) {
            var e = event || window.event || arguments.callee.caller.arguments[0];
            if(e && e.keyCode==13){ // enter 键
                LoginFunction.login();
            }
        }
    }
</script>
</body>
</html>