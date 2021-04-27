<%--
  Created by IntelliJ IDEA.
  User: wangyq
  Date: 2021/4/21
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/hello" method="post">
    <input type="text" name="name">
    <input type="submit">
</form>
</body>
</html>
