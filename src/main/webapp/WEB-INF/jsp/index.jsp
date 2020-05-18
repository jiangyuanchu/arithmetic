<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>index</title>
    <link href="../../static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="../../static/css/index.css" type="text/css" rel="stylesheet">
</head>
<body>
<script src="../../static/jquery-3.4.1.js"></script>
<script src="../../static/bootstrap/js/bootstrap.min.js"></script>
<header class="head">
    <div class="ul">
<%--        <a href="https://voice.baidu.com/act/newpneumonia/newpneumonia/?from=osari_pc_1" class="a">抗击肺炎</a>--%>
        <a href="./register" target="_blank" class="shezhi">注册</a>
        <a href="./login" target="_blank" class="login">登录</a>
        <a href="" class="user">
            <span class="glyphicon glyphicon-user"></span>
            <span>
              ${sessionScope.user.name }
            </span>
        </a>
        <a href="exit" target="_blank" class="tuichu">退出登录</a>
        <!-- Single button -->
    </div>
</header>
<form id="myForm" action="baiduSearch" method="post">
    <input name="words" type="text" class="input">
    <input type="submit" value="搜索" class="search">
</form>
<div class="recomend">
    <div class="person_recoment">个性化推荐</div>
    <ul>
        <c:forEach items="${recommendList }" var="recommend">
        <li>
            <span class="one"></span>
            <a href="baiduSearch?words=${recommend.word}">${recommend.word }</a>
        </li>
        </c:forEach>

    </ul>
</div>
<script>

    var user = "${sessionScope.user.name}";
    console.log(user);
    if(user){
        $(".recomend").css("display", "block");
        $(".user").css("display", "inline-block");
        $(".tuichu").css("display", "inline-block");
        $(".login").css("display", "none");
        $(".shezhi").css("display", "none");
    }else{
        $(".recomend").css("display", "none");
        $(".user").css("display", "none");
        $(".tuichu").css("display", "none");
        $(".login").css("display", "inline-block");
        $(".shezhi").css("display", "inline-block");
    }
</script>
</body>
</html>
