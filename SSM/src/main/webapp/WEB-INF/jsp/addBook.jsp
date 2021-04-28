<%--
  Created by IntelliJ IDEA.
  User: wangyq
  Date: 2021/4/28
  Time: 18:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>新增</small>
                </h1>
                <form action="${pageContext.request.contextPath}/book/addBook">
                    <div class="form-group">
                        <label for="bkname">书籍名称</label>
                        <input type="" class="form-control" id="bkname" placeholder="书籍名称" name="bookName" required>
                    </div>
                    <div class="form-group">
                        <label for="bkId">书籍id</label>
                        <input type="" class="form-control" id="bkId" placeholder="书籍id" name="id" required>
                    </div>
                    <div class="form-group">
                        <label for="bkprice">价格</label>
                        <input type="" class="form-control" id="bkprice" placeholder="价格" name="price" required>
                    </div>
                    <button type="submit" class="btn btn-default">添加</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
