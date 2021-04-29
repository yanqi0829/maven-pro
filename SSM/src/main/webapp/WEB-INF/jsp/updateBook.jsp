<%--
  Created by IntelliJ IDEA.
  User: wangyq
  Date: 2021/4/29
  Time: 9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改书籍</title>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>修改书籍</small>
                </h1>
                <form action="${pageContext.request.contextPath}/book/updateBook">
                    <%--      前端传递隐藏域              --%>
                    <input type="hidden" name="id" value="${books.id}">
                    <div class="form-group">
                        <label for="bkId">作者</label>
                        <input type="" class="form-control" id="bkId" placeholder="书籍id" name="authorId"
                               value="${books.authorId}"
                               required>
                    </div>
                    <div class="form-group">
                        <label for="bkname">书籍名称</label>
                        <input type="" class="form-control" id="bkname" placeholder="书籍名称" name="bookName"
                               value="${books.bookName}" required>
                    </div>
                    <div class="form-group">
                        <label for="bkprice">价格</label>
                        <input type="" class="form-control" id="bkprice" placeholder="价格" name="price"
                               value="${books.price}" required>
                    </div>
                    <button type="submit" class="btn btn-default">修改</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
