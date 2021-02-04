<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="AntiLeechServlet">模拟需要设置防盗链的资源</a>
<br/>
<a href="JSPDemo.jsp">跳转去JSP Demo页面</a>
<br/>
<a href="JSPDemo.jsp?name=1">跳转去JSP模拟error的情况</a>
<br/>
<a href="ForwardSourceDemo.jsp">测试JSP标签 Forward内部转发</a>
<br/>
<a href="JavaBeanFormDemo.jsp">测试Java Bean表单赋值</a>
<br/>
<a href="JSTLDemo.jsp">JSTL 遍历操作</a>
<br/>
同时在线人数:${num}
<br/>
</body>
</html>