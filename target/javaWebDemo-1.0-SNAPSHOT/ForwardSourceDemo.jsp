<%--
  Created by IntelliJ IDEA.
  User: zhaoshichen
  Date: 2021/2/4
  Time: 4:00 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我是个Forward标签的Demo，跳转到JSPDemo</title>
    <%-- 4.JSP标签/行为 --%>
    <jsp:forward page="ForwardTargetDemo.jsp">
        <jsp:param name="testk" value="testv"/>
    </jsp:forward>
</head>
<body>

</body>
</html>
