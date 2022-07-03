<%--
  Created by IntelliJ IDEA.
  User: wgx
  Date: 2021/11/15
  Time: 20:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <% String basePath = request.getScheme()
            + "://" + request.getServerName()
            + ":"
            + request.getServerPort()
            + request.getContextPath()
            + "/"; %>
    <base href="<%= basePath %>"/>
    <link rel="stylesheet" href="static/css/login.css"/>
</head>
<body>
<img src="static/img/mylogo.jpg" id="mylogo"/>
<p>www.wugongxun.icu</p>
<div id="div1">
    <h2>欢迎登录</h2>
</div>
<div id="div2">
    <form action="userServlet" method="post">
        <input type="hidden" name="action" value="login"/>
        <label id="title">&nbsp会员登录</label>
        <a href="pages/user/register.jsp">立即注册</a>
        <br/>
        <span id="error">
            ${ pageContext.request.getAttribute("error") }
        </span>
        <br/>
        <br/>
        <label>&nbsp用户名称:</label>
        <input type="text" placeholder="请输入用户名" name="username" class="text">
        <br/>
        <span class="errorMes" id="usernameError"></span>
        <br/>
        <br/>
        <label>&nbsp用户密码:</label>
        <input type="password" placeholder="请输入密码" name="password" class="text">
        <br/>
        <span class="errorMes" id="passwordError"></span>
        <br/>
        <br/>
        <input type="submit" id="submit" value="登录" class="text">
    </form>
</div>
</body>
</html>
