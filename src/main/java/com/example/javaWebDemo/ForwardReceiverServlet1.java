package com.example.javaWebDemo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * 演示 Tomcat服务器内转发功能的实现 - 负责接收的Servlet1
 */
@WebServlet(name = "ForwardReceiverServlet1", value = "/ForwardReceiverServlet1")
public class ForwardReceiverServlet1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String value = (String)(request.getAttribute("testK"));
        response.getWriter().write("Got:"+value+" Req:"+request.getParameter("testK"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write("Got Param:" + request.getParameter("testK")+"\n");
    }
}
