<%@ page import="com.wgx.pojo.Book" %><%--
  Created by IntelliJ IDEA.
  User: wgx
  Date: 2021/11/19
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>编辑图书</title>
    <% String basePath = request.getScheme()
            + "://" + request.getServerName()
            + ":"
            + request.getServerPort()
            + request.getContextPath()
            + "/"; %>
    <base href="<%= basePath %>"/>
    <link rel="stylesheet" href="static/css/book_edit.css">
    <link rel="stylesheet" href="static/css/common.css">
</head>
<body>
<img src="static/img/mylogo.jpg" id="mylogo"/>
<p>www.wugongxun.icu</p>
<h1>编辑图书</h1>
<a href="manager/bookServlet?action=page" id="book_manager">图书管理</a>
<a href="" id="order">订单管理</a>
<a href="index.jsp" id="return">返回首页</a>
<div id="main">
    <table>
        <tr>
            <td>名称</td>
            <td>价格</td>
            <td>作者</td>
            <td>销量</td>
            <td>库存</td>
            <td>操作</td>
        </tr>
        <tr>
            <form action="manager/bookServlet" method="get">
                <input type="hidden" name="action" value="${param.method}"/>
                <input type="hidden" name="id" value="${requestScope.book.id}"/>
                <input type="hidden" name="pageNo" value="${param.pageNo}"/>
                <td><input type="text" name="name" value="${requestScope.book.name}"/></td>
                <td><input type="text" name="price" value="${requestScope.book.price}"/></td>
                <td><input type="text" name="author" value="${requestScope.book.author}"/></td>
                <td><input type="text" name="sales" value="${requestScope.book.sales}"/></td>
                <td><input type="text" name="stock" value="${requestScope.book.stock}"/></td>
                <td><input type="submit"/></td>
            </form>
        </tr>
    </table>
</div>
<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>
