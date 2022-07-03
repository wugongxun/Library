<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册成功</title>
    <% String basePath = request.getScheme()
            + "://" + request.getServerName()
            + ":"
            + request.getServerPort()
            + request.getContextPath()
            + "/"; %>
    <base href="<%= basePath %>"/>
    <link rel="stylesheet" href="static/css/success.css"/>
</head>
<body>
    <img src="static/img/mylogo.jpg" id="mylogo"/>
    <p>www.wugongxun.icu</p>
    <div id="main"><h1>注册成功!<a href="index.jsp">转到主页</a></h1></div>
    <%@ include file="/pages/common/footer.jsp" %>
</body>
</html>