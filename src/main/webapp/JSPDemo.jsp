<!DOCTYPE html>

<%--访问方式：http://localhost:8080/javaWebDemo_war_exploded/JSPDemo.jsp--%>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>

<%--1 JSP脚本，即Java代码  --%>
<%--1.1 写法1 简写 --%>
<%
    String a = "中文数据输出";
%>
<%--1.2 写法2 推荐 --%>
<jsp:scriptlet>
    out.write("New Style:"+a+"\n");
</jsp:scriptlet>
<br/>

<%--2 表达式输出--%>
<%="Old Style:"+a%>
<br/>

<%--3 JSP指令 --%>
<%--3.1 写法1 简写，设置UTF8编码并且设置错误处理页 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="TreatErrDemo.jsp" %>
<jsp:scriptlet>
    // 有参数时，手动造一个exception前往错误页面
    if (request.getParameter("name")!=null){
        out.write(2/0);
    }
</jsp:scriptlet>
<%--3.2 写法2 推荐，include其他JSP --%>
<jsp:directive.include file="ToIncludeDemo.jsp"/>
<br/>

<%-- 4.JSP标签/行为，见ForwardSourceDemo/ForwardTargetDemo --%>

<%-- 5.使用内置对象编程，测试pageContext的findAttribute，先在session中放入attribute --%>
<jsp:scriptlet>
    session.setAttribute("myk","myvalue");
</jsp:scriptlet>
<jsp:scriptlet>
    out.println("获取到属性："+pageContext.findAttribute("myk"));
</jsp:scriptlet>

<body>

</body>
</html>