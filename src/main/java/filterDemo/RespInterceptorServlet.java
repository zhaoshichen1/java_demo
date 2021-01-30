package filterDemo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "RespInterceptorServlet", value = "/RespInterceptorServlet")
public class RespInterceptorServlet extends HttpServlet {
    /**
     * curl -X GET \
     *   http://localhost:8080/javaWebDemo_war_exploded/RespInterceptorServlet \
     *   -H 'Content-Type: application/x-www-form-urlencoded' \
     *   -H 'Postman-Token: 5f333cac-9b18-4a1e-89d8-054c799b5a96' \
     *   -H 'cache-control: no-cache'
     * 测试PrintWriter
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write("测试数据 abcde");
    }

    /**
     * curl -X POST \
     *   http://localhost:8080/javaWebDemo_war_exploded/RespInterceptorServlet \
     *   -H 'Content-Type: application/x-www-form-urlencoded' \
     *   -H 'Postman-Token: 33a76038-9a13-4ccc-a465-347eee0d1b21' \
     *   -H 'cache-control: no-cache'
     * 测试ServletOutputStream
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getOutputStream().write("测试数据 dsdas".getBytes(StandardCharsets.UTF_8));
    }
}
