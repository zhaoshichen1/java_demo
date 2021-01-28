package com.example.javaWebDemo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * 配合EncodingFilter的测试用的Servlet，进行中文的输入和输出测试
 */
@WebServlet(name = "EncodingFilterServlet", value = "/EncodingFilterServlet")
public class EncodingFilterServlet extends HttpServlet {

    /**
     * curl -X GET \
     *   http://localhost:8080/javaWebDemo_war_exploded/EncodingFilterServlet \
     *   -H 'Content-Type: application/x-www-form-urlencoded' \
     *   -H 'Postman-Token: 10645d36-e9bf-4a1e-99ac-69fbc259aa20' \
     *   -H 'cache-control: no-cache'
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write("输出中文试试 123 abc");
    }

    /**
     * curl -X POST \
     *   http://localhost:8080/javaWebDemo_war_exploded/EncodingFilterServlet \
     *   -H 'Content-Type: application/x-www-form-urlencoded' \
     *   -H 'Postman-Token: 01409723-e373-4d7b-a926-570501459032' \
     *   -H 'cache-control: no-cache' \
     *   -d 'testK=%E4%B8%AD%E6%96%87%E5%8F%82%E6%95%B0123&undefined='
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String testV = request.getParameter("testK");
        if (testV != null){
            System.out.println(testV);
            return ;
        }
        System.out.println("Param Empty");
    }
}
