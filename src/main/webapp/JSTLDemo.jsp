<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: zhaoshichen
  Date: 2021/2/4
  Time: 6:32 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%
        List list = new ArrayList<>();
        list.add("zhongfucheng");
        list.add("ouzicheng");
        list.add("xiaoming");
        session.setAttribute("list", list);
    %>
    <c:forEach  var="list" items="${list}" >
        ${list}<br>
    </c:forEach>
</head>
<body>

</body>
</html>
