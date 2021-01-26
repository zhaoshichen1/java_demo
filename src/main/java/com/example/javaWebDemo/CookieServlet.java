package com.example.javaWebDemo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 演示如何向浏览器设置Cookie并且通过request获取Cookie
 */
@WebServlet(name = "CookieServlet", value = "/CookieServlet")
public class CookieServlet extends HttpServlet {

    /**
     * curl -X GET \
     *   http://localhost:8080/javaWebDemo_war_exploded/CookieServlet \
     *   -H 'Content-Type: application/x-www-form-urlencoded' \
     *   -H 'Postman-Token: f3f9520f-662e-483c-a38c-fe689373538f' \
     *   -H 'cache-control: no-cache'
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 创建Cookie对象，指定名称和值；中文需要指定按照UTF-8编码；
        Cookie cookie = new Cookie("username", URLEncoder.encode("中文+English","UTF-8"));

        // 向浏览器给一个Cookie；设置过期时间，否则关闭浏览器cookie即失效；
        cookie.setMaxAge(1000);
        response.addCookie(cookie);

        // 设置response的编码
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write("我已经向浏览器发送了一个Cookie");
    }

    /**
     * curl -X POST \
     *   http://localhost:8080/javaWebDemo_war_exploded/CookieServlet \
     *   -H 'Content-Type: application/x-www-form-urlencoded' \
     *   -H 'Postman-Token: 0e964f12-3e39-4773-9e9c-0a40b979c157' \
     *   -H 'cache-control: no-cache'
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 遍历打印所有cookie
        Cookie[] cks = request.getCookies();
        for (int i=0;i<cks.length;i++){
            System.out.println(cks[i].getValue());
        }
    }
}
