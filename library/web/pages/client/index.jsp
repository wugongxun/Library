<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.util.Arrays" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wgx
  Date: 2021/11/14
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>首页</title>
  <% String basePath = request.getScheme()
          + "://" + request.getServerName()
          + ":"
          + request.getServerPort()
          + request.getContextPath()
          + "/";
  pageContext.setAttribute("basePath", basePath);
  %>
  <base href="<%= basePath %>"/>
  <link rel="stylesheet" href="static/css/index.css">
  <link rel="stylesheet" href="static/css/common.css">
  <script src="static/script/jquery-3.6.0.min.js"></script>
  <script>
    $(function () {
      $("a.delete").click(function () {
        return confirm("确认删除" + $(this).parent().parent().find("td:first").text() + "?");
      });
      $("#pageJump").click(function () {
        location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo=" + $("#pn_input").val();
      });
      $("#search_button").click(function () {
        location.href = "${pageScope.basePath}client/bookServlet?action=pageByPrice&pageNo=1" + "&min=" + $("#min").val() + "&max=" + $("#max").val();
      });
      $("#cart").click(function () {
        if (${empty sessionScope.loginUser}) {
          alert("请先登录");
          return false;
        }
      });
      $(".addCartItem").click(function () {
        if (${empty sessionScope.loginUser}) {
          alert("请先登录");
          return false;
        }
        location.href = "${pageScope.basePath}cartServlet?action=addCartItem&userId=${sessionScope.loginUser.id}&itemId=" + $(this).attr("itemId");
      });
      $("#myOrder").click(function () {
        if (${empty sessionScope.loginUser}) {
          alert("请先登录");
          return false;
        }
      });
    });
  </script>
</head>
  <body>
    <img src="static/img/mylogo.jpg" id="mylogo"/>
    <p>www.wugongxun.icu</p>
    <h1>网上书城</h1>
    <c:if test="${not empty sessionScope.loginUser.username}">
      <%@include file="/pages/common/welcome.jsp" %>
    </c:if>
    <c:if test="${empty sessionScope.loginUser.username}">
      <a href="pages/user/login.jsp" id="login">登录</a>
      <a href="pages/user/register.jsp" id="register">注册</a>
    </c:if>
    <a href="orderServlet?action=listMyOrders" id="myOrder">我的订单</a>
    <a href="pages/cart/cart.jsp" id="cart">购物车</a>
    <a href="manager/bookServlet?action=page" id="manager">后台管理</a>
    <div id="main">
      <div id="search">
        价格:<input type="text" class="search_price" id="min" value="${param.min}"/>元
        -<input type="text" class="search_price" id="max" value="${param.max}"/>元
        <button id="search_button">搜索</button><br/>
        <c:if test="${sessionScope.loginUser.cart.totalCount != 0 && not empty sessionScope.loginUser.cart}">
          您的购物车中共有<span id="cartTotalCount">${sessionScope.loginUser.cart.totalCount}</span>件商品<br/>
          您刚刚将<span id="good">${sessionScope.addItemName}</span>加入了购物车
        </c:if>
        <c:if test="${sessionScope.loginUser.cart.totalCount == 0 || empty sessionScope.loginUser.cart}">
          <span id="good">购物车为空</span>
        </c:if>
      </div>
      <c:if test="${not empty requestScope.page.pageItems}">
      <c:forEach items="${requestScope.page.pageItems}" var="pageItem">
      <div id="exhibition">
        <div id="exhibition_img">
          <img id="book_img" src="${pageItem.imgPath}"/>
        </div>
        <div id="book_info">
          &nbsp书名:<span>${pageItem.name}</span><br/>
          &nbsp作者:<span>${pageItem.author}</span><br/>
          &nbsp价格:￥<span>${pageItem.price}</span><br/>
          &nbsp销量:<span>${pageItem.sales}</span><br/>
          &nbsp库存:<span>${pageItem.stock}</span><br/>
        </div>
        &nbsp<input type="button" value="加入购物车" class="addCartItem" itemId="${pageItem.id}">
      </div>
      </c:forEach>
      </c:if>

      <div id="page">
        <c:if test="${requestScope.page.pageNo > 1}">
          <a href="client/bookServlet?action=page">首页</a>
          <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo - 1}">上一页</a>
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
            <a href="${requestScope.page.url}&pageNo=${pageNo}">${pageNo}</a>
          </c:if>
        </c:forEach>
        <c:if test="${requestScope.page.pageNo < requestScope.page.pageCount}">
          <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo + 1}">下一页</a>
          <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageCount}">末页</a>
        </c:if>
        共${requestScope.page.pageCount}页,${requestScope.page.itemsCount}条记录
        到第<input type="text" id="pn_input" value="${requestScope.page.pageNo}">页
        <button id="pageJump">确定</button>
      </div>

    </div>
    <%@ include file="/pages/common/footer.jsp" %>
  </body>
</html>