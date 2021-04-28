<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
    <style>
        h2{
            width: 180px;
            height: 30px;
            font-size: 18px;
        }
        a{
            text-decoration: none;
            color: black;
            margin: 100px auto;
            text-align: center;
            line-height: 38px;
            background-color: cornflowerblue;
            border-radius: 5px;

        }
    </style>
</head>
<body>
<h2>
    <a href="${pageContext.request.contextPath}/book/allBooks">调用controller /book/allBooks</a>
</h2>
</body>
</html>
