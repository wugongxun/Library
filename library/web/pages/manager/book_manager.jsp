<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wgx
  Date: 2021/11/18
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>图书管理系统</title>
    <% String basePath = request.getScheme()
            + "://" + request.getServerName()
            + ":"
            + request.getServerPort()
            + request.getContextPath()
            + "/";
    pageContext.setAttribute("basePath", basePath);
    %>
    <base href="<%= basePath %>"/>
    <link rel="stylesheet" href="static/css/book_manager.css">
    <link rel="stylesheet" href="static/css/common.css">
    <script src="static/script/jquery-3.6.0.min.js"></script>
    <script>
        $(function () {
           $("a.delete").click(function () {
               return confirm("确认删除" + $(this).parent().parent().find("td:first").text() + "?");
           });
           $("#pageJump").click(function () {
               location.href = "${pageScope.basePath}${requestScope.page.url}" + $("#pn_input").val();
           });
        });
    </script>
</head>
    <body>
        <img src="static/img/mylogo.jpg" id="mylogo"/>
        <p>www.wugongxun.icu</p>
        <h1>图书管理系统</h1>
        <a href="orderServlet?action=listOrders" id="order">订单管理</a>
        <a href="index.jsp" id="return">返回首页</a>
        <div id="main">
            <table>
                <tr>
                    <td>书名</td>
                    <td>价格</td>
                    <td>作者</td>
                    <td>销量</td>
                    <td>库存</td>
                    <td colspan="2">操作</td>
                </tr>
                <c:forEach items="${requestScope.page.pageItems}" var="book">
                    <tr>
                        <td>${book.name}</td>
                        <td>${book.price}</td>
                        <td>${book.author}</td>
                        <td>${book.sales}</td>
                        <td>${book.stock}</td>
                        <td><a href="manager/bookServlet?action=getBook&id=${book.id}&method=update&pageNo=${requestScope.page.pageNo}">修改</a></td>
                        <td><a href="manager/bookServlet?action=delete&id=${book.id}&pageNo=${requestScope.page.pageCount}" class="delete">删除</a></td>
                    </tr>
                </c:forEach>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td><a href="pages/manager/book_edit.jsp?method=addBook&pageNo=${requestScope.page.pageCount}">添加图书</a></td>
                </tr>
            </table>
            <div id="page">
                <c:if test="${requestScope.page.pageNo > 1}">
                    <a href="manager/bookServlet?action=page">首页</a>
                    <a href="${requestScope.page.url}${requestScope.page.pageNo - 1}">上一页</a>
                </c:if>
                <c:if test="${requestScope.page.pageCount <= 5}">
                    <c:set var="begin" value="1"></c:set>
                    <c:set var="end" value="${requestScope.page.pageCount}"></c:set>
                </c:if>
                <c:if test="${requestScope.page.pageCount > 5}">
                    <c:choose>
                        <c:when test="${requestScope.page.pageNo <= 3}">
                            <c:set var="begin" value="1"></c:set>
                            <c:set var="end" value="5"></c:set>
                        </c:when>
                        <c:when test="${requestScope.page.pageNo >= requestScope.page.pageCount - 3}">
                            <c:set var="begin" value="${requestScope.page.pageCount - 4}"></c:set>
                            <c:set var="end" value="${requestScope.page.pageCount}"></c:set>
                        </c:when>
                        <c:otherwise>
                            <c:set var="begin" value="${requestScope.page.pageNo - 2}"></c:set>
                            <c:set var="end" value="${requestScope.page.pageNo + 2}"></c:set>
                        </c:otherwise>
                    </c:choose>
                </c:if>
                <c:forEach begin="${begin}" end="${end}" var="pageNo">
                    <c:if test="${pageNo == requestScope.page.pageNo}">
                        【${pageNo}】
                    </c:if>
                    <c:if test="${pageNo != requestScope.page.pageNo}">
                        <a href="${requestScope.page.url}${pageNo}">${pageNo}</a>
                    </c:if>
                </c:forEach>
                <c:if test="${requestScope.page.pageNo < requestScope.page.pageCount}">
                    <a href="${requestScope.page.url}${requestScope.page.pageNo + 1}">下一页</a>
                    <a href="${requestScope.page.url}${requestScope.page.pageCount}">末页</a>
                </c:if>
                共${requestScope.page.pageCount}页,${requestScope.page.itemsCount}条记录
                到第<input type="text" id="pn_input" value="${requestScope.page.pageNo}">页
                <button id="pageJump">确定</button>
            </div>
        </div>
        <%@ include file="/pages/common/footer.jsp" %>
    </body>
</html>
