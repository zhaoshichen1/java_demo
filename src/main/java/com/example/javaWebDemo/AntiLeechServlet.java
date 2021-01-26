package com.example.javaWebDemo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * 防盗链逻辑实现
 */
@WebServlet(name = "AntiLeechServlet", value = "/AntiLeechServlet")
public class AntiLeechServlet extends HttpServlet {

    /**
     * 以下的请求会命中防盗链逻辑从而进行重定向；需要从首页点击请求后访问；
     * curl -X GET \
     *   http://localhost:8080/javaWebDemo_war_exploded/AntiLeechServlet \
     *   -H 'Content-Type: application/x-www-form-urlencoded' \
     *   -H 'Postman-Token: 51b00920-e009-4a3d-b7fd-e630f692c54e' \
     *   -H 'cache-control: no-cache'
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String refer = request.getHeader("Referer");
        System.out.println(refer);

        // 不是从我的首页进来的，不被允许的访问
        if (refer == null || !refer.contains("http://localhost:8080/javaWebDemo_war_exploded/")){
            response.sendRedirect("/javaWebDemo_war_exploded");
            return ;
        }

        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write("恭喜你，被允许的访问获取到数据了！");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
