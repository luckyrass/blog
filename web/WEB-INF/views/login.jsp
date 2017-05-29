<%--
  Created by IntelliJ IDEA.
  User: jdan
  Date: 2016/10/29
  Time: 20:47
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆页面</title>
    <link rel="shortcut icon" type="image/png" href="<c:url value="/resources/img/icon.png" />">

    <link rel="stylesheet" href="<c:url value="/resources/css/libs/bootstrap.css" />"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/libs/font-awesome.css" />" />
    <link rel="stylesheet" href="<c:url value="/resources/css/login.css" />"/>

    <script src="<c:url value="/resources/js/libs/jquery.min.js" />"></script>
    <script src="<c:url value="/resources/js/libs/bootstrap.js" />"></script>
    <script src="<c:url value="/resources/js/libs/jquery.form.js" />"></script>
    <script src='<c:url value='/resources/js/libs/jquery.validate.js '/>'></script>
</head>
<body>
    <div class="well loginbox">
        <h1>博客登陆</h1>
        <%--<h1>医康互联<br/>移动设备管理平台</h1>--%>
        <form:form role="form" method="post" modelAttribute="loginForm" action="login" id="loginForm">
            <div class="form-group">
                <div class="input-group input-group-lg">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                    <form:input path="user.username" class="form-control" placeholder="用户名" type="text" value=""/>
                </div>
            </div>
            <div class="form-group">
                <div class="input-group input-group-lg" data-toggle="tooltip" data-original-title="密码">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                    <form:input path="user.password" class="form-control" placeholder="密码" type="password" value=""/>
                </div>
            </div>
            <div>
                    <%--用于输入后台返回的验证错误信息 --%>
                <P style="color:red;text-align: center"><c:out value="${message }" /></P>
            </div>
            <div class="checkbox">
                <label>
                    <form:checkbox path="isRemember"/>记住密码
                </label>
                <a href="<c:url value="/index" />" style="float: right;">浏览进入</a>
            </div>
            <button class="wdfull btn btn-primary btn-lg" type="submit" value="login" id="submit">
                登录
            </button>
        </form:form>
    </div>
</body>
<script>
    $(document).ready(function() {
        $("#loginForm").validate({
            rules:{
                "user.username":{
                    required:true,
                    maxlength:18
                },
                "user.password":{
                    required:true,
                    maxlength:18
                }
            },
            messages:{
                "user.username":{
                    required:"请输入用户名",
                    maxlength:"用户名长度不能超过18"
                },
                "user.password":{
                    required:"请输入密码",
                    maxlength:"密码长度不能超过18"
                }
            }
        });
        $("#submit").click(function() {
            if($("#loginForm").valid()) {
                $("#loginForm").submit();
                return true;
            }
            return false;
        });
    });
</script>
</html>
