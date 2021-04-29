<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
    <style>
        h2 {
            width: 180px;
            height: 30px;
            font-size: 18px;
        }

        a {
            text-decoration: none;
            color: black;
            margin: 100px auto;
            text-align: center;
            line-height: 38px;
            background-color: cornflowerblue;
            border-radius: 5px;

        }
    </style>
<%--    <script src="/static/js/jquery.js"></script>--%>
    　　<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script>
        function a() {
            $.post({
                url: "${pageContext.request.contextPath}/book/a1",
                data: {"name": $("#username").val()},
                success: function (data) {
                    console.log(data)
                },
                error: function () {
                }
            })
        }

    </script>
</head>
<body>
<h2>
    <a href="${pageContext.request.contextPath}/book/allBooks">调用controller /book/allBooks</a>
</h2>
<hr/>
<%--失去焦点的时候，发起一个请求到后台--%>
<h3>ajax请求</h3>
用户名:<input type="text" id="username" onblur="a()">
</body>
</html>
