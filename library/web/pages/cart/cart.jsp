<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wgx
  Date: 2021/11/27
  Time: 13:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <% String basePath = request.getScheme()
            + "://" + request.getServerName()
            + ":"
            + request.getServerPort()
            + request.getContextPath()
            + "/";
        pageContext.setAttribute("basePath", basePath);
    %>
    <base href="<%= basePath %>"/>
    <link rel="stylesheet" href="static/css/cart.css">
    <link rel="stylesheet" href="static/css/common.css">
    <script src="static/script/jquery-3.6.0.min.js"></script>
    <script>
        $(function () {
            $("a.deleteCartItem").click(function () {
                return confirm("确认删除" + $(this).parent().parent().find("td:first").text() + "?");
            });
        });
    </script>
</head>
<body>
    <img src="static/img/mylogo.jpg" id="mylogo"/>
    <p>www.wugongxun.icu</p>
    <h1>购物车</h1>
    <%@include file="/pages/common/welcome.jsp" %>
    <a href="orderServlet?action=listMyOrders" id="myOrder">我的订单</a>
    <a href="index.jsp" id="return">返回首页</a>
    <div id="main">
        <table >
            <tr>
                <td>商品名称</td>
                <td>数量</td>
                <td>单价</td>
                <td>金额</td>
                <td>操作</td>
            </tr>
            <c:forEach items="${sessionScope.loginUser.cart.cartItems}" var="cartItem">
                <tr>
                    <td>${cartItem.itemName}</td>
                    <td>${cartItem.count}</td>
                    <td>${cartItem.price}</td>
                    <td>${cartItem.totalPrice}</td>
                    <td><a href="cartServlet?action=deleteItem&userId=${sessionScope.loginUser.id}&itemId=${cartItem.itemId}" class="deleteCartItem">删除</a></td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="5">购物车中共有<span id="totalCount">${sessionScope.loginUser.cart.totalCount}</span>件商品&nbsp&nbsp总金额<span id="totalPrice">${sessionScope.loginUser.cart.totalPrice}</span>元&nbsp&nbsp<a href="cartServlet?action=clearCart&userId=${sessionScope.loginUser.id}">清空购物车</a>&nbsp&nbsp<a href="orderServlet?action=createOrder&userId=${sessionScope.loginUser.id}">去结账</a> </td>
            </tr>
        </table>
    </div>
    <%@ include file="/pages/common/footer.jsp" %>

</body>
</html>
