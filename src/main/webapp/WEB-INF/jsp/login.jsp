<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>login</title>
    <link href="../../static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="../../static/css/login.css" type="text/css" rel="stylesheet">
</head>

<body>
<script src="../../static/jquery-3.4.1.js"></script>
<script src="../../static/bootstrap/js/bootstrap.min.js"></script>
<div class="login">
    <form action="" method="" id="myForm">
        <div class="title">
            <h2>欢迎登录！</h2>
        </div>
        <div class="acc">
            <label for="account">账号：</label>
            <input id="account" type="text">
        </div>
        <p class="unacc"></p>
        <div class="pass">
            <label for="password">密码：</label>
            <input id="password" type="text">
        </div>
        <p class="unpass"></p>
        <div class="login-btn">
            <button type="button" id="btn" class="btn btn-success">登录</button>
            <p style="margin-top: 20px; color: red;"></p>
        </div>
    </form>
</div>
<script>
    $(function () {
        $("#btn").on("click", function () {
            //1 获取账号密码信息
            var account = $("input[id='account']").val();
            var password = $("input[id='password']").val();
            //2 验证账号密码是否为空
            if (!$.trim(account)) {
                $(".unacc").html("账号不能为空！");
                $("input[id='account']").focus();
                return;
            }else{
                $(".unacc").html("");
            }
            if (!$.trim(password)) {
                $(".unpass").html("密码不能为空！");
                $("input[id='password']").focus();
                return;
            }else{
                $(".unpass").html("");
            }
            $("input[id='account']").val($.trim(account));
            //4 提交
            $("#myForm").submit();
        })
    })
</script>
</body>

</html>