<%--
  Created by IntelliJ IDEA.
  User: jdan
  Date: 2016/10/29
  Time: 20:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<html>
<head>
    <title>首页</title>
    <link rel="shortcut icon" type="image/png" href="<c:url value="/resources/img/icon1.png" />">
</head>
<body>
    <%@include file="header.jsp"%>
    <link href="<c:url value="/resources/css/index.css" />" rel="stylesheet">
    <div class="banner">
        <section class="box">
            <ul class="texts">
                <p>我听闻最美的故事，是公主死去了，屠龙的少年还在燃烧。</p>
                <p>火苗再小，你都要反复的点燃。</p>
                <p>所谓热血的少年，青涩的爱恋，死亡与梦之约。</p>
            </ul>
            <div class="avatar"><a href="#"><span>jdan</span></a> </div>
        </section>
    </div>
    <article>
        <h2 class="title_tj">
            <p>文章<span>推荐</span></p>
        </h2>
        <div class="bloglist left">
            <c:forEach var="news" items="${newsList}">
                <div class = "item">
                    <h3>${news.title}</h3>
                    <!-- <figure><img src="images/001.png"></figure> -->
                    <ul>
                        <p>${news.newsintro}</p>
                        <a href="<c:url value="/detailPage?id=${news.newsid}" />" target="_blank" class="readmore">阅读全文>></a>
                    </ul>
                    <p class="dateview"><span><fmt:formatDate value="${news.releasedate}" type="date"/></span><span>作者：${news.authorname}</span><span>个人博客：[ <a href="#">${news.categoryname}</a> ]</span></p>
                </div>
            </c:forEach>

        </div>
        <aside class="right">
            <div class="weather"><iframe width="250" scrolling="no" height="60" frameborder="0" allowtransparency="true" src="http://i.tianqi.com/index.php?c=code&id=12&icon=1&num=1"></iframe></div>
            <div class="news">
                <h3>
                    <p>最新<span>文章</span></p>
                </h3>
                <ul class="rank">
                    <c:forEach var="news" items="${recentNewsList}">
                        <li><a href="<c:url value="/detailPage?id=${news.url}" />" title="${news.title}" target="_blank">${news.title}</a></li>
                    </c:forEach>
                </ul>
                <h3 class="ph">
                    <p>点击<span>排行</span></p>
                </h3>
                <ul class="paih">
                    <c:forEach var="news" items="${recentNewsList}">
                        <li><a href="<c:url value="/detailPage?id=${news.url}" />" title="${news.title}" target="_blank">${news.title}</a></li>
                    </c:forEach>
                </ul>
            </div>
            <!-- Baidu Button BEGIN -->
            <div id="bdshare" class="bdshare_t bds_tools_32 get-codes-bdshare"><a class="bds_tsina"></a><a class="bds_qzone"></a><a class="bds_tqq"></a><a class="bds_renren"></a><span class="bds_more"></span><a class="shareCount"></a></div>
            <script type="text/javascript" id="bdshare_js" data="type=tools&amp;uid=6574585" ></script>
            <script type="text/javascript" id="bdshell_js"></script>
            <script type="text/javascript">
                document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000)
            </script>
        </aside>
    </article>
</body>
</html>
