package com.example.javaWebDemo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 演示使用Session - 获取session中的信息
 */
@WebServlet(name = "SessionServlet2", value = "/SessionServlet2")
public class SessionServlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 遍历打印所有session中的数据
        HttpSession ss = request.getSession();
        Enumeration<String> elements = ss.getAttributeNames();
        while(elements.hasMoreElements()){
            String key = elements.nextElement();
            response.getWriter().write("Key="+key+",Value="+ss.getAttribute(key)+"\n");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
