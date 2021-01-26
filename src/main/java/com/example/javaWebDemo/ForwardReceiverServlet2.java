package com.example.javaWebDemo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 演示 Tomcat服务器内转发功能的实现 - 负责接收的Servlet2
 */
@WebServlet(name = "ForwardReceiverServlet2", value = "/ForwardReceiverServlet2")
public class ForwardReceiverServlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String value = (String)(request.getAttribute("testK"));
        response.getWriter().write("Receiver 2 Got:"+value);
    }
}
