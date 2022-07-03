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
    <title>订单详情</title>
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
    <script src="static/script/jquery-3.6.0.min.js"></script>
    <script>
        $(function () {
            $("#receivedOrder").click(function () {
                return confirm("确认收货?");
            });
        });
    </script>
</head>
<body>
<img src="static/img/mylogo.jpg" id="mylogo"/>
<p>www.wugongxun.icu</p>
<h1>订单详情</h1>
<c:if test="${requestScope.from == 'user'}">
    <a href="orderServlet?action=listMyOrders" id="myOrder">我的订单</a>
</c:if>
<c:if test="${requestScope.from == 'manager'}">
    <a href="orderServlet?action=listOrders" id="orders">订单管理</a>
</c:if>
<a href="index.jsp" id="return">返回首页</a>
<div id="main">
    <table>
        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>
        </tr>
        <c:forEach items="${requestScope.orderItems}" var="orderItem">
            <tr>
                <td>${orderItem.name}</td>
                <td>${orderItem.count}</td>
                <td>${orderItem.price}</td>
                <td>${orderItem.totalPrice}</td>
            </tr>
        </c:forEach>
        <c:if test="${'user' == requestScope.from && '已发货' == requestScope.status}">
            <tr>
                <td colspan="4"><a href="orderServlet?action=receivedOrder&orderId=${requestScope.orderId}" id="receivedOrder">确认收货</a></td>
            </tr>
        </c:if>
        <c:if test="${'user' == requestScope.from && '已发货' != requestScope.status}">
            <tr>
                <td colspan="4">${requestScope.status}</td>
            </tr>
        </c:if>
        <c:if test="${'manager' == requestScope.from && '未发货' == requestScope.status}">
            <tr>
                <td colspan="4"><a href="orderServlet?action=deliverGoods&orderId=${requestScope.orderId}">发货</a></td>
            </tr>
        </c:if>
        <c:if test="${'manager' == requestScope.from && '未发货' != requestScope.status}">
            <tr>
                <td colspan="4">${requestScope.status}</td>
            </tr>
        </c:if>
    </table>
</div>
<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>
