<%--
  Created by IntelliJ IDEA.
  User: wgx
  Date: 2021/11/26
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="welcome">
    欢迎<span>${sessionScope.loginUser.username}</span>
    <a href="userServlet?action=logout">注销</a>
</div>
