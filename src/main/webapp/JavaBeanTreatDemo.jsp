<%--
  Created by IntelliJ IDEA.
  User: zhaoshichen
  Date: 2021/2/4
  Time: 5:25 下午
  To change this template use File | Settings | File Templates.

  curl 'http://localhost:8080/javaWebDemo_war_exploded/JavaBeanTreatDemo.jsp' \
  -H 'Content-Type: application/x-www-form-urlencoded' \
  -H 'Cookie: JSESSIONID=483929377A7CAC4E4394E7536E59EA2C' \
  --data-raw 'name=rr&age=111' \
  --compressed
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%-- 声明一个Person的对象p --%>
<jsp:useBean id="p" class="javaBeanDemo.Person" scope="page"/>
<%-- 自动通过body中的参数进行匹配，填入p对象中；实际上通过setter操作 --%>
<jsp:setProperty name="p" property="age"/>
<jsp:setProperty name="p" property="name"/>
年龄为:<jsp:getProperty name="p" property="age"/><br/>
姓名为:<jsp:getProperty name="p" property="name"/>
</body>
</html>
