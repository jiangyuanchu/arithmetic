<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>register</title>
    <link href="../../static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="../../static/css/register.css" type="text/css" rel="stylesheet">
</head>

<body>
<script src="../../static/jquery-3.4.1.js"></script>
<script src="../../static/bootstrap/js/bootstrap.min.js"></script>
<div class="box">
    <h3>用科技</h3>
    <p>让复杂的世界更简单</p>
</div>
<div class="register">
    <div class="title">
        <h3>欢迎注册!</h3>
        <p>已有账号？<a href="./login.jsp" target="_blank" class="go_login" style="color: #2e58ff;cursor: pointer;">去登陆</a></p>
    </div>
    <div class="content">
        <form id="myForm" action="" type="">
            <p>
                <label class="user_name" for="name">用户名</label>
                <input id="name" type="text">
            </p>
            <p>
                <label class="user_account" for="account">手机号</label>
                <input id="account" type="text">
            </p>
            <p>
                <label class="user_password" for="password">密&nbsp;&nbsp;&nbsp;码</label>
                <input id="password" type="text">
            </p>
            <p>
                <button class="regisyer_btn" type="button">注册</button>
            </p>
        </form>
    </div>
</div>

<script>
    $(function () {
        $(".regisyer_btn").on("click", function () {
            //1 获取账号密码信息
            var name = $("input[id='name']").val();
            var account = $("input[id='account']").val();
            var password = $("input[id='password']").val();
            //2 验证账号密码是否为空
            if (!$.trim(name)) {
                $("input[id='name']").focus();
                return;
            }
            if (!$.trim(account)) {
                $("input[id='account']").focus();
                return;
            }
            if (!$.trim(password)) {
                $("input[id='password']").focus();
                return;
            }
            $("input[id='account']").val($.trim(account));
            //4 提交
            $("#myForm").submit();
        })
    })
</script>
</body>

</html>