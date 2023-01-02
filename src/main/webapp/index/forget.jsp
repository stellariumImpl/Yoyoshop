<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>忘记密码</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link type="text/css" rel="stylesheet" href="css/bootstrap.css">
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/simpleCart.min.js"></script>

    <link rel="stylesheet" href="layui/css/layui.css"  media="all">
</head>
<body>

<jsp:include page="header.jsp"/>
<!--account-->
<div class="account">
    <div class="container">
        <div class="register">
            <c:if test="${msg!=null}"><div class="alert alert-danger">${msg}</div></c:if>
            <form action="forget" method="post">
                <div class="register-top-grid">
                    <h3>重置密码</h3>
                    <div class="input" style="margin-left: 160px">
                        <span>用户名 <label style="color:red;">*</label></span>
                        <input type="text" name="username" placeholder="请输入用户名" required="required" style="width: 350px;height: 32px">
                    </div>
                    <div class="input" style="margin-left: 160px">
                        <span>手机号 <label style="color:red;">*</label></span>
                        <input type="tel" minlength="11" maxlength="11" name="phone" placeholder="请输入手机号" required="required" style="width: 350px;height: 32px">
                    </div>
                    <div class="clearfix"> </div>
                </div>
                <div class="register-but text-center">
                    <input type="submit" value="重置"><br><br>

                    <div class="clearfix"> </div>
                </div>
            </form>
            <div class="clearfix"> </div>
        </div>
    </div>
</div>
<!--//account-->

<jsp:include page="footer.jsp"/>
<script src="layui/layui.js" charset="utf-8"></script>
</body>
</html>