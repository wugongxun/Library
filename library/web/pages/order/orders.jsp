<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wgx
  Date: 2021/11/30
  Time: 13:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>我的订单</title>
  <% String basePath = request.getScheme()
          + "://" + request.getServerName()
          + ":"
          + request.getServerPort()
          + request.getContextPath()
          + "/";
    pageContext.setAttribute("basePath", basePath);
  %>
  <base href="<%= basePath %>"/>
  <link rel="stylesheet" href="static/css/order.css">
  <link rel="stylesheet" href="static/css/common.css">
</head>
<body>
<img src="static/img/mylogo.jpg" id="mylogo"/>
<p>www.wugongxun.icu</p>
<h1>我的订单</h1>
<a href="index.jsp" id="return">返回首页</a>
<div id="main">
  <table>
    <tr>
      <td>日期</td>
      <td>金额</td>
      <td>状态</td>
      <td>详情</td>
    </tr>
    <c:forEach items="${requestScope.orders}" var="order">
      <tr>
        <td>${order.createTimeFormat}</td>
        <td>${order.price}</td>
        <td>${order.status}</td>
        <td><a href="orderServlet?action=showOrderDetail&orderId=${order.orderId}&from=user&status=${order.status}">查看详情</a></td>
      </tr>
    </c:forEach>
  </table>
</div>
<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>
