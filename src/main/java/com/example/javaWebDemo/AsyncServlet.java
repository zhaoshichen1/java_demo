package com.example.javaWebDemo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * 实现一个异步处理请求的Servlet
 */
@WebServlet(name = "AsyncServlet", value = "/AsyncServlet", asyncSupported = true)
public class AsyncServlet extends HttpServlet {

    /**
     * curl -X GET \
     *   http://localhost:8080/javaWebDemo_war_exploded/AsyncServlet \
     *   -H 'Content-Type: application/x-www-form-urlencoded' \
     *   -H 'Postman-Token: 1a37a60c-a1f4-4830-9132-b6f2afd975a3' \
     *   -H 'cache-control: no-cache'
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       AsyncContext ctx = request.startAsync();
       ctx.start(new Runnable() {
           @Override
           public void run() {
               try {
                   Thread.sleep(2000); // 睡2秒
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               try {
                   ctx.getResponse().getWriter().write("finish request");
               } catch (IOException e) {
                   e.printStackTrace();
               }
               ctx.complete();
           }
       });
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}


