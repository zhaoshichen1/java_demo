<%--
  Created by IntelliJ IDEA.
  User: zhaoshichen
  Date: 2021/2/4
  Time: 3:50 下午
  To change this template use File | Settings | File Templates.

  必须要通过命令声明为error page
--%>
<jsp:directive.page contentType="text/html;charset=UTF-8" language="java"
         isErrorPage="true"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--打印异常细节--%>
<jsp:scriptlet>
    out.println("异常"+exception);
</jsp:scriptlet>

</body>
</html>
