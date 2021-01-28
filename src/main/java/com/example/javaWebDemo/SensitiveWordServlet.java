package com.example.javaWebDemo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

@WebServlet(name = "SensitiveWordServlet", value = "/SensitiveWordServlet")
public class SensitiveWordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    /**
     * 测试输入的时候带敏感词，获取后再输出
     *
     * curl -X POST \
     *   http://localhost:8080/javaWebDemo_war_exploded/SensitiveWordServlet \
     *   -H 'Content-Type: application/x-www-form-urlencoded' \
     *   -H 'Postman-Token: bc1ba256-7950-44c9-ae5c-a044ad73709f' \
     *   -H 'cache-control: no-cache' \
     *   -d 'test1=a1%3D-3fsdhjlks&test2=%E6%95%8F%E6%84%9F%E8%AF%8D%E6%B5%8B%E8%AF%95&test3=%E6%AD%A3%E5%B8%B8&undefined='
     *
     *   应该输出（敏感词被替换了）：
     *   Key=test1,Value=**=-3fsdhjlks
     *   Key=test2,Value=***测试
     *   Key=test3,Value=正常
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Enumeration<String> params = request.getParameterNames();
        while (params.hasMoreElements()){
            String key = params.nextElement();
            response.getWriter().write("Key="+key+",Value="+request.getParameter(key)+"\n");
        }
    }
}
