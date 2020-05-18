<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>search</title>
  <link href="../../static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="../../static/css/search.css" type="text/css" rel="stylesheet">
</head>
<body>
    <script src="../../static/jquery-3.4.1.js"></script>
    <script src="../../static/bootstrap/js/bootstrap.min.js"></script>
    <header>
      <form action="baiduSearch" method="post" id="myform">
        <span class="span">
          <input type="text" value="${words }" name="words" class="input">
        </span>
        <span>
          <input type="submit" class="btn" value="搜索一下">
        </span>
      </form>
      <div class="u">
        <a href="./index" class="goindex">首页</a>
        <a href="" id="user" class="username">
          username
        </a>
        <a href="./login" class="goindex">退出</a>
      </div>

    </header>
    <ul class="search_content">
        <c:forEach items="${resultList }" var="result" >
        <li><a href="${result.url }">${result.description }</a></li>
        </c:forEach>
    </ul>
    <script>

    </script>
</body>
</html>