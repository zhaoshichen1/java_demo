<%--
  Created by IntelliJ IDEA.
  User: zhaoshichen
  Date: 2021/2/4
  Time: 4:02 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>从ForwardSourceDemo转发而来</title>
    <jsp:scriptlet>
        if (request.getParameter("testk")!=null){
            out.println("获取数据:"+request.getParameter("testk"));
        } else {
            out.println("不是转发来的吧，未获取参数");
        }
    </jsp:scriptlet>
</head>
<body>

</body>
</html>
