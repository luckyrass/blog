<%--
  Created by IntelliJ IDEA.
  User: jdan
  Date: 2017/5/30
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<html>
<head>
    <title>文章管理</title>
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
    <div class="toolbar">
        <%--<button type="button" class="btn btn-primary addnews" href="<c:url var="/index"/> ">写新文章</button>--%>
        <a class="btn btn-primary" href="/news/add">写新文章</a>
        <div class="col-md-3 search fr">
            <div class="input-group">
                <input type="text" class="form-control" id="segment" value=""/>
               <span class="input-group-btn">
                  <button class="btn btn-default" type="button" onclick="searchUser()">
                      <i class="glyphicon glyphicon-search"></i>
                  </button>
               </span>
            </div><!-- /input-group -->
        </div>
    </div>

    <form class="form-inline form-search" role="form" method="post" action="/news/list">
        <div class="form-group">
            <label class="control-label">类别：</label>
            <select class="form-control" id="selectNewsType" name="category">
                <option value="all">全部</option>
                <option value="study">学无止境</option>
                <option value="life">慢生活</option>
            </select>
        </div>
        <div class="form-group mgLeft30" id="subNewsType">
            <label>子类别：</label>
            <select class="form-control" id="study" disabled="disabled" name="studyCategory">
                <option value="all">全部</option>
                <option value="algorithm">算法</option>
                <option value="java">Java</option>
                <option value="paper">论文</option>
                <option value="experience">心得笔记</option>
            </select>
            <select class="form-control" id="life" style="display: none" disabled="disabled" name="lifeCategory">
                <option value="all">全部</option>
                <option value="diary">日记</option>
                <option value="book">读书</option>
                <option value="programLife">程序人生</option>
                <option value="quotation">经典语录</option>
            </select>
        </div>
        <div class="form-group mgLeft30">
            <label>可见性：</label>
            <select class="form-control" id="privilege" name="privilege">
                <option value= -1>全部</option>
                <option value= 0>公开</option>
                <option value= 1>私密</option>
            </select>
        </div>
        <button type="submit" class="btn btn-default mgleft30">确定</button>
    </form>


    <div class="box-content">
        <table class="table table-hover">
            <thead>
                <tr>
                    <th>标题</th>
                    <th>日期</th>
                    <th>可见性</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="news" items="${newsList}">
                <tr>
                    <td class="news-title"><a href="<c:url value="/detailPage?id=${news.newsid}"/>" target="_blank">${news.title}</a></td>
                    <td class=""><fmt:formatDate value="${news.releasedate}" type="date"/></td>
                    <td class="">
                        <c:set var="flag" value="${news.privilege}"/>
                        <c:if test="${flag == 0}">
                            <span class="label label-success">公开</span>
                        </c:if>
                        <c:if test="${flag == 1}">
                            <span class="label label-danger">私密</span>
                        </c:if>
                    </td>
                    <td>
                        <a data-toggle="modal" data-target="" onclick="" style="margin-right: 10px;">编辑</a>|
                        <a style="margin-left: 10px;" onclick="confirm('${news.newsid}', '${news.title}')">删除</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <!--confirm modal-->
    <div class="modal fade" id="confirm" tabindex="1" role="dialog" aria-labelledby="myModalLable" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">
                        提示
                    </h4>
                </div>
                <div class="modal-body">
                    <p>确认删除新闻 [<span id="name"></span>] ?</p>
                </div>
                <div class="modal-footer">
                    <a type="button" class="btn btn-default" href="" id="delete">确认</a>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>

</body>
<script>
    function confirm(id, name){
        $("#name").text(name);
        $("#delete").attr("href", "<c:url value="/news/delete?" />" + "newsid="+ id);
        $("#confirm").modal('show');
    }

    $(document).ready(function() {
        <c:if test="${JsupCategory != null}">
            $("#selectNewsType").val('${JsupCategory}');
        </c:if>
        <c:if test="${JsupCategory != all}">
            $("#subNewsType select").removeAttr("disabled");
        </c:if>
        <c:if test="${JstudyCategory != null}">
            $("#study").val('${JstudyCategory}');
        </c:if>
        <c:if test="${JlifeCategory != null}">
            $("#life").val('${JlifeCategory}');
        </c:if>
        <c:if test="${Jprivilege != null}">
            $("#privilege").val('${Jprivilege}');
        </c:if>

        $("#selectNewsType").change(function(){
            var val = $("#selectNewsType").val();
            console.log(val);
            if (val == "all") {
                $("#subNewsType select").attr("disabled","disabled");
            }else {
                $("#subNewsType select").removeAttr("disabled");
                if (val == "study") {
                    $("#study").css("display", "inline-block");
                    $("#life").css("display", "none");
                }else if(val == "life") {
                    $("#study").css("display", "none");
                    $("#life").css("display", "inline-block");
                }
            }
        });

    });
</script>
</html>
