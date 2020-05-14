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
        <a href="https://voice.baidu.com/act/newpneumonia/newpneumonia/?from=osari_pc_1" class="a">抗击肺炎</a>
        <a href="./register" target="_blank" class="shezhi">注册</a>
        <a href="./login" target="_blank" class="login">登录</a>
        <a href="" class="user">
            <span class="glyphicon glyphicon-user"></span>
            <span>
              用户名
            </span>
        </a>
        <a href="./login.jsp" target="_blank" class="tuichu">退出登录</a>
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
        <li>
            <span class="one">1</span>
            <a href="">吉林省舒兰市全面进入战时状态吉林省舒兰市全面进入战时状态</a>
        </li>
        <li>
            <span class="two">2</span>
            <a href="">加纳1人传染533名工厂同事加纳1人传染533名工厂同事</a>
        </li>
        <li>
            <span class="three">3</span>
            <a href="">赵丽颖冯绍峰牵手逛街赵丽颖冯绍峰牵手逛街</a>
        </li>
        <li>
            <span class="others">4</span>
            <a href="">吉林省舒兰市全面进入战时状态</a>
        </li>
        <li>
            <span class="others">5</span>
            <a href="">加纳1人传染533名工厂同事</a>
        </li>
        <li>
            <span class="others">6</span>
            <a href="">赵丽颖冯绍峰牵手逛街</a>
        </li>
    </ul>
</div>
<script>

</script>
</body>
</html>
