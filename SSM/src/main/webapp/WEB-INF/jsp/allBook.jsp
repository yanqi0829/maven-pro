<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>书籍展示</title>
    <%--    引入bootstrap--%>
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/5.0.0-beta3/css/bootstrap-grid.css"
          rel="stylesheet">
</head>
<body>
<h1>书籍展示</h1>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>书籍信息列表</small>
                </h1>
            </div>
        </div>
    </div>
    <div id="row">
        <div id="col-md-4 column">
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/book/toAddBook">新增书籍</a>
        </div>
    </div>

    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th>id</th>
                    <th>名称</th>
                    <th>价格</th>
                    <th>作者</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="book" items="${list}">
                    <tr>
                        <td>${book.id}</td>
                        <td>${book.bookName}</td>
                        <td>${book.price}</td>
                        <td>${book.authorId}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/book/toUpdate?id=${book.id}">修改</a>
                            &nbsp;|&nbsp;
                            <a href="${pageContext.request.contextPath}/book/deleteBook?id=${book.id}">删除</a>
                            
                        </td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>
        </div>
    </div>
</div>
</body>
</html>
