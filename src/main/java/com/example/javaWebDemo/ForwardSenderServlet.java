package com.example.javaWebDemo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * 实现Tomcat内部Servlet之间的request转发
 */
@WebServlet(name = "ForwardSenderServlet", value = "/ForwardSenderServlet")
public class ForwardSenderServlet extends HttpServlet {

    /**
     * curl -X GET \
     *   http://localhost:8080/javaWebDemo_war_exploded/ForwardSenderServlet \
     *   -H 'Content-Type: application/x-www-form-urlencoded' \
     *   -H 'Postman-Token: 73ecc3e5-d294-4d7b-b3de-3ffbfb3d8d32' \
     *   -H 'cache-control: no-cache'
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("ForwardReceiverServlet");

        request.setAttribute("testK", "testV");
        dispatcher.forward(request, response);
    }

    /**
     * curl -X POST \
     *   http://localhost:8080/javaWebDemo_war_exploded/ForwardSenderServlet \
     *   -H 'Content-Type: application/x-www-form-urlencoded' \
     *   -H 'Postman-Token: 87c34026-c9d6-49ff-aeed-84b124b5805c' \
     *   -H 'cache-control: no-cache' \
     *   -d 'testK=recevier%201111&undefined='
     * 使用include进行多次转发，response顺序输出
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("testK", "testV");
        request.getRequestDispatcher("ForwardReceiverServlet1").include(request, response);
        request.getRequestDispatcher("ForwardReceiverServlet2").include(request, response);
    }
}
