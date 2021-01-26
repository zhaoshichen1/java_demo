package com.example.javaWebDemo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ForwardReceiverServlet", value = "/ForwardReceiverServlet")
public class ForwardReceiverServlet extends HttpServlet {
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
