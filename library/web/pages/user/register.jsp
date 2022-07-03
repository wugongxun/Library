<%--
  Created by IntelliJ IDEA.
  User: wgx
  Date: 2021/11/16
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>会员注册</title>
    <% String basePath = request.getScheme()
        + "://" + request.getServerName()
        + ":"
        + request.getServerPort()
        + request.getContextPath()
        + "/"; %>
    <base href="<%= basePath %>"/>
    <link rel="stylesheet" href="static/css/register.css"/>
    <script src="static/script/jquery-3.6.0.min.js"></script>
    <script>
        $(function () {
            $("#submit").click(function () {
                var usernamePatten = /^\w{5,12}$/;
                var usernameText = $("[name='username']").val();
                if(!usernamePatten.test(usernameText)) {
                    $("#usernameError").text("用户名必须为数字、字母或者下划线，必须为5到12位");
                    return false;
                }
                $("#usernameError").text("");
                var passwordText = $("[name='password']").val();
                var passwordPatten = /^\w{5,12}$/;
                if(!passwordPatten.test(passwordText)) {
                    $("#passwordError").text("密码必须为数字、字母或者下划线，必须为5到12位");
                    return false;
                }
                $("#passwordError").text("");
                var confirmPasswordText = $("[name='confirmPassword']").val();
                if (!(confirmPasswordText == passwordText)){
                    $("#confirmPasswordError").text("确认密码必须和密码一致");
                    return false;
                }
                $("#confirmPasswordError").text("");
                var emailText = $("[name='email']").val();
                var emailPatten = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
                if(!emailPatten.test(emailText)) {
                    $("#emailError").text("邮箱格式不正确");
                    return false;
                }
                $("#emailError").text("");
                var codeText = $("[name='code']").val();
                codeText = $.trim(codeText);
                if(codeText == null || codeText == "") {
                    $("#codeError").text("验证码不能为空");
                    return false;
                }
                $("#codeError").text("");
            });
            $("#code").click(function () {
                this.src = "${basePath}kaptcha.jpg?date=" + new Date();
            });
            $("[name='username']").blur(function () {
                var username = this.value;
                $.getJSON("http://localhost:8080/library/userServlet", "action=ajaxExistUsername&username=" + username, function (data) {
                    if ($("[name='username']").val() == null || $("[name='username']").val() == "") {
                        $("#usernameError").text("用户名不能为空");
                    }else {
                        if (data.existUsername) {
                            $("#usernameError").text("用户名已存在");
                        }else {
                            $("#usernameError").text("");
                        }
                    }
                });
            });
        });
    </script>
</head>
<body>
<img src="static/img/mylogo.jpg" id="mylogo"/>
<p>www.wugongxun.icu</p>
<div id="div1">
    <h2>欢迎注册</h2>
</div>
<div id="div2">
    <form action="userServlet" method="post">
        <input type="hidden" name="action" value="register"/>
        <label><h1>&nbsp注册会员</h1></label>
        <label>&nbsp用户名称:</label>
        <input type="text" placeholder="请输入用户名" name="username" value="${requestScope.username}">
        <br/>
        <span class="errorMes" id="usernameError">
            <%
                String error = (String)request.getAttribute("error");
            %>
            ${ "用户名不可用".equals(error) ? error : " " }
        </span>
        <br/>
        <label>&nbsp用户密码:</label>
        <input type="password" placeholder="请输入用户密码" name="password">
        <br/>
        <span class="errorMes" id="passwordError"></span>
        <br/>
        <label>&nbsp确认密码:</label>
        <input type="password" placeholder="请输入确认密码" name="confirmPassword">
        <br/>
        <span class="errorMes" id="confirmPasswordError"></span>
        <br/>
        <label>&nbsp电子邮件:</label>
        <input type="text" placeholder="请输入电子邮件" name="email">
        <br/>
        <span class="errorMes" id="emailError"></span>
        <br/>
        <label>&nbsp验证码:</label>
        <input type="text" name="code">
        <img src="kaptcha.jpg" id="code"/>
        <br/>
        <span class="errorMes" id="codeError">
            &nbsp<%= "验证码错误".equals(error) ? error : " " %>
        </span>
        <br/>
        <button type="submit" id="submit">注册</button>
    </form>
</div>
</body>
</html>
