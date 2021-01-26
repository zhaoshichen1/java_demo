package com.example.javaWebDemo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 演示使用Session - 在session中存储对象和修改JSESSIONID的过期时间
 */
@WebServlet(name = "SessionServlet1", value = "/SessionServlet1")
public class SessionServlet1 extends HttpServlet {

    /**
     * curl -X GET \
     *   http://localhost:8080/javaWebDemo_war_exploded/SessionServlet1 \
     *   -H 'Content-Type: application/x-www-form-urlencoded' \
     *   -H 'Postman-Token: 09febdc6-f070-4986-83ac-4cb6c92eaff0' \
     *   -H 'cache-control: no-cache'
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession ss = request.getSession();
        ss.setAttribute("k1","v1");
        ss.setAttribute("k2","v2");

        // 设置session的有效时间
        ss.setMaxInactiveInterval(60);

        // 修改session对应的cookie的有效时间
        Cookie cookie = new Cookie("JSESSIONID", ss.getId());
        cookie.setMaxAge(1000);
        response.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
