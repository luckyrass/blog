<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: jdan
  Date: 2017/5/30
  Time: 20:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加新闻</title>
    <link rel="shortcut icon" type="image/png" href="<c:url value="/resources/img/icon.png" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/libs/bootstrap.css" />"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/libs/font-awesome.css" />" />
    <link rel="stylesheet" href="<c:url value="/resources/css/base.css" />" />
    <link rel="stylesheet" href="<c:url value="/resources/css/manage.css" />" />

    <script src="<c:url value="/resources/js/libs/jquery.min.js" />"></script>
    <script src="<c:url value="/resources/js/libs/bootstrap.js" />"></script>
</head>
<body>
<header>
    <div id="logo"><a href="<c:url value="/index"/> "></a></div>
    <nav class="topnav" id="topnav">
        <shiro:authenticated>
            <span class="userInfo">welcome <shiro:principal/> </span>
            <a href="<c:url value="/user/logout" />"><span>退出</span><span class="en">Logout</span></a>
        </shiro:authenticated>
    </nav>
</header>
    <form:form role="form" modelAttribute="news" method="post" action="add">
        <label>发表文章</label>
        <form:input type="hidden" path="authorname" value="<shiro:principal/>"/>
        <div class="form-group">
            <label for="title">文章标题</label>
            <form:input type="text" class="form-control" path="title" id="title" placeholder="请输入文章标题"/>
        </div>
        <div class="form-group">
            <label for="newsIntro">文章简介</label>
            <form:textarea path="newsintro" class="form-control" rows="3" id="newsIntro"></form:textarea>
        </div>
        <div class="form-group">
            <label for="newsContent">文章内容</label>
            <form:textarea path="newscontent" class="form-control" rows="10" id="newsContent"></form:textarea>
        </div>
        <div class="form-group">
            <label for="keywords">文章标签</label>
            <form:input type="text" class="form-control" path="keywords" id="keywords" placeholder="请输入文章标题"/>
        </div>
        <div class="form-group">
            <label>文章分类</label>
            <div>
                <c:forEach var="category" items="${categories}">
                    <label class="checkbox-inline">
                        <form:input type="hidden" path="categoryname" value="${category.namealias}"/>
                            <%--<form:radiobutton type="radio" path="newstype" value="${category.typeid}"/>${category.namealias}--%>
                        <form:radiobutton path="newstype" value="${category.typeid}"/>${category.namealias}
                    </label>
                </c:forEach>
            </div>
        </div>
        <div class="form-group">
            <label>是否公开</label>
            <label class="checkbox-inline">
                <form:radiobutton path="privilege" name = "privilege" value="0"/>公开
            </label>
            <label class="checkbox-inline">
                <form:radiobutton path="privilege" name = "privilege" value="1"/>私密
            </label>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary">发表文章</button>
            <button type="button" class="btn btn-default">舍弃</button>
        </div>
    </form:form>
</body>
</html>
